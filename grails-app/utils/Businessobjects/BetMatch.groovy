package Businessobjects

/**
 * Created by cordi on 28/08/2018.
 */
class BetMatch {
    private def date
    private def team1
    private def team2
    private def oddTeam1
    private def oddTeam2
    private def oddDraw
    private def maxDelta
    private def bettingResult
    private def outcome

    def getDate() {
        return date
    }

    void setDate(date) {
        this.date = date
    }

    def getTeam1() {
        return team1
    }

    void setTeam1(team1) {
        this.team1 = team1
    }

    def getTeam2() {
        return team2
    }

    void setTeam2(team2) {
        this.team2 = team2
    }

    def getOddTeam1() {
        return oddTeam1
    }

    void setOddTeam1(oddTeam1) {
        this.oddTeam1 = oddTeam1
    }

    def getOddTeam2() {
        return oddTeam2
    }

    void setOddTeam2(oddTeam2) {
        this.oddTeam2 = oddTeam2
    }

    def getOddDraw() {
        return oddDraw
    }

    void setOddDraw(oddDraw) {
        this.oddDraw = oddDraw
    }

    def getMaxDelta() {
        return maxDelta
    }

    void setMaxDelta(maxDelta) {
        this.maxDelta = maxDelta
    }

    def getBettingResult() {
        return bettingResult
    }

    void setBettingResult(bettingResult) {
        this.bettingResult = bettingResult
    }

    def getOutcome() {
        return outcome
    }

    void setOutcome(outcome) {
        this.outcome = outcome
    }
}
