package pl.derjack.simpleproject

class PersonCaller {

    fun something() {

        val peter = Person("Peter", 15)
        val bob = peter.copy(name = "Bob")
        val youngerPeter = peter.copy(age = 10)

        val nick = Person("Nick")
        nick.age = 20

        val namedParams = Person(age = 18, name = "Alice")

    }

}

