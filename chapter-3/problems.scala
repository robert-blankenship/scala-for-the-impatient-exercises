object problems {
	
	// Write a code snippet that sets a to an array of n random integers between 0 (inclusive) and n (exclusive).
	object p1 {
		def arrayOfNRandomIntegers(n: Int): Array[Int] = {
			Array.range(0,10).map( _ => scala.util.Random.nextInt )
		}
	}

	// Write a loop that swaps adjacent elements of an array of
	// integers. For example, Array(1, 2, 3, 4, 5)
	// becomes Array(2, 1, 4, 3, 5).	
	object p2 {
		def swapAdjacentElements(arr: Array[Int]): Array[Int] = {
			for (i <- 0 until arr.length) {
				if (i % 2 == 1) {
					val el = arr(i)
					arr(i) = arr(i - 1)
					arr(i - 1) = el
				}
			}
			arr
		}
	}

	// Repeat the preceding assignment, but produce a new array with the swapped values. Use for/yield
	object p3 {
		def isEven(x: Int): Boolean = x % 2 == 0
		def isOdd(x: Int): Boolean = !isEven(x)

		def createSwappedArray(arr: Array[Int]): Array[Int] = {
			val range = (0 until arr.length).toArray

			for (i <- range) yield {
				if (isOdd(i)) {
					arr(i - 1)
				} else if (isEven(i) && i + 1 < arr.length) {
					arr(i + 1)
				} else {
					arr(i)
				}
			}
		}

		def createSwappedArrayFunctional(arr: Array[Int]): Array[Int] = {
			var i = -1

			arr.map { el =>
				i += 1

				if (isOdd(i)) {
					arr(i - 1)
				} else if (isEven(i) && i + 1 < arr.length) {
					arr(i + 1) 
				} else {
					arr(i)
				}
			}
		}
	}

	// Given an array of integers, produce a new array that
	// contains all positive values of the original array,
	// in their original order, followed by all values that
	// are zero or negative, in their original order.		
	object p4 {
		// Much cleaner than using a loop.
		def binnedArrayBySign(arr: Array[Int]): Array[Int] = {
			arr.filter( _ >= 0) ++ arr.filter( _ < 0)
		}

		def binnedArrayBySignLoop(arr: Array[Int]): Array[Int] = {
			var positiveInts = Array[Int]()
			var negativeInts = Array[Int]()

			for (i <- 0 until arr.length) {
				val el = arr(i)

				if (el < 0) {
					negativeInts = negativeInts :+ el
				} else {
					positiveInts = positiveInts :+ el
				}
			}
			positiveInts ++ negativeInts
		}
	}

	// How do you compute the average of an Array[Double]? 
	object p5 {
		def getAverage(arr: Array[Double]): Double = {
			arr.sum / arr.length
		}
	}

	// How do you rearrange the elements of an Array[Int] so that they appear in
	// reverse sorted order? How do you do the same with an ArrayBuffer[Int]?
	object p6 {
		def reverseSortedOrder(arr: IndexedSeq[Int]): IndexedSeq[Int] = {
			arr.sortWith(_ > _)
		}
	}

	// Write a code snippet that produces all values from an array with duplicates
	// removed. (Hint: Look at Scaladoc.)
	object p7 {
		def getUniques(arr: Array[Any]): Array[Any] = arr.toSet[Any].toArray
		def getUniques2(arr: Array[Any]): Array[Any] = arr.distinct
	}


    // Rewrite the example at the end of Section 3.4, “Transforming Arrays,” on page 34
    // using the drop method for dropping the index of the first match.
    // Look the method up in Scaladoc.
	object p8 {
		def positivesAndFirstNegative(a: collection.mutable.ArrayBuffer[Int]): collection.mutable.ArrayBuffer[Int] = {
			val indexes = for (i <- 0 until a.length if a(i) < 0) yield i
			for (j <- indexes.drop(1).reverse) a.remove(j)
			a
		}
	}

	// Make a collection of all time zones returned by java.util.TimeZone.getAvailableIDs
	// that are in America. Strip off the "America/" prefix and sort the result.
	object p9 {
		def getAmericanTimezones: Array[String] = {
			val country = "America"
			val selector = country + "/"

			java.util.TimeZone.getAvailableIDs
				.filter(_.startsWith(selector))
				.map(_.drop(selector.length))
				.sorted
		}
	}

	// Import java.awt.datatransfer._ and make an object of type SystemFlavorMap with the call
	// 		val flavors = SystemFlavorMap.getDefaultFlavorMap().asInstanceOf[SystemFlavorMap]
	// Then call the getNativesForFlavor method with parameter DataFlavor.imageFlavor and get
	// the return value as a Scala buffer. (Why this obscure class? It’s hard to find uses
	// of java.util.List in the standard Java library.)
	object p10 {
		def getImageFlavors: Array[String] = {
			import java.awt.datatransfer._

			val flavors = SystemFlavorMap.getDefaultFlavorMap().asInstanceOf[SystemFlavorMap]
			val imageFlavors = flavors.getNativesForFlavor(DataFlavor.imageFlavor)

			imageFlavors.toArray.map(_.toString)
		}
	}


	def printAll {
		println(p1.arrayOfNRandomIntegers(10).mkString(", "))
		println(p2.swapAdjacentElements(Array.range(0,10)).mkString(", "))
		println(p3.createSwappedArray(Array.range(0,11)).mkString(", "))
		println(p4.binnedArrayBySign(Array.range(-9, -1) ++ Array.range(5,11)).mkString(", "))
		println(p5.getAverage(Array(9.0, 1.0)))
		
		println(p6.reverseSortedOrder(Array(4, 9, 98, 1)).mkString(", "))
		println(p6.reverseSortedOrder(scala.collection.mutable.ArrayBuffer(4, 9, 98, 1)).mkString(", "))
		
		println(p7.getUniques(Array(1,1,2,3)).mkString(", "))
		
		println(p8.positivesAndFirstNegative(collection.mutable.ArrayBuffer(1,1,-2,-3)).mkString(", "))
	
		println(p9.getAmericanTimezones.mkString(", "))

		println(p10.getImageFlavors.mkString(", "))
	}
}


problems.printAll