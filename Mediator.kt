import kotlin.coroutines.coroutineContext

enum class OrderStatus{
    CREATED, IN_WORK, DELIVERED, CANCELED
}

data class Order(val product: String, val address: String, var status: OrderStatus = OrderStatus.CREATED)


interface OrderMediator {
    fun placeOrder(product: String, address: String, customer: Customer)
    fun deliverOrder(order: Order, customer: Customer)
    fun updateOrderStatus(order: Order, status: OrderStatus)
}

//Реализация Mediator
class OrderMediatorImpl: OrderMediator{
    private val orders = mutableListOf<Order>()
    private val warehouse = Warehouse(this)
    private val courier = Courier(this)

    override fun placeOrder(product: String, address: String, customer: Customer) {
        if (warehouse.checkProductAvailability(product)){
            println("The product $product is in stock")
            customer.receiveOrderStatus(OrderStatus.IN_WORK)
            val order = Order(product, address, OrderStatus.IN_WORK)
            orders.add(order)
            warehouse.prepareItemForDelivery(product)
            deliverOrder(order, customer)
        } else {
            println("The product $product is out of stock")
            val order = Order(product, address, OrderStatus.CANCELED)
            orders.add(order)
            customer.receiveOrderStatus(OrderStatus.CANCELED)
        }
    }

    override fun updateOrderStatus(order: Order, status: OrderStatus) {
        order.status = status
        println("The order status of ${order.product} has been updated: $status")
    }

    override fun deliverOrder(order: Order, customer: Customer) {
        println("The ${order.product} has been given to the courier")
        courier.deliver(order, customer)
    }
}


//Класс покупатель
class Customer(private val mediator: OrderMediator, private val name: String){
    fun createOrder(product: String, address: String) {
        println("$name places an order for $product with delivery to the address: $address")
        mediator.placeOrder(product, address, this)
    }

    fun receiveOrderStatus(status: OrderStatus) {
        println("$name receives an order status update: $status")
    }


}

//Класс склад
class Warehouse(private val mediator: OrderMediator){
    private val stock = mutableMapOf(
        "Iphone" to 10,
        "Samsung" to 5,
        "Xiaomi" to 20
    )

    fun checkProductAvailability(product: String): Boolean {
        return (stock[product] ?: 0) > 0
    }

    fun prepareItemForDelivery(product: String){
        stock[product] = stock[product]?.minus(1) ?: 0
        println("The warehouse transfers $product to the delivery service")
    }

}

//Класс курьер
class Courier(private  val mediator: OrderMediator){
    fun deliver(order: Order, customer: Customer) {
        println("The courier delivers ${order.product} to: ${order.address}")
        mediator.updateOrderStatus(order, OrderStatus.DELIVERED)
        customer.receiveOrderStatus(OrderStatus.DELIVERED)
    }

}

fun main(){
    val mediator = OrderMediatorImpl()
    val customer = Customer(mediator, "Matvey")
    customer.createOrder("Iphone", "Lenina 43")
}