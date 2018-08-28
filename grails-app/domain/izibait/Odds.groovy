package izibait

class Odds {

    Double oddTeam1
    Double oddTeam2
    Double oddDraw

    Double calculated_oddTeam1
    Double calculated_oddTeam2
    Double calculated_oddDraw

    static constraints = {
        oddTeam1 nullable: false
        oddTeam2 nullable: false
        oddDraw nullable: false
        calculated_oddTeam1 nullable:  true
        calculated_oddTeam2 nullable: true
        calculated_oddDraw nullable:  true
    }
}
