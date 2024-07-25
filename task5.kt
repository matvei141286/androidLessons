abstract class Vehicle(val brand: String) {
    abstract fun startEngine()
    abstract fun move()
    abstract fun getDescription(): String
}

class Car(brand: String) : Vehicle(brand) {
    override fun startEngine() {
        println("Запущен двигатель автомобиля")
    }

    override fun move() {
        println("Автомобиль поехал")
    }

    override fun getDescription(): String {
        return "втомобиль"
    }
}

class Airplane(brand: String) : Vehicle(brand) {
    override fun startEngine() {
        println("Запущен двигатель самолета")
    }

    override fun move() {
        println("Самолет полетел")
    }

    override fun getDescription(): String {
        return "самолет"
    }
}

class Ship(brand: String) : Vehicle(brand) {
    override fun startEngine() {
        println("Запущен двигатель корабля")
    }

    override fun move() {
        println("Корабль поплыл")
    }

    override fun getDescription(): String {
        return "корабль"
    }
}

class Mechanic {
    fun serviceVehicle(vehicle: Vehicle) {
        println("Тип транспортного средства: ${vehicle.getDescription()}  марка: ${vehicle.brand}")
    }
}

fun main() {
    val arrayOfVehicles = arrayOf<Vehicle>(Car("BMW X5"), Airplane("МИГ 29"), Ship("ракетный крейсер Варяг"))
    val mechanic = Mechanic()
    for (vehicle in arrayOfVehicles) {
        mechanic.serviceVehicle(vehicle)
        vehicle.startEngine()
        vehicle.move()
        println("*************************************************************************")
    }
}