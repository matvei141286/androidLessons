data class Computer(
    val processor: String,
    val motherBoard: String,
    val ram: String,
    val ssd: String
    )

class ComputerBuilder{
    private var processor = "Intel"
    private var motherBoard = "Asus"
    private var ram = "Samsung"
    private var ssd = "Dexp"

    fun setProcessor(processor: String) = apply { this.processor = processor }
    fun setMotherBoard(motherBoard: String) = apply { this.motherBoard = motherBoard }
    fun setRam(ram: String) = apply { this.ram = ram }
    fun setSsd(ssd: String) = apply { this.ssd = ssd }

    fun build(): Computer {
        return  Computer(processor, motherBoard, ram, ssd)
    }

}

fun main() {
    val computer = ComputerBuilder()
        .setProcessor("AMD")
        .setMotherBoard("Gigabyte")
        .setRam("Kingston")
        .setSsd("Transcend")
        .build()
    println(computer)
}




