package store.controller

import store.model.Customer
import store.model.Membership
import store.model.Receipt
import store.model.Stock
import store.view.InputView
import store.view.OutputView

class ConvenienceStoreController {
    private val stock = Stock()
    private val customer = Customer()
    private val receipt = Receipt()
    private val membership = Membership()
    private val inputView = InputView()
    private val outputView = OutputView()
    fun execute(){
        while (true){
            outputView.printStock(stock.getItems())
            checkStock()

        }
    }
    private fun checkPromotionStock(){

    }
    private fun checkStock(){
        try {
            customer.setOrder(InputView().readItem())
            stock.checkStock(customer.getOrder())
        } catch (e: IllegalArgumentException) {
            return checkStock()
        }

    }
}