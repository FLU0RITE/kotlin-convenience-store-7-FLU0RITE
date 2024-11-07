package store.view

import camp.nextstep.edu.missionutils.Console
import store.util.Constants


class InputView {
    fun readItem(stock:MutableList<List<String>>):String{
        println(Constants.INTRODUCE)
        OutputView().printStock(stock)
        println(Constants.QUESTION_INPUT_ITEM)
        return Console.readLine()
    }

}