package store.model

import store.util.ConstantText

data class Order(
    val name: String,
    var count: Int
)

class Customer {
    private var order = mutableListOf<Order>()

    fun makeOrder(line: String) {
        this.order = mutableListOf()
        val regex = ConstantText.REGEX.text.toRegex()
        this.order = regex.findAll(line).map { matchResult ->
            val name = matchResult.groupValues[1]
            val count = matchResult.groupValues[2].toInt()
            Order(name, count)
        }.toMutableList()
        if (this.order.isEmpty()) throw IllegalArgumentException()
    }

    fun setOrder(orders: MutableList<Order>) {
        this.order = orders
    }

    fun answer(answer: String): Boolean {
        when (answer) {
            ConstantText.ANSWER_YES.text -> return true
            ConstantText.ANSWER_NO.text -> return false
        }
        throw IllegalArgumentException()
    }

    fun getOrder(): MutableList<Order> {
        return order
    }
}

