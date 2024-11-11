package store.model

import store.util.ConstantText
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
        val path = Path.of(ConstantText.STOCK_PATH.text)
        try {
            val read = Files.readAllLines(path)
            read.removeAt(0)
            read.forEach { items.add(makeItem(it.split(ConstantText.COMMA.text))) }
        } catch (e: IllegalPathStateException) {
            println(ErrorMessage.ERROR_FILE_LOCATION.text)
        }
    }

    fun updateStock(orders: List<Order>) {
        orders.forEach { order ->
            updateStockForOrder(order)
        }
    }

    private fun updateStockForOrder(order: Order) {
        var remainingCount = order.count
        val itemsWithDiscount = items.filter { it.name == order.name && it.discount != ConstantText.NULL_STRING.text }
        val itemsWithoutDiscount = items.filter { it.name == order.name && it.discount == ConstantText.NULL_STRING.text }

        remainingCount = updateItemsCount(itemsWithDiscount, remainingCount)
        if (remainingCount > 0) {
            updateItemsCount(itemsWithoutDiscount, remainingCount)
        }
    }

    private fun updateItemsCount(items: List<Item>, count: Int): Int {
        var remainingCount = count
        for (item in items) {
            when {
                item.count >= remainingCount -> {
                    item.count -= remainingCount
                    return 0
                }
                item.count < remainingCount -> {
                    remainingCount -= item.count
                    item.count = 0
                }
            }
        }
        return remainingCount
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
            if(selectedStock.isEmpty()){
                println(ErrorMessage.ERROR_NO_STOCK.text)
                throw IllegalArgumentException()
            }
        }
        for (order in orders) {
            val selectedStock = getSelectedStock(order)
            if (selectedStock.sumOf { it.count } < order.count) {
                println(ErrorMessage.ERROR_OVER_STOCK.text)
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

