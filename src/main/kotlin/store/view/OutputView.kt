package store.view

import store.model.Customer
import store.model.Item
import store.model.Stock
import store.util.Constants
import java.text.DecimalFormat

class OutputView {
    fun printStock(stock: MutableList<Item>) {
        println(Constants.INTRODUCE)
        for (item in stock) {
            println(
                String.format(Constants.STOCK_OUTPUT, item.name, item.value, item.count, item.discount)
                    .replace(Constants.NULL_STRING, Constants.EMPTY_SPACE)
                    .replace(Constants.ZERO_COUNT, Constants.OUT_OF_STOCK)
            )
        }
        println()
    }

}