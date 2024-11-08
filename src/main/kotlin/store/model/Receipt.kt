package store.model

data class ReceiptResult(
    val name: String,
    var present: Int,
    var general: Int,
    var totalPrice: Int,
    var discountedPrice: Int,
)
class Receipt {

}