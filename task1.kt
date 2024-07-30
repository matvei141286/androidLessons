//Программа создает массив из 10 случайных целых чисел в диапазоне от -100 до 100 и выводит в консоль максимальное, минимальное и среднее значение

fun main() {
    val arrayOfRandomNumbers = IntArray(10) { (Math.random() * 201 - 100).toInt() }
    println("${arrayOfRandomNumbers.joinToString(", ")}")
    var maxValue = arrayOfRandomNumbers[0]
    var minValue = arrayOfRandomNumbers[0]
    var sum = arrayOfRandomNumbers[0].toDouble()
    for (i in 1 until arrayOfRandomNumbers.size) {
        if (arrayOfRandomNumbers[i] > maxValue) {
            maxValue = arrayOfRandomNumbers[i]
        }
        if (arrayOfRandomNumbers[i] < minValue) {
            minValue = arrayOfRandomNumbers[i]
        }
        sum += arrayOfRandomNumbers[i].toDouble()
    }
    val averageValue = (sum / arrayOfRandomNumbers.size)
    println("Max value = $maxValue")
    println("Min value = $minValue")
    println("Average value = $averageValue")

}
