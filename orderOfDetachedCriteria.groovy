// animals.Animals.list().each { a -> println "a ${a.name} sire ${a.sire?.name} dam ${a.dam?.name} mgs ${a.dam?.sire?.name}" }

println()
println "single criteria on sire of dam name"
println()
criteria = new grails.gorm.DetachedCriteria(animals.Animals)
criteria = criteria.build {
    dam {
        sire {
            ilike 'name','Tentam'
        }
    }
}
criteria.list().each { a-> println "name ${a.name} bornOn ${a.bornOn} sire ${a.sire?.name} dam ${a.dam?.name} mgs ${a.dam?.sire?.name}" }


println()
println "chained criteria on dam name and sire of dam name"
println()
criteria = new grails.gorm.DetachedCriteria(animals.Animals)
criteria = criteria.build {
    dam {
        ilike 'name','La Voyageuse'
    }
}
criteria = criteria.build {
    dam {
        sire {
            ilike 'name','Tentam'
        }
    }
}
criteria.list().each { a-> println "name ${a.name} bornOn ${a.bornOn} sire ${a.sire?.name} dam ${a.dam?.name} mgs ${a.dam?.sire?.name}" }

println()
println "chained criteria on sire name and sire of dam name"
println()
criteria = new grails.gorm.DetachedCriteria(animals.Animals)
criteria = criteria.build {
    sire {
        ilike 'name','Mr. Prospector'
    }
}
criteria = criteria.build {
    dam {
        sire {
            ilike 'name','Tentam'
        }
    }
}
criteria.list().each { a-> println "name ${a.name} bornOn ${a.bornOn} sire ${a.sire?.name} dam ${a.dam?.name} mgs ${a.dam?.sire?.name}" }

println()
println "chained criteria on sire of dam name and sire name"
println()
criteria = new grails.gorm.DetachedCriteria(animals.Animals)
criteria = criteria.build {
    dam {
        sire {
            ilike 'name','Tentam'
        }
    }
}
criteria = criteria.build {
    sire {
        ilike 'name','Mr. Prospector'
    }
}
criteria.list().each { a-> println "name ${a.name} bornOn ${a.bornOn} sire ${a.sire?.name} dam ${a.dam?.name} mgs ${a.dam?.sire?.name}" }