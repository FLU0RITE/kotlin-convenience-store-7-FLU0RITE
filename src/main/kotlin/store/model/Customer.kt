package store.model

import store.util.Constants

class Customer {
    private var order = mutableMapOf<String, Int>()

    fun setOrder(line: String) {
        val separatedLine = line.replace(Constants.OPEN_SQUARE_BRACKET, Constants.EMPTY_SPACE).replace(Constants.CLOSED_SQUARE_BRACKET, Constants.EMPTY_SPACE).split(Constants.COMMA)
        for (itemAndCount in separatedLine) {
            val itemAndCountSeparated = itemAndCount.split(Constants.DASH)
            val item = itemAndCountSeparated[0]
            val count = itemAndCountSeparated[1]
            this.order[item] = count.toInt()
        }
    }

    fun getOrder(): MutableMap<String, Int> {
        return order
    }
}