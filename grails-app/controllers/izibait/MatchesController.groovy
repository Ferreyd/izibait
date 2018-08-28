package izibait

import grails.validation.ValidationException
import groovy.json.JsonSlurper

import java.sql.Date

import static org.springframework.http.HttpStatus.*

class MatchesController {

    MatchesService matchesService
    BetService betService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        Date now = new Date(Calendar.getInstance().getTime().getTime())
        Date previousWeek =  now - 14
        def res = Matches.findAllByDateBetween(previousWeek, now)
        def betMatches = betService.computeBetMatchObject(res)
        respond matchesService.list(params), model:[matchesCount: matchesService.count(), selectedMatch: betMatches]
    }

    def show(Long id) {
        respond matchesService.get(id)
        respond matchesService.list(params), model:[matchesCount: matchesService.count(),view: 'index']
    }

    def loadJson(){
        betService.loadJson()
        index(500)
    }

    def create() {
        respond new Matches(params)
    }

    def save(Matches matches) {
        if (matches == null) {
            notFound()
            return
        }

        try {
            matchesService.save(matches)
        } catch (ValidationException e) {
            respond matches.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'matches.label', default: 'Matches'), matches.id])
                redirect matches
            }
            '*' { respond matches, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond matchesService.get(id)
    }

    def update(Matches matches) {
        if (matches == null) {
            notFound()
            return
        }

        try {
            matchesService.save(matches)
        } catch (ValidationException e) {
            respond matches.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'matches.label', default: 'Matches'), matches.id])
                redirect matches
            }
            '*'{ respond matches, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        matchesService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'matches.label', default: 'Matches'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'matches.label', default: 'Matches'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    private def jsonloading(){
        def jsonSlurper = new JsonSlurper()
        def jsonObject = jsonSlurper.parse(new File("C:/Users/cordi/IdeaProjects/Izibait/grails-app/assets/jsonFiles/matches.json"))
        jsonObject.each { json ->

            Team t1 = new Team()
            Team t2 = new Team()
            Odds o = new Odds()
            Matches m = new Matches()

            if(!Team.findByName(json.team1)){
                t1.setName(json.team1)
                t1.save(flush: true)
            }else {
                t1 = Team.findByName(json.team1)
            }


            if(!Team.findByName(json.team2)){
                t2.setName(json.team2)
                t2.save(flush: true)
            }else {
                t2 = Team.findByName(json.team2)
            }

            o.setOddTeam1(Double.parseDouble(json.team1_odds))
            o.setOddTeam2(Double.parseDouble(json.team2_odds))
            o.setOddDraw(Double.parseDouble(json.draw_odds))
            o.save(flush: true)

            m.setDate(new Date(Long.parseLong(json.start_time)*1000))
            m.setOutcome(json.outcome)

            m.setOdds(o)
            m.setTeam1(t1)
            m.setTeam2(t2)

            m.save(flush: true)

        }
    }
}
