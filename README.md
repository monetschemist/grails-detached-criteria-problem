Thu May  3 12:46:01 PDT 2018
Chris Hermansen clhermansen@gmail.com

Grails Version: 3.3.5
Groovy Version: 2.4.15
JVM Version: 1.8.0_171

Test application "animals"

Background

The application has one domain class, Animals, defined as

	package animals

	class Animals {

		String name
		Date bornOn
		String breeder
		Animals sire
		Animals dam

		static constraints = {
			name blank: false, nullable: false
			bornOn blank: false, nullable: false
			breeder blank: false, nullable: false
			sire nullable: true
			dam nullable: true
		}
	}

CRUD controller and views were created.

BootStrap.groovy initializes the database when Grails is run.

Use the Grails console to execute the following commands:

	animals.Animals.list().each { a -> println "a ${a.name} sire ${a.sire?.name} dam ${a.dam?.name} mgs ${a.dam?.sire?.name}" }

This will list all the animals currently loaded in the database.  The result should appear as:

	a Northern Dancer sire null dam null mgs null
	a Sweet Alliance sire null dam null mgs null
	a Formidable sire null dam null mgs null
	a As Blessed sire null dam null mgs null
	a Tentam sire null dam null mgs null
	a Nimble Deb sire null dam null mgs null
	a Excaliburs Lake sire null dam null mgs null
	a Sade sire null dam null mgs null
	a Shareef Dancer sire Northern Dancer dam Sweet Alliance mgs null
	a Glory of Hera sire Formidable dam As Blessed mgs null
	a Tecorno sire Tentam dam Nimble Deb mgs null
	a Excaliburs Glory sire Excaliburs Lake dam Sade mgs null
	a Glory of Dancer sire Shareef Dancer dam Glory of Hera mgs Formidable
	a Delectable sire Tecorno dam Excaliburs Glory mgs Excaliburs Lake
	a Delectable Baby sire Glory of Dancer dam Delectable mgs Tecorno
	a Pas de Nom sire null dam null mgs null
	a His Majesty sire null dam null mgs null
	a Spring Adieu sire null dam null mgs null
	a Raise a Native sire null dam null mgs null
	a Gold Digger sire null dam null mgs null
	a Fanfreluche sire null dam null mgs null
	a Danzig sire Northern Dancer dam Pas de Nom mgs null
	a Razyana sire His Majesty dam Spring Adieu mgs null
	a Mr. Prospector sire Raise a Native dam Gold Digger mgs null
	a La Voyageuse sire Tentam dam Fanfreluche mgs null
	a Danehill sire Danzig dam Razyana mgs His Majesty
	a Society Lady sire Mr. Prospector dam La Voyageuse mgs Tentam
	a Nasmatt sire Danehill dam Society Lady mgs Mr. Prospector
	Result: [animals.Animals : 1, animals.Animals : 2, animals.Animals : 3, animals.Animals : 4, animals.Animals : 5, animals.Animals : 6, animals.Animals : 7, animals.Animals : 8, animals.Animals : 9, animals.Animals : 10, animals.Animals : 11, animals.Animals : 12, animals.Animals : 13, animals.Animals : 14, animals.Animals : 15, animals.Animals : 16, animals.Animals : 17, animals.Animals : 18, animals.Animals : 19, animals.Animals : 20, animals.Animals : 21, animals.Animals : 22, animals.Animals : 23, animals.Animals : 24, animals.Animals : 25, animals.Animals : 26, animals.Animals : 27, animals.Animals : 28]

In that list we can see the animal "Society Lady" whose sire is "Mr. Prospector", dam is "La Voyageuse" and maternal grandsire is "Tentam".

Use the Grails console to execute the following commands (test of single detached criteria on dam name):

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

As expected, this will report

	single criteria on sire of dam name

	name Society Lady bornOn 1990-01-01 00:00:00.0 sire Mr. Prospector dam La Voyageuse mgs Tentam

Use the Grails console to execute the following commands (test of chained detached criteria on dam name and then sire of dam name):

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

As expected, this will report

	chained criteria on dam name and sire of dam name

	name Society Lady bornOn 1990-01-01 00:00:00.0 sire Mr. Prospector dam La Voyageuse mgs Tentam

Use the Grails console to execute the following commands (test of chained detached criteria on sire name and then sire of dam name):

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

Unexpectedly, no rows satisfy the chained criteria, giving the result:

	chained criteria on sire name and sire of dam name

What I discovered today is good news and bad.  Good because there appears to be a workaround; bad because this seems very perverse.

Reverse the order of the criteria in the above test to first select sire of dam name, then sire name:

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
	criteria.list().each { a-> println "name ${a.name} bornOn ${a.bornOn} sire ${a.sire?.name} dam ${a.dam?.name} mgs ${a.dam?.sire?.name}" 

This gives the expected answer!

	chained criteria on sire of dam name and sire name

	name Society Lady bornOn 1990-01-01 00:00:00.0 sire Mr. Prospector dam La Voyageuse mgs Tentam
