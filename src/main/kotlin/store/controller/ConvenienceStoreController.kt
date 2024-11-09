package store.controller

import store.model.*
import store.view.InputView
import store.view.OutputView

class ConvenienceStoreController {
    private val stock = Stock()
    private val customer = Customer()
    private val receipt = Receipt()
    private val inputView = InputView()
    private val outputView = OutputView()
    private val promotion = Promotion()
    fun execute() {
        while (true) {
            outputView.printStock(stock.getItems())
            checkStock()
            checkPromotionStock()
            receipt.calculate(stock, customer, checkMembership())
        }
    }

    private fun checkMembership(): Boolean {
        val answer = try {
            customer.answer(inputView.readMembership())
        } catch (e: IllegalArgumentException) {
            checkMembership()
        }
        return answer
    }

    private fun checkPromotionStock() {
        for (order in customer.getOrder()) {
            when (promotion.checkStockToGivePromotion(stock, order)) {
                1 -> checkBringItem(order)
                2 -> checkPaySomethingCash(order)
            }
        }
    }

    private fun checkBringItem(order: Order) {
        val answer = try {
            customer.answer(inputView.readAddPromotion(order.name))
        } catch (e: IllegalArgumentException) {
            checkBringItem(order)
        }
        when (answer) {
            true -> {
                val originalOrder = customer.getOrder()
                originalOrder.find { it.name == order.name }!!.count++
                customer.setOrder(originalOrder)
            }
        }
    }

    private fun checkPaySomethingCash(order: Order) {
        val noPromotionCount = promotion.calculateSomethingToCash(stock, order)
        val answer = try {
            customer.answer(inputView.readNoPromotion(order.name, noPromotionCount))
        } catch (e: IllegalArgumentException) {
            checkPaySomethingCash(order)
        }
        when (answer) {
            false -> {
                val originalOrder = customer.getOrder()
                originalOrder.find { it.name == order.name }!!.count -= noPromotionCount
                customer.setOrder(originalOrder)
            }
        }
    }

    private fun checkStock() {
        try {
            customer.makeOrder(inputView.readItem())
            stock.checkStock(customer.getOrder())
        } catch (e: IllegalArgumentException) {
            return checkStock()
        }

    }
}