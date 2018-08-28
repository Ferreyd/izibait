package izibait

class Team {

    String name

    static constraints = {
        name unique: true
    }
}
