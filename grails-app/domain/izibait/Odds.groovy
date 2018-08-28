package izibait

class Odds {

    Double oddTeam1
    Double oddTeam2
    Double oddDraw


    static constraints = {
        oddTeam1 nullable: false
        oddTeam2 nullable: false
        oddDraw nullable: false
    }
}
