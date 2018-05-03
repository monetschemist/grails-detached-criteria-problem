package animals

class BootStrap {

    def init = { servletContext ->

		// Delectable Baby family tree

		def sss = new Animals(name:'Northern Dancer',bornOn:Date.parse('yyyy/MM/dd','1961/01/01'),breeder:'CAN',sire:null,dam:null); sss.save()
		def dss = new Animals(name:'Sweet Alliance',bornOn:Date.parse('yyyy/MM/dd','1974/01/01'),breeder:'USA',sire:null,dam:null); dss.save()

		def sds = new Animals(name:'Formidable',bornOn:Date.parse('yyyy/MM/dd','1975/01/01'),breeder:'USA',sire:null,dam:null); sds.save()
		def dds = new Animals(name:'As Blessed',bornOn:Date.parse('yyyy/MM/dd','1974/01/01'),breeder:'GB',sire:null,dam:null); dds.save()

		def ssd = new Animals(name:'Tentam',bornOn:Date.parse('yyyy/MM/dd','1969/01/01'),breeder:'USA',sire:null,dam:null); ssd.save()
		def dsd = new Animals(name:'Nimble Deb',bornOn:Date.parse('yyyy/MM/dd','1969/01/01'),breeder:'USA',sire:null,dam:null); dsd.save()

		def sdd = new Animals(name:'Excaliburs Lake',bornOn:Date.parse('yyyy/MM/dd','1983/01/01'),breeder:'USA',sire:null,dam:null); sdd.save()
		def ddd = new Animals(name:'Sade',bornOn:Date.parse('yyyy/MM/dd','1984/01/01'),breeder:'USA',sire:null,dam:null); ddd.save()

		def ss = new Animals(name:'Shareef Dancer',bornOn:Date.parse('yyyy/MM/dd','1980/01/01'),breeder:'USA',sire:sss,dam:dss); ss.save()
		def ds = new Animals(name:'Glory of Hera',bornOn:Date.parse('yyyy/MM/dd','1982/01/01'),breeder:'GB',sire:sds,dam:dds); ds.save()

		def sd = new Animals(name:'Tecorno',bornOn:Date.parse('yyyy/MM/dd','1980/01/01'),breeder:'USA',sire:ssd,dam:dsd); sd.save()
		def dd = new Animals(name:'Excaliburs Glory',bornOn:Date.parse('yyyy/MM/dd','1991/01/01'),breeder:'IND',sire:sdd,dam:ddd); dd.save()

		def s = new Animals(name:'Glory of Dancer',bornOn:Date.parse('yyyy/MM/dd','1993/01/01'),breeder:'USA',sire:ss,dam:ds); s.save()
		def d = new Animals(name:'Delectable',bornOn:Date.parse('yyyy/MM/dd','2001/01/01'),breeder:'USA',sire:sd,dam:dd); d.save()

		def f = new Animals(name:'Delectable Baby',bornOn:Date.parse('yyyy/MM/dd','2008/01/01'),breeder:'USA',sire:s,dam:d); f.save()

		def northernDancer = sss
		def tentam = ssd

		// Nasmatt family tree

		sss = northernDancer
		dss = new Animals(name:'Pas de Nom',bornOn:Date.parse('yyyy/MM/dd','1968/01/01'),breeder:'USA',sire:null,dam:null); dss.save()

		sds = new Animals(name:'His Majesty',bornOn:Date.parse('yyyy/MM/dd','1968/01/01'),breeder:'USA',sire:null,dam:null); sds.save()
		dds = new Animals(name:'Spring Adieu',bornOn:Date.parse('yyyy/MM/dd','1974/01/01'),breeder:'CAN',sire:null,dam:null); dds.save()

		ssd = new Animals(name:'Raise a Native',bornOn:Date.parse('yyyy/MM/dd','1961/01/01'),breeder:'USA',sire:null,dam:null); ssd.save()
		dsd = new Animals(name:'Gold Digger',bornOn:Date.parse('yyyy/MM/dd','1969/1962/01'),breeder:'USA',sire:null,dam:null); dsd.save()

		sdd = tentam
		ddd = new Animals(name:'Fanfreluche',bornOn:Date.parse('yyyy/MM/dd','1967/01/01'),breeder:'CAN',sire:null,dam:null); ddd.save()

		ss = new Animals(name:'Danzig',bornOn:Date.parse('yyyy/MM/dd','1977/01/01'),breeder:'USA',sire:sss,dam:dss); ss.save()
		ds = new Animals(name:'Razyana',bornOn:Date.parse('yyyy/MM/dd','1981/01/01'),breeder:'USA',sire:sds,dam:dds); ds.save()

		sd = new Animals(name:'Mr. Prospector',bornOn:Date.parse('yyyy/MM/dd','1970/01/01'),breeder:'USA',sire:ssd,dam:dsd); sd.save()
		dd = new Animals(name:'La Voyageuse',bornOn:Date.parse('yyyy/MM/dd','1975/01/01'),breeder:'CAN',sire:sdd,dam:ddd); dd.save()

		s = new Animals(name:'Danehill',bornOn:Date.parse('yyyy/MM/dd','1986/01/01'),breeder:'USA',sire:ss,dam:ds); s.save()
		d = new Animals(name:'Society Lady',bornOn:Date.parse('yyyy/MM/dd','1990/01/01'),breeder:'USA',sire:sd,dam:dd); d.save()

		f = new Animals(name:'Nasmatt',bornOn:Date.parse('yyyy/MM/dd','1998/01/01'),breeder:'GB',sire:s,dam:d); f.save()
    }
    def destroy = {
    }
}
