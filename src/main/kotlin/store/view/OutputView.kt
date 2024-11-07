package store.view

import store.util.Constants
import java.text.DecimalFormat

class OutputView {
    fun printStock(stock: MutableList<List<String>>) {
        for (line in stock) {

            println(String.format(Constants.STOCK_OUTPUT,line[0],line[1].toInt(),line[2],line[3]).replace(Constants.NULL_STRING,Constants.EMPTY_SPACE))
        }
        println()
    }
}