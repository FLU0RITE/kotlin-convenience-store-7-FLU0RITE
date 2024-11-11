package store.model

import camp.nextstep.edu.missionutils.DateTimes

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
            totalPrice += price * order.count
            orderAndPrice.add(OrderAndPrice(order, price * order.count))
        }
        var promotionPrice = 0
        for (order in promotionOrder) {
            promotionPrice += order.count * stock.getSelectedStock(order).first().price
        }
        var membershipDiscount = calculateMembership(stock, promotionOrder, customer.getOrder())
        when {
            !membership -> membershipDiscount = 0
            membershipDiscount > 8000 -> membershipDiscount = 8000
        }
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
        }
        for (order in promotionOrder) {
            val item = stock.getItems().find { it.name == order.name }!!

            promotionPrice += item.price * (order.count * (promotion.getEvents()
                .find { it.name == item.discount }!!.buy + 1))
        }
        return (totalPrice - promotionPrice) / 10 * 3
    }

    private fun calculatePresents(stock: Stock, customer: Customer): List<Order> {
        val presents = mutableListOf<Order>()
        for (order in customer.getOrder()) {
            for (item in stock.getSelectedStock(order)) {
                val selectedEvent = promotion.selectPromotion(item.discount)
                val whenBuy = selectedEvent?.buy ?: continue
                val get = selectedEvent.get
                when {
                    DateTimes.now() < selectedEvent.startDate -> break
                    DateTimes.now() > selectedEvent.endDate -> break
                }
                when {
                    order.count <= item.count -> presents.add(Order(order.name, (order.count) / (whenBuy + get)))
                    order.count > item.count -> presents.add(Order(order.name, (item.count) / (whenBuy + get)))
                }

            }
        }
        return presents
    }
}