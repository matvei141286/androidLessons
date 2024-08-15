enum class GeneratorType{
    SIMPLE, ADVANCE
}

interface SentenceGenerator{
    fun generateSentence(): String
}

class SimpleSentenceGenerator: SentenceGenerator{
    private val subjects = listOf("Cat", "Dog", "Bird")
    private val verbs = listOf("run", "jump", "fly")

    override fun generateSentence(): String {
        val subject = subjects.random()
        val verb = verbs.random()
        return "$subject $verb."
    }
}

class AdvanceSentenceGenerator: SentenceGenerator{
    private val subjects = listOf("Cat", "Dog", "Bird")
    private val verbs = listOf("run", "jump", "fly")
    private val adverbs = listOf("quickly", "slowly", "silently")

    override fun generateSentence(): String {
        val subject = subjects.random()
        val verb = verbs.random()
        val obj = adverbs.random()
        return "$subject $verb $obj."
    }
}

object SentenceFactory{
    fun createGenerator(type: GeneratorType): SentenceGenerator {
        return when(type){
            GeneratorType.SIMPLE -> SimpleSentenceGenerator()
            GeneratorType.ADVANCE -> AdvanceSentenceGenerator()
        }
    }
}

fun main(){
    val simpleSentence = SentenceFactory
        .createGenerator(GeneratorType.SIMPLE)
        .generateSentence()
    println(simpleSentence)
    val advanceSentence = SentenceFactory
        .createGenerator(GeneratorType.ADVANCE)
        .generateSentence()
    println(advanceSentence)
}

