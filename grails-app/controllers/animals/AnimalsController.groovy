package animals

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class AnimalsController {

    AnimalsService animalsService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond animalsService.list(params), model:[animalsCount: animalsService.count()]
    }

    def show(Long id) {
        respond animalsService.get(id)
    }

    def create() {
        respond new Animals(params)
    }

    def save(Animals animals) {
        if (animals == null) {
            notFound()
            return
        }

        try {
            animalsService.save(animals)
        } catch (ValidationException e) {
            respond animals.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'animals.label', default: 'Animals'), animals.id])
                redirect animals
            }
            '*' { respond animals, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond animalsService.get(id)
    }

    def update(Animals animals) {
        if (animals == null) {
            notFound()
            return
        }

        try {
            animalsService.save(animals)
        } catch (ValidationException e) {
            respond animals.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'animals.label', default: 'Animals'), animals.id])
                redirect animals
            }
            '*'{ respond animals, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        animalsService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'animals.label', default: 'Animals'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'animals.label', default: 'Animals'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
