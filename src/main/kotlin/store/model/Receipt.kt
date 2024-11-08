package store.model

data class ReceiptResult(
    val name: String,
    var present: Int,
    var general: Int,
    var totalPrice: Int,
    var discountedPrice: Int,
)

class Receipt {
    fun calculate(stock: Stock, customer: Customer, membership: Boolean) {
        for (order in customer.getOrder()) {
        }
    }
}