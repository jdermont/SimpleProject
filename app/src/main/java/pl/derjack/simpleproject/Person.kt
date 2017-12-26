package pl.derjack.simpleproject

data class Person(val name: String,
                  var age: Int = 0) {

    fun hello(name: String): String {
        return "Hello, " + name
    }

}




