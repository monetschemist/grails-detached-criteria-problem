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
