package store.model

data class ReceiptResult(
    val orders: List<Order>,
    val present: List<Order>,
    val totalPrice: Int,
    val promotionDiscount: Int,
    val membershipDiscount: Int,
)

class Receipt {
    fun calculate(stock: Stock, customer: Customer, membership: Boolean) {
        for (order in customer.getOrder()) {
        }
    }
}