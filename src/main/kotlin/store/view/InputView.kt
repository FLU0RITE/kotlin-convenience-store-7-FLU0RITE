package store.view

import camp.nextstep.edu.missionutils.Console
import store.model.Item
import store.util.Constants


class InputView {
    fun readItem():String{
        println(Constants.QUESTION_INPUT_ITEM)
        return Console.readLine()
    }

    fun readMembership():String{
        println(Constants.QUESTION_MEMBERSHIP_DISCOUNT)
        return Console.readLine()
    }

    fun readRetry():String{
        println(Constants.QUESTION_RETRY)
        return Console.readLine()
    }

    fun readAddPromotion(name:String):String{
        println(String.format(Constants.QUESTION_ADD_PROMOTION,name))
        return Console.readLine()
    }

    fun readNoPromotion():String{
        println(Constants.QUESTION_NO_PROMOTION)
        return Console.readLine()
    }

}