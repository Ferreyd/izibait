package izibait

import Businessobjects.BetMatch
import grails.gorm.transactions.Transactional
import groovy.json.JsonSlurper

import java.sql.Date

@Transactional
class BetService {

    /**
     * Charge un fichier json à un endroit donné
     * @return
     */
    def loadJson() {
        def jsonSlurper = new JsonSlurper()
        def jsonObject = jsonSlurper.parse(new File("/grails-app/assets/jsonFiles/matches.json"))

        //Je charge une hashmap avec le nom de l'équipe en clé
        //ainsi je ne fait qu'une chargement de la base et je pioche dans la map ce qui est plus optimisé
        HashMap<String, Team> teamHashMap = new HashMap<>()
        Team.findAll().each { t ->
            teamHashMap.put(t.name, t)
        }

        jsonObject.each { json ->

            Team t1 = new Team()
            Team t2 = new Team()
            Odds o = new Odds()
            Matches m = new Matches()

            if (!teamHashMap.get(json.team1)) {
                t1.setName(json.team1)
                t1.save(flush: true)
                teamHashMap.put(t1.name, t1)
            } else {
                t1 = teamHashMap.get(json.team1)
            }
            if (!teamHashMap.get(json.team2)) {
                t2.setName(json.team2)
                t2.save(flush: true)
                teamHashMap.put(t2.name, t2)
            } else {
                t2 = teamHashMap.get(json.team2)
            }

            o.setOddTeam1(Double.parseDouble(json.team1_odds))
            o.setOddTeam2(Double.parseDouble(json.team2_odds))
            o.setOddDraw(Double.parseDouble(json.draw_odds))
            o.save(flush: true)

            m.setDate(new Date(Long.parseLong(json.start_time) * 1000))
            m.setOutcome(json.outcome)

            m.setOdds(o)
            m.setTeam1(t1)
            m.setTeam2(t2)

            m.save(flush: true)

        }
    }

    def computeTopTable(List<Matches> matches) {

    }

    def computeBetMatchObject(List<Matches> matches) {
        List<BetMatch> betMatches = new ArrayList<>()
        matches.each { m ->
            BetMatch betMatch = new BetMatch()
            betMatch.setTeam1(m.getTeam1().getName())
            betMatch.setTeam2(m.getTeam2().getName())
            betMatch.setOddTeam1(m.getOdds().getOddTeam1())
            betMatch.setOddTeam2(m.getOdds().getOddTeam2())
            betMatch.setOddDraw(m.getOdds().getOddDraw())
            betMatch.setDate(m.getDate())
            if (m.getOutcome()) {
                betMatch.setOutcome(m.getOutcome())
            }

            Double maxDelta = Math.max(betMatch.getOddTeam1(), betMatch.getOddTeam2()) - Math.min(betMatch.getOddTeam1(), betMatch.getOddTeam2())
            betMatch.maxDelta = maxDelta
            betMatch.bettingResult = Math.min(betMatch.getOddTeam1(), betMatch.getOddTeam2())

            betMatches.add(betMatch)

        }

        return betMatches
    }
}