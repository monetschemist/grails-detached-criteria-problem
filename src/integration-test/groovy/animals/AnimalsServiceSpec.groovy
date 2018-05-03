package animals

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class AnimalsServiceSpec extends Specification {

    AnimalsService animalsService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Animals(...).save(flush: true, failOnError: true)
        //new Animals(...).save(flush: true, failOnError: true)
        //Animals animals = new Animals(...).save(flush: true, failOnError: true)
        //new Animals(...).save(flush: true, failOnError: true)
        //new Animals(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //animals.id
    }

    void "test get"() {
        setupData()

        expect:
        animalsService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Animals> animalsList = animalsService.list(max: 2, offset: 2)

        then:
        animalsList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        animalsService.count() == 5
    }

    void "test delete"() {
        Long animalsId = setupData()

        expect:
        animalsService.count() == 5

        when:
        animalsService.delete(animalsId)
        sessionFactory.currentSession.flush()

        then:
        animalsService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Animals animals = new Animals()
        animalsService.save(animals)

        then:
        animals.id != null
    }
}
