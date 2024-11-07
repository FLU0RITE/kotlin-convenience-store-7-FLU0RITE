package store.model

import store.model.discount.Promotion

class Receipt {

    fun calculate(stock: Stock, customer: Customer, membership: Boolean): Stock {
        for (order in customer.getOrder()) {
            val selectedStock = stock.getContents().filter { it.name == order.name }
            Promotion().applyPromotion(selectedStock,order)

        }

        return stock
    }
}