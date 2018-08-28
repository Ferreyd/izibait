package izibait

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class MatchesServiceSpec extends Specification {

    MatchesServiceImpl matchesService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Matches(...).save(flush: true, failOnError: true)
        //new Matches(...).save(flush: true, failOnError: true)
        //Matches matches = new Matches(...).save(flush: true, failOnError: true)
        //new Matches(...).save(flush: true, failOnError: true)
        //new Matches(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //matches.id
    }

    void "test get"() {
        setupData()

        expect:
        matchesService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Matches> matchesList = matchesService.list(max: 2, offset: 2)

        then:
        matchesList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        matchesService.count() == 5
    }

    void "test delete"() {
        Long matchesId = setupData()

        expect:
        matchesService.count() == 5

        when:
        matchesService.delete(matchesId)
        sessionFactory.currentSession.flush()

        then:
        matchesService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Matches matches = new Matches()
        matchesService.save(matches)

        then:
        matches.id != null
    }
}
