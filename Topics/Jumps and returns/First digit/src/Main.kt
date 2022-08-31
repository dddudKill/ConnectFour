fun main() {
    val str = readln()
    for (i in str.indices) {
        if (str[i].isDigit()) {
            println(str[i])
            break
        }
    }
}