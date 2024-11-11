package store.model

import store.util.ConstantText
import store.util.ErrorMessage
import java.awt.geom.IllegalPathStateException
import java.nio.file.Files
import java.nio.file.Path
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class Event(
    val name: String,
    val buy: Int,
    val get: Int,
    val startDate: LocalDateTime,
    val endDate: LocalDateTime
)

class Promotion {
    private val events = mutableListOf<Event>()

    fun getEvents(): MutableList<Event> {
        return this.events
    }

    init {
        val path = Path.of(ConstantText.PROMOTION_PATH.text)
        try {
            val read = Files.readAllLines(path)
            read.removeAt(0)
            read.forEach { events.add(makeEvent(it.split(ConstantText.COMMA.text))) }
        } catch (e: IllegalPathStateException) {
            println(ErrorMessage.ERROR_FILE_LOCATION.text)
        }
    }

    private fun makeEvent(line: List<String>): Event {
        val formatter = DateTimeFormatter.ofPattern(ConstantText.DATE_FORMAT.text)
        val startDate = LocalDateTime.parse(line[3] + ConstantText.OCLOCK.text, formatter)
        val endDate = LocalDateTime.parse(line[4] + ConstantText.OCLOCK_MINUS_ONE.text, formatter)
        return Event(line[0], line[1].toInt(), line[2].toInt(), startDate, endDate)
    }

    fun checkStockToGivePromotion(stock: Stock, order: Order): Int {
        val selectedStock = stock.getSelectedStock(order)
        for (item in selectedStock) {
            val selectedEvent = events.find { it.name == item.discount }
            val whenBuy = selectedEvent?.buy ?: continue
            val get = selectedEvent.get
            when {
                order.count % (whenBuy + get) == whenBuy && item.count > order.count -> return 1
                item.count < order.count -> return 2
            }
        }
        return 3
    }

    fun calculateSomethingToCash(stock: Stock, order: Order): Int {
        val selectedStock = stock.getSelectedStock(order)
        for (item in selectedStock) {
            val selectedEvent = events.find { it.name == item.discount }
            val whenBuy = selectedEvent?.buy ?: continue
            val get = selectedEvent.get
            return order.count - (item.count / (whenBuy + get)) * (whenBuy + get)
        }
        throw IllegalArgumentException()
    }

    fun selectPromotion(name: String): Event? {
        return events.find { it.name == name }
    }
}

