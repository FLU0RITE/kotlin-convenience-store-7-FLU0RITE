package store.model

data class ReceiptResult(
    val orders: List<OrderAndPrice>,
    val presents: List<Order>,
    val totalCountAndPrice: CountAndPrice,
    val promotionDiscount: Int,
    val membershipDiscount: Int,
    val resultPrice: Int,
)

data class OrderAndPrice(
    val order: Order,
    val price: Int,
)

data class CountAndPrice(
    val count: Int,
    val price: Int,
)

class Receipt {
    private val promotion = Promotion()
    private lateinit var receipt: ReceiptResult

    fun getReceipt(): ReceiptResult {
        return receipt
    }

    fun calculate(stock: Stock, customer: Customer, membership: Boolean) {
        val promotionOrder = calculatePresents(stock, customer)
        val orderAndPrice = mutableListOf<OrderAndPrice>()
        var totalCount = 0
        var totalPrice = 0
        for (order in customer.getOrder()) {
            val price = stock.getItems().find { order.name == it.name }!!.price
            totalCount += order.count
            totalPrice += price
            orderAndPrice.add(OrderAndPrice(order, price * order.count))
        }
        var promotionPrice = 0
        for (order in promotionOrder) {
            promotionPrice += order.count * stock.getSelectedStock(order).first().price
        }
        val membershipDiscount = calculateMembership(stock, promotionOrder, customer.getOrder())
        receipt = ReceiptResult(
            orderAndPrice,
            promotionOrder,
            CountAndPrice(totalCount, totalPrice),
            promotionPrice,
            membershipDiscount,
            totalPrice - promotionPrice - membershipDiscount,
        )
    }


    private fun calculateMembership(stock: Stock, promotionOrder: List<Order>, originalOrder: List<Order>): Int {
        var promotionPrice = 0
        var totalPrice = 0
        for (order in originalOrder) {
            totalPrice += stock.getItems().find { it.name == order.name }!!.price * order.count
            promotionOrder.find { order.name == it.name }.let {
                promotionPrice += stock.getItems().find { it.name == order.name }!!.price * order.count
            }
        }
        return (totalPrice - promotionPrice) / 10 * 3
    }

    private fun calculatePresents(stock: Stock, customer: Customer): List<Order> {
        val presents = mutableListOf<Order>()
        for (order in customer.getOrder()) {
            for (item in stock.getSelectedStock(order)) {
                val selectedEvent = promotion.getEvents().find { it.name == item.discount }
                val whenBuy = selectedEvent?.buy ?: continue
                val get = selectedEvent.get
                presents.add(Order(order.name, item.count / (whenBuy + get)))
            }
        }
        return presents
    }
}