package store.model

import store.util.Constants

data class Order(
    val name: String,
    val count:Int
)

class Customer {
    private var order = mutableListOf<Order>()

    fun setOrder(line: String) {
        val separatedLine = line.replace(Constants.OPEN_SQUARE_BRACKET, Constants.EMPTY_SPACE).replace(Constants.CLOSED_SQUARE_BRACKET, Constants.EMPTY_SPACE).split(Constants.COMMA)
        for (itemAndCount in separatedLine) {
            val itemAndCountSeparated = itemAndCount.split(Constants.DASH)
            val item = itemAndCountSeparated[0]
            val count = itemAndCountSeparated[1]
            this.order.add(Order(item,count.toInt()))
        }
    }

    fun getOrder(): MutableList<Order> {
        return order
    }
}

