object problems {
	// Improve the Counter class in Section 5.1, “Simple Classes
	// and Parameterless Methods,” on page 51 so that it doesn’t
	// turn negative at Int.MaxValue.
	object p1 {
		class Counter {
			private var value = 0
			def increment() = if (value < Int.MaxValue) value += 1
			def current = value
		}
		
		val expectation = Int.MaxValue
		val testArguments = ()

		def runTest: Boolean = {
			val counter = new Counter
			for (i <- 0.until(Int.MaxValue)) counter.increment()
			counter.increment()
			counter.current == expectation
		}
	}

	object p2 {
		// Kind of verbose, but does exactly what the question asks for.
		class BankAccount(initialBalance: Int = 0) {
			private[this] var balanceHidden: Int = _
			def balance = balanceHidden
			private def balance_=(amount: Int) { balanceHidden = amount }

			// Can only be done internally.
			balance = initialBalance

			class InsufficientFundsException extends Exception {
				val message = "Insufficient Funds!"
			}

			def deposit(amount: Int) {
				if (amount < 0) withdraw(-amount) else balance += amount
			}

			def withdraw(amount: Int) {
				if (amount < 0) {
					deposit(amount)
				} else if (amount > balance) {
					throw new InsufficientFundsException
				} else {
					balance -= amount
				}
			}
		}

		class BankAccount_2(private var balance: Int = 0) {
			class InsufficientFundsException(val message:String = "Insufficient Funds!") extends Exception

			def deposit(amount: Int) {
				if (amount < 0) withdraw(-amount) else balance += amount
			}

			def withdraw(amount: Int) {
				if (amount < 0) {
					deposit(amount)
				} else if (amount > balance) {
					throw new InsufficientFundsException
				} else {
					balance -= amount
				}
			}

			def getBalance = balance
		}

		def runTest {
			val account = new BankAccount_2(1000)
			account.withdraw(200)
			account.deposit(300)
			println(account.getBalance)
		}
	}

	// Write a class Time with read-only properties hours and minutes and
	// a method before(other: Time): Boolean that checks whether this time
	// comes before the other. A Time object should be constructed as new Time(hrs, min),
	// where hrs is in military time format (between 0 and 23).
	object p3 {
		class Time(val hours: Int, val minutes: Int) {
			def totalMinutes = hours * 60 + minutes
			def before(other: Time): Boolean = this.totalMinutes < other.totalMinutes
		}

		def runTest {
			val t1 = new Time(12, 0)
			val t2 = new Time(13, 0)
			val t3 = new Time(13, 1)

			println(t1.before(t2))
			println(t2.before(t1))
			println(t2.before(t3))
		}
	}
    
    // Reimplement the Time class from the preceding exercise so that
    // the internal representation is the number of minutes since midnight
    // (between 0 and 24 × 60 – 1). Do not change the public interface.
    // That is, client code should be unaffected by your change.
	object p4 {
		class Time(val hours: Int, val minutes: Int) {
			private def totalMinutes = hours * 60 + minutes
			def before(other: Time): Boolean = this.totalMinutes < other.totalMinutes
		}

		def runTest {
			val t1 = new Time(12, 0)
			val t2 = new Time(13, 0)
			val t3 = new Time(13, 1)
			println(t1.before(t2))
			println(t2.before(t1))
			println(t2.before(t3))
		}
	}


	// Make a class Student with read-write JavaBeans properties name 
	// (of type String) and id (of type Long). What methods are generated? 
	// (Use javap to check.) Can you call the JavaBeans getters and setters
	// in Scala? Should you?
	object p5 {
		import beans.BeanProperty
		class Student(
			@BeanProperty var name: String,
			@BeanProperty var id: Long
		)

		def runTest { 
			val student = new Student("Bob", 69)
			println(student.getName)
			println(student.getId)
			println("Should you? Probably not. It's duplicate functionality.")
		}
	}

	object p6 {
		class Person(private var name: String, private var age: Int) {
			if (age < 0) age = 0
			def getAge = age
		}
		def runTest {
			val person = new Person("Robert", -1)
			println(person.getAge)
			
		}
	}

	// Write a class Person with a primary constructor that accepts a
	// string containing a first name, a space, and a last name,
	// such as new Person("Fred Smith"). Supply read-only properties
	// firstName and lastName. Should the primary constructor parameter
	// be a var, a val, or a plain parameter? Why?
	object p7 {
		class Person(fullName: String) {
			val firstName = fullName.split(" ")(0)
			val lastName = fullName.split(" ")(1) 
		}

		def runTest {
			val person = new Person("Robert Blankenship")
			println(person.firstName, person.lastName)
		}
	}

	// Make a class Car with read-only properties for manufacturer, model name,
	// and model year, and a read-write property for the license plate.
	// Supply four constructors. All require the manufacturer and model name.
	// Optionally, model year and license plate can also be specified in the
	// constructor. If not, the model year is set to -1 and the license plate to
	// the empty string. Which constructor are you choosing as the primary constructor? Why?
	object p8 {
		class Car(
			val manufacturer: String,
			val model: String,
			val modelYear: Int = -1,
			var licensePlate: String = ""
		)

		def runTest {
			val car = new Car("Toyota", "Corolla", 2014, "1AAA234")
			println(car)
		}
	}


	// Reimplement the class of the preceding exercise in Java, C#, or C++ (your choice).
	// How much shorter is the Scala class?
	object p9 {
		class Car { println("nah") }
		def runTest {val car = new Car()}
	}

	// Consider the class
	// 		class Employee(val name: String, var salary: Double) {
	// 			def this() { this("John Q. Public", 0.0) }
	// 		}
	// Rewrite it to use explicit fields and a default primary constructor.
	// Which form do you prefer? Why?
	object p10 {
		class EmployeeOriginal(val name: String, var salary: Double) {
			def this() { this("John Q. Public", 0.0) }
		}

		// I prefer this form, though the functionality is slightly different.
		class Employee(val name: String = "John Q. Public", var salary: Double = 0.0)

		def compare(e1: Employee, e2: EmployeeOriginal) {
			println(e1.name)
			println(e2.name)

			println(e1.salary)
			println(e2.salary)
		}

		compare(new Employee(), new EmployeeOriginal())
		// Different functionality.
		// compare(new Employee("Robert"), new EmployeeOriginal("Robert"))
		compare(new Employee("Robert", 10), new EmployeeOriginal("Robert", 10))
	}
}
problems.p10