package store.model

import store.util.Constants

class Membership {
    fun checkMembership(answer: String): Boolean {
        return when (answer) {
            Constants.ANSWER_YES -> true
            Constants.ANSWER_NO -> false
            else -> {
                throw IllegalArgumentException()
            }
        }
    }
}