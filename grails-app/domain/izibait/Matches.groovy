package izibait

import java.sql.Date

class Matches {

    String outcome
    Date date

    Team team1
    Team team2

    Odds odds

    static constraints = {
        team1 nullable: false
        team2 nullable: false
        odds nullable: false
    }
}
