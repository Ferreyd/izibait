package Businessobjects

/**
 * Created by cordi on 28/08/2018.
 */
class BetMatch {
    private Date date
    private String team1
    private String team2
    private Double oddTeam1
    private Double oddTeam2
    private Double oddDraw
    private Double maxDelta
    private Double bettingResult
    private String outcome

    Date getDate() {
        return date
    }

    String getOutcome() {
        return outcome
    }

    void setOutcome(String outcome) {
        this.outcome = outcome
    }

    void setDate(Date date) {
        this.date = date
    }

    String getTeam1() {
        return team1
    }

    void setTeam1(String team1) {
        this.team1 = team1
    }

    String getTeam2() {
        return team2
    }

    void setTeam2(String team2) {
        this.team2 = team2
    }

    Double getOddTeam1() {
        return oddTeam1
    }

    void setOddTeam1(Double oddTeam1) {
        this.oddTeam1 = oddTeam1
    }

    Double getOddTeam2() {
        return oddTeam2
    }

    void setOddTeam2(Double oddTeam2) {
        this.oddTeam2 = oddTeam2
    }

    Double getOddDraw() {
        return oddDraw
    }

    void setOddDraw(Double oddDraw) {
        this.oddDraw = oddDraw
    }

    Double getMaxDelta() {
        return maxDelta
    }

    void setMaxDelta(Double maxDelta) {
        this.maxDelta = maxDelta
    }

    Double getBettingResult() {
        return bettingResult
    }

    void setBettingResult(Double bettingResult) {
        this.bettingResult = bettingResult
    }
}
