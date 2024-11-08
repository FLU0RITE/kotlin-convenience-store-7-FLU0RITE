package store.model

import store.util.Constants
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

    init {
        val path = Path.of("src/main/resources/promotions.md")
        try {
            val read = Files.readAllLines(path)
            read.removeAt(0)
            read.forEach { events.add(makeEvent(it.split(Constants.COMMA))) }
        } catch (e: IllegalPathStateException) {
            println(ErrorMessage.ERROR_FILE_LOCATION)
        }
    }

    private fun makeEvent(line: List<String>): Event {
        val formatter = DateTimeFormatter.ofPattern("yyyy-dd-mm")
        val startDate = LocalDateTime.parse(line[3], formatter)
        val endDate = LocalDateTime.parse(line[4], formatter)

        return Event(line[0], line[1].toInt(), line[2].toInt(), startDate, endDate)
    }

    fun checkBringMoreItem(stock: Stock, order: Order): Boolean {
        val selectedStock = stock.getItems().filter { it.name == order.name }
        for (item in selectedStock) {
            val selectedEvent = events.find { it.name == item.discount }
            val whenBuy = selectedEvent?.buy ?: continue
            val get = selectedEvent!!.get
            when {
                order.count % whenBuy + get == whenBuy  && item.count > order.count -> return true
            }
        }
        return false
    }

    private fun selectPromotion(name: String): Event? {
        return events.find { it.name == name }
    }
}

