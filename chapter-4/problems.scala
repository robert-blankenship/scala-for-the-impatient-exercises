object problems {
	
	// Write a function minmax(values: Array[Int]) that returns a pair
	// containing the smallest and largest values in the array.
	object p8 {
		def minmax(values: Array[Int]): (Int, Int) = {
			( values.min, values.max )
		}
	}

	// Write a function lteqgt(values: Array[Int], v: Int) that returns
	// a triple containing the counts of values less than v, equal to v,
	// and greater than v.
	object p9 {
		def lteqgt(values: Array[Int], v: Int): (Int, Int, Int) = {
			(values.count(_ < v), values.count(_ == v), values.count(_ > v))
		}
	}


	// Come up with a usecase for String().zip(String())
	object p10 {
		// ???
	}

	def printAll {
		println(p8.minmax(Array(1,2,3,4)))
		println(p9.lteqgt(Array(1,2,3,4), 2))
	}

}

problems.printAll