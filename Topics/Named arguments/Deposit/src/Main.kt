import kotlin.math.pow

fun finalAmount (startingAmount: Double = 1000.0, yearlyPercent: Double = 5.0, years: Int = 10): Int {
    return (startingAmount * (1 + yearlyPercent / 100).pow(years)).toInt()
}

fun main() {
    val name = readln()
    val amount = readln().toDouble()
    when (name) {
        "amount" -> println(finalAmount(startingAmount = amount))
        "percent" -> println(finalAmount(yearlyPercent = amount))
        "years" -> println(finalAmount(years = amount.toInt()))
    }
}