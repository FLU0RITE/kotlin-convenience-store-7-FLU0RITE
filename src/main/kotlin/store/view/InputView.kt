package store.view

import camp.nextstep.edu.missionutils.Console
import store.util.ConstantText


class InputView {
    fun readItem(): String {
        println(ConstantText.QUESTION_INPUT_ITEM.text)
        return Console.readLine()
    }

    fun readMembership(): String {
        println(ConstantText.QUESTION_MEMBERSHIP_DISCOUNT.text)
        return Console.readLine()
    }

    fun readRetry(): String {
        println(ConstantText.QUESTION_RETRY.text)
        return Console.readLine()
    }

    fun readAddPromotion(name: String): String {
        println(String.format(ConstantText.QUESTION_ADD_PROMOTION.text, name))
        return Console.readLine()
    }

    fun readNoPromotion(name: String, count: Int): String {
        println(String.format(ConstantText.QUESTION_NO_PROMOTION.text, name, count))
        return Console.readLine()
    }

}