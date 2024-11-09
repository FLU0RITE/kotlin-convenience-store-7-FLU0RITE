package store.model

import store.util.Constants
import store.util.ErrorMessage
import java.awt.geom.IllegalPathStateException
import java.nio.file.Files
import java.nio.file.Path

data class Item(
    val name: String,
    val price: Int,
    var count: Int,
    val discount: String
)

class Stock {
    private var items = mutableListOf<Item>()

    init {
        val path = Path.of("src/main/resources/products.md")
        try {
            val read = Files.readAllLines(path)
            read.removeAt(0)
            read.forEach { items.add(makeItem(it.split(Constants.COMMA))) }
        } catch (e: IllegalPathStateException) {
            println(ErrorMessage.ERROR_FILE_LOCATION)
        }
    }

    fun updateStock(orders: List<Order>) {
        for (order in orders) {
            var haveToMinus = order.count
            items.filter { it.name == order.name }.let {
                for (item in it) {
                    if (item.discount != Constants.NULL_STRING){
                        when{
                            item.count >= haveToMinus ->item.count -= haveToMinus
                            item.count < haveToMinus -> {
                                haveToMinus -= item.count
                                item.count = 0
                            }
                        }
                    }
                }
                for (item in it) {
                    if (item.discount == Constants.NULL_STRING){
                        item.count -= haveToMinus
                    }
                }
            }

        }
    }

    private fun makeItem(line: List<String>): Item {
        return Item(line[0], line[1].toInt(), line[2].toInt(), line[3])
    }

    fun setItems(newStock: MutableList<Item>) {
        this.items = newStock
    }

    fun checkStock(orders: List<Order>) {
        for (order in orders) {
            val selectedStock = getSelectedStock(order)
            if (selectedStock.sumOf { it.count } < order.count) {
                println(ErrorMessage.ERROR_OVER_STOCK)
                throw IllegalArgumentException()
            }
        }
    }

    fun getSelectedStock(order: Order): List<Item> {
        return items.filter { it.name == order.name }
    }

    fun getItems(): MutableList<Item> {
        return items
    }
}

