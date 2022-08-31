fun main() {
    val in1 = readln()
    val action = readln()
    val in2 = readln()
    when(action) {
        "equals" -> println(in1 == in2)
        "plus" -> println("$in1$in2")
        "endsWith" -> println(in1.endsWith(in2))
        else -> println("Unknown operation")
    }
}