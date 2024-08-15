import java.lang.Exception

interface NewDataProcessor{
    fun processData(data: String)
    fun getResult(): String
}

class ModernDataProcessor: NewDataProcessor {

    private val legacyAdapter = LegacyAdapter()

    override fun processData(data: String) {
        // Вызов метода старой библиотеки через адаптер
        legacyAdapter.processData(data)
    }

    override fun getResult(): String {
        return legacyAdapter.getResult()
    }


    private class LegacyAdapter {

        fun processData(data: String) {
           LegacyDataProcessor.oldProcessData(data)

        }

        fun getResult(): String {
            return LegacyDataProcessor.oldGetResult()
        }
    }
}

class LegacyDataProcessor{
    companion object{

        private var result: String? = null

        fun oldProcessData(data: String){
            println("calling  an outdated method oldProcessData")
            result = data.uppercase()
        }

        fun oldGetResult(): String{
            println("calling  an outdated method oldGetResult")
            return result?: throw Exception("Something went wrong...")
        }
    }
}

fun main(){
    val dataProcessor = ModernDataProcessor()
    dataProcessor.processData("Matvey")
    println(dataProcessor.getResult())
}