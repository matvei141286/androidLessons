import java.lang.Exception

sealed class OrderState {
    class Created(): OrderState()
    class Paid(): OrderState()
    class Delivered(): OrderState()
}

class Created {
}

class Delivered {
}

class Paid {
}

data class Order(val id: Int, val items: List<String>, var state: OrderState = OrderState.Created())


fun payOrder(order: Order): Order {
    return if (order.state is OrderState.Created) {
        order.copy(state = OrderState.Paid())
    } else {
        throw Exception("Something went wrong !")
    }
}

fun deliverOrder(order: Order): Order {
    return if (order.state is OrderState.Paid) {
        order.copy(state = OrderState.Delivered())
    } else {
        throw Exception("Something went wrong !")
    }
}

fun printOrderDetails(order: Order) {
    println("Order ID: ${order.id}")
    println("Items: ${order.items.joinToString(", ")}")
    when (order.state) {
        is OrderState.Created -> println("Order Status: Created")
        is OrderState.Paid -> println("Order Status: Paid")
        is OrderState.Delivered -> println("Order Status: Delivered")
    }
    println("*************************")
}

fun main() {
    // Создаем заказ
    val order1 = Order(id = 1, items = listOf("item 1", "item 2"))
    // Печатаем детали заказа
    printOrderDetails(order1)
    // Оплачиваем заказ
    val paidOrder = payOrder(order1)
    printOrderDetails(paidOrder)
    // Доставляем заказ
    val deliveredOrder = deliverOrder(paidOrder)
    printOrderDetails(deliveredOrder)
}