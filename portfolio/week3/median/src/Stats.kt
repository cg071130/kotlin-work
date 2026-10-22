// Task 7.7.1: statistics functions
fun median(list: List<Number>): Double {
    if (list.isEmpty()) {
        throw IllegalArgumentException("无法计算空列表的中位数")
    }

    // 使用传入的 list 变量代替 this
    val sortedList = list.map { it.toDouble() }.sorted()
    val mid = sortedList.size / 2

    return if (sortedList.size % 2 == 0) {
        (sortedList[mid - 1] + sortedList[mid]) / 2.0
    } else {
        sortedList[mid]
    }
}