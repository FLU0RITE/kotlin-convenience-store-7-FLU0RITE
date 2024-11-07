package store.model

import store.util.Constants
import store.util.ErrorMessage
import java.awt.geom.IllegalPathStateException
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.util.IllformedLocaleException

class Stock {
    private var contents = mutableListOf<List<String>>()
    init {
        val path = Path.of("src/main/resources/products.md")
        try {
            val read = Files.readAllLines(path)
            read.forEach {contents.add(it.split(Constants.COMMA))}
            contents.removeAt(0)
        } catch (e: IllegalPathStateException){
            println(ErrorMessage.ERROR_FILE_LOCATION)
        }
    }

    fun getContents():MutableList<List<String>>{
        return contents
    }
}