package store.controller

import store.model.*
import store.view.InputView
import store.view.OutputView

class ConvenienceStoreController {
    private val stock = Stock()
    private val customer = Customer()
    private val receipt = Receipt()
    private val membership = Membership()
    private val inputView = InputView()
    private val outputView = OutputView()
    private val promotion = Promotion()
    fun execute() {
        while (true) {
            outputView.printStock(stock.getItems())
            checkStock()
            checkPromotionStock()
        }
    }

    private fun checkPromotionStock() {
        for (order in customer.getOrder()) {
            checkPromotionItem(order)
        }
    }

    private fun checkPromotionItem(order:Order){
        if (promotion.checkBringMoreItem(stock, order)) {
            val answer = try {
                customer.answer(inputView.readAddPromotion(order.name))
            } catch (e: IllegalArgumentException) {
                checkPromotionItem(order)
            }
            when (answer) {
                true -> {
                    val originalOrder = customer.getOrder()
                    originalOrder.find { it.name == order.name }!!.count++
                    customer.setOrder(originalOrder)
                }
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