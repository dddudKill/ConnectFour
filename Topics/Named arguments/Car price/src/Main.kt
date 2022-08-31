// complete this function, add default values
fun carPrice(old: Int = 5, kilometers: Int = 100000, maximumSpeed: Int = 120, automatic: Boolean = false) {
    println(20000 - old * 2000 +
            if (maximumSpeed < 120) { -100 * (120 - maximumSpeed) }
            else { 100 * (maximumSpeed - 120) } - 200 * (kilometers / 10000) + if (automatic) { 1500 } else { 0 })
}
