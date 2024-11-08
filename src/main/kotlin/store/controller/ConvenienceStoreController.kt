package store.controller

import store.model.Customer
import store.model.Membership
import store.model.Receipt
import store.model.Stock
import store.view.InputView

class ConvenienceStoreController {
    private val stock = Stock()
    private val customer = Customer()
    private val receipt = Receipt()
    val membership = Membership()

    fun execute(){
        customer.setOrder(InputView().readItem(stock.getItems()))
        stock.checkStock(customer.getOrder())
    }
}