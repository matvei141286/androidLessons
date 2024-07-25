//В программе реализован метод проверки введенного слова на паллиндром с использованием рекурсии

fun isPalindrome(word: String): Boolean {
    if (word.length <= 1) {
        return true
    } else {
        return if (word.first() == word.last()) {
            isPalindrome(word.substring(1, word.length - 1))
        } else {
            false
        }
    }
}

fun main() {
    print("Write the word you want to check if it is a pallindrome: ")
    val word = readln()
    if (isPalindrome(word)) {
        println("$word - pallindrome")
    } else {
        println("$word - not pallindrome")
    }
}