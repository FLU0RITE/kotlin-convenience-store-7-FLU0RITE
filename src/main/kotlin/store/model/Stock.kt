package store.model

import store.util.Constants
import store.util.ErrorMessage
import java.awt.geom.IllegalPathStateException
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.util.IllformedLocaleException

class Stock {
    private var contents = mutableListOf<Item>()

    init {
        val path = Path.of("src/main/resources/products.md")
        try {
            val read = Files.readAllLines(path)
            read.removeAt(0)
            read.forEach { contents.add(makeItem(it.split(Constants.COMMA))) }
            contents.removeAt(0)
        } catch (e: IllegalPathStateException) {
            println(ErrorMessage.ERROR_FILE_LOCATION)
        }
    }

    private fun makeItem(line: List<String>): Item {
        return Item(line[0], line[1].toInt(), line[2].toInt(), line[3])
    }

    fun getContents(): MutableList<Item> {
        return contents
    }
}