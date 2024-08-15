interface Character {
    fun useAttack(): Int
    fun useDefense(): Int
    fun useMagic(): Int
    fun showInfo()
}

abstract class CharacterDecorator(private val character: Character) : Character by character

class BasicCharacter : Character {
    override fun useAttack(): Int {
        return 0
    }

    override fun useDefense(): Int {
        return 0
    }

    override fun useMagic(): Int {
        return 0
    }

    override fun showInfo() {
        println("I am basic character")
    }
}

class AttackDecorator(private val character: Character) : CharacterDecorator(character) {
    override fun useAttack(): Int {
        return character.useAttack() + 10
    }

    override fun showInfo() {
        if(character is BasicCharacter){
            println("I am character with increase attack")
        } else {
            character.showInfo()
            println("I am character with increase attack")
        }
    }
}

class DefenseDecorator(private val character: Character) : CharacterDecorator(character) {
    override fun useDefense(): Int {
        return character.useDefense() + 10
    }

    override fun showInfo() {
        if(character is BasicCharacter){
            println("I am character with increase defense")
        } else {
            character.showInfo()
            println("I am character with increase defense")
        }
    }
}

class MagicDecorator(private val character: Character) : CharacterDecorator(character) {
    override fun useMagic(): Int {
        return character.useMagic() + 10
    }

    override fun showInfo() {
        if(character is BasicCharacter){
            println("I am character with increase magic")
        } else {
            character.showInfo()
            println("I am character with increase magic")
        }
    }
}

fun main(){
    val simpleCharacter: Character = BasicCharacter()
    simpleCharacter.showInfo()
    val magicCharacter: Character = MagicDecorator(BasicCharacter())
    magicCharacter.showInfo()
    val attackCharacter: Character = AttackDecorator(BasicCharacter())
    attackCharacter.showInfo()
    val defenseCharacter: Character = DefenseDecorator(BasicCharacter())
    defenseCharacter.showInfo()
    println("***************************************************************")
    val bossCharacter: Character = MagicDecorator(DefenseDecorator(AttackDecorator(BasicCharacter())))
    bossCharacter.showInfo()


}


