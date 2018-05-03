package animals

import grails.gorm.services.Service

@Service(Animals)
interface AnimalsService {

    Animals get(Serializable id)

    List<Animals> list(Map args)

    Long count()

    void delete(Serializable id)

    Animals save(Animals animals)

}