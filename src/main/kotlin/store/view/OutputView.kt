package store.view

import store.model.Item
import store.model.Receipt
import store.model.ReceiptResult
import store.util.Constants
import store.util.Transformer

class OutputView {
    fun printStock(stock: MutableList<Item>) {
        println(Constants.INTRODUCE)
        for (item in stock) {
            when {
                item.count == 0 -> println(
                    String.format(Constants.STOCK_WITH_ZERO_ITEM_OUTPUT.text, item.name, item.price, item.discount)
                        .replace(Constants.NULL_STRING.text, Constants.EMPTY_SPACE.text)
                )

                item.count > 0 -> println(
                    String.format(Constants.STOCK_OUTPUT.text, item.name, item.price, item.count, item.discount)
                        .replace(Constants.NULL_STRING.text, Constants.EMPTY_SPACE.text)
                )
            }
            if (stock.filter { it.name == item.name }.size == 1 && stock.find { it.name == item.name }!!.discount != Constants.NULL_STRING.text) {
                println(
                    String.format(Constants.STOCK_WITH_ZERO_ITEM_OUTPUT_NO_DISCOUNT.text, item.name, item.price)
                        .replace(Constants.NULL_STRING.text, Constants.EMPTY_SPACE.text)
                )
            }
        }
        println()
    }

    fun printReceipt(receipt: ReceiptResult) {
        println(Constants.DOUBLE_DASH_WITH_CONVENIENCE_STORE.text)
        println(Constants.NAME_COUNT_PRICE.text)
        for (order in receipt.orders) {
            println(
                String.format(
                    Constants.RECEIPT_FORMAT_WITH_COUNT.text,
                    Transformer().formatWithPadding(order.order.name, 20),
                    order.order.count,
                    order.price
                )
            )
        }
        println(Constants.DOUBLE_DASH_WITH_GIFT.text)
        for (present in receipt.presents) {
            println(
                String.format(
                    Constants.RECEIPT_FORMAT_PRESENTS.text,
                    Transformer().formatWithPadding(present.name, 20),
                    present.count
                )
            )
        }
        println(Constants.DOUBLE_DASHES.text)
        println(
            String.format(
                Constants.RECEIPT_FORMAT_TOTAL_PRICE.text,
                receipt.totalCountAndPrice.count,
                receipt.totalCountAndPrice.price
            )
        )
        println(String.format(Constants.RECEIPT_FORMAT_PROMOTION_PRICE.text, -receipt.promotionDiscount))
        println(String.format(Constants.RECEIPT_FORMAT_MEMBERSHIP_PRICE.text, -receipt.membershipDiscount))
        println(String.format(Constants.RECEIPT_FORMAT_RESULT_PRICE.text, receipt.resultPrice))
        println()
    }


}