package store.view

import camp.nextstep.edu.missionutils.Console
import store.util.Constants


class Input {
    fun readItem(stock:String):String{
        println(Constants.INTRODUCE)
        println(stock)
        println(Constants.QUESTION_INPUT_ITEM)
        return Console.readLine()
    }

}