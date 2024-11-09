package store.model

data class ReceiptResult(
    val orders: List<Order>,
    val presents: List<Order>,
    val membershipDiscount: Int
)

class Receipt {
    fun calculate(stock: Stock, customer: Customer, membership: Boolean): ReceiptResult {
        return ReceiptResult(customer.getOrder(),calculatePresents(stock,customer),)
    }

    private fun calculatePresents(stock: Stock, customer: Customer): List<Order> {
        val promotion = Promotion()
        val presents = mutableListOf<Order>()
        for (order in customer.getOrder()){
            for (item in stock.getSelectedStock(order)) {
                val selectedEvent = promotion.getEvents().find { it.name == item.discount }
                val whenBuy = selectedEvent?.buy ?: continue
                val get = selectedEvent.get
                presents.add(Order(order.name,item.count / (whenBuy + get)))
            }
        }
        return presents
    }
}