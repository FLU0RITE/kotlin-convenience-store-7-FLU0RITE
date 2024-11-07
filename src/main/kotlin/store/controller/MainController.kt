package store.controller

import store.model.Customer
import store.model.Stock
import store.view.InputView

class MainController {
    fun execute(){
        val stock = Stock()
        val customer = Customer()
        customer.setOrder(InputView().readItem(stock.getContents()))

    }
}