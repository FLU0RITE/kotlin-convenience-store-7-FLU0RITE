package store.model

import store.util.Constants

class Membership {
    fun checkMembership(answer: String): Boolean {
        when (answer) {
            Constants.ANSWER_YES -> return true
            Constants.ANSWER_NO -> return false
        }
        throw IllegalArgumentException()
    }
}