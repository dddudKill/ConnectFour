fun solution(numbers: List<Int>, number: Int): MutableList<Int> {
    val list = numbers.toMutableList()
    list.add(number)
    return list
}