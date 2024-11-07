package store.model.discount

import camp.nextstep.edu.missionutils.DateTimes
import store.model.Item
import store.model.Order
import store.util.Constants
import store.util.ErrorMessage
import java.awt.geom.IllegalPathStateException
import java.nio.ByteOrder
import java.nio.file.Files
import java.nio.file.Path
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date

data class event(
    val name: String,
    val buy: Int,
    val get: Int,
    val startDate: LocalDate,
    val endDate: LocalDate
)

class Promotion {
    private val events = mutableListOf<event>()

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

    private fun makeEvent(line: List<String>): event {
        val formatter = DateTimeFormatter.ofPattern("yyyy-dd-mm")
        val startDate = LocalDate.parse(line[3], formatter)
        val endDate = LocalDate.parse(line[4], formatter)
        return event(line[0], line[1].toInt(), line[2].toInt(), startDate, endDate)
    }

    fun applyPromotion(selectedStock: List<Item>, order: Order) {
        for (item in selectedStock) {

        }
    }
}

