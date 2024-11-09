package store.view

import store.model.Item
import store.model.Receipt
import store.model.ReceiptResult
import store.util.Constants

class OutputView {
    fun printStock(stock: MutableList<Item>) {
        println(Constants.INTRODUCE)
        for (item in stock) {
            println(
                String.format(Constants.STOCK_OUTPUT, item.name, item.price, item.count, item.discount)
                    .replace(Constants.NULL_STRING, Constants.EMPTY_SPACE)
                    .replace(Constants.ZERO_COUNT, Constants.OUT_OF_STOCK)
            )
        }
        println()
    }

    fun printReceipt(receipt: ReceiptResult){
        println(Constants.DOUBLE_DASH_WITH_CONVENIENCE_STORE)
        println(Constants.NAME_COUNT_PRICE)
        for (order in receipt.orders){
            println(String.format(Constants.RECEIPT_FORMAT_WITH_COUNT,order.order.name,order.order.count,order.price))
        }
        println(Constants.DOUBLE_DASH_WITH_GIFT)
        for (present in receipt.presents){
            println(String.format(Constants.RECEIPT_FORMAT_PRESENTS,present.name,present.count))
        }
        println(Constants.DOUBLE_DASHES)
        println(String.format(Constants.RECEIPT_FORMAT_TOTAL_PRICE,receipt.totalCountAndPrice.count,receipt.totalCountAndPrice.price))
        println(String.format(Constants.RECEIPT_FORMAT_PROMOTION_PRICE,receipt.promotionDiscount))
        println(String.format(Constants.RECEIPT_FORMAT_MEMBERSHIP_PRICE,receipt.membershipDiscount))
        println(String.format(Constants.RECEIPT_FORMAT_RESULT_PRICE,receipt.resultPrice))
    }

}