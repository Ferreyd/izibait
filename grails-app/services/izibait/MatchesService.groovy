package izibait

import grails.gorm.services.Service

@Service(Matches)
interface MatchesService {

    Matches get(Serializable id)

    List<Matches> list(Map args)

    Long count()

    void delete(Serializable id)

    Matches save(Matches matches)


}