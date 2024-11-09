package store.model

data class ReceiptResult(
    val orders: List<Order>,
    val presents: List<Order>,
    val membershipDiscount: Int,
)

class Receipt {
    private val promotion = Promotion()
    fun calculate(stock: Stock, customer: Customer, membership: Boolean): ReceiptResult {
        val promotionOrder = calculatePresents(stock, customer)
        val orders = customer.getOrder()
        return ReceiptResult(
            orders,
            promotionOrder,
            calculateMembership(stock, promotionOrder, orders)
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