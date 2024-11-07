package store.view

import store.util.Constants

class OutputView {
    fun printStock(stock: MutableList<List<String>>) {
        for (line in stock) {
            println(String.format(Constants.STOCK_OUTPUT,line[0],line[1],line[2],line[3]).replace(Constants.NULL_STRING,Constants.EMPTY_SPACE))
        }
        println()
    }
}