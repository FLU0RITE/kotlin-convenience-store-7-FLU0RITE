package store.view

import store.model.Item
import store.model.ReceiptResult
import store.util.ConstantText
import store.util.Transformer

class OutputView {
    fun printStock(stock: MutableList<Item>) {
        println(ConstantText.INTRODUCE.text)
        for (item in stock) {
            printStockItem(item)
            if (stock.filter { it.name == item.name }.size == 1 && stock.find { it.name == item.name }!!.discount != ConstantText.NULL_STRING.text) {
                println(
                    String.format(ConstantText.STOCK_WITH_ZERO_ITEM_OUTPUT_NO_DISCOUNT.text, item.name, item.price)
                        .replace(ConstantText.NULL_STRING.text, ConstantText.EMPTY_SPACE.text)
                )
            }
        }
        println()
    }

    private fun printStockItem(item: Item) {
        when {
            item.count == 0 -> println(
                String.format(ConstantText.STOCK_WITH_ZERO_ITEM_OUTPUT.text, item.name, item.price, item.discount)
                    .replace(ConstantText.NULL_STRING.text, ConstantText.EMPTY_SPACE.text)
            )
            item.count > 0 -> println(
                String.format(ConstantText.STOCK_OUTPUT.text, item.name, item.price, item.count, item.discount)
                    .replace(ConstantText.NULL_STRING.text, ConstantText.EMPTY_SPACE.text)
            )
        }
    }

    fun printReceipt(receipt: ReceiptResult) {
        println(ConstantText.DOUBLE_DASH_WITH_CONVENIENCE_STORE.text)
        println(ConstantText.NAME_COUNT_PRICE.text)
        printReceiptOrders(receipt)
        printReceiptPresents(receipt)
        printReceiptTotals(receipt)
    }

    private fun printReceiptOrders(receipt: ReceiptResult) {
        for (order in receipt.orders) {
            println(
                String.format(
                    ConstantText.RECEIPT_FORMAT_WITH_COUNT.text,
                    Transformer().formatWithPadding(order.order.name, 20),
                    order.order.count,
                    order.price
                )
            )
        }
    }

    private fun printReceiptPresents(receipt: ReceiptResult) {
        println(ConstantText.DOUBLE_DASH_WITH_GIFT.text)
        for (present in receipt.presents) {
            println(
                String.format(
                    ConstantText.RECEIPT_FORMAT_PRESENTS.text,
                    Transformer().formatWithPadding(present.name, 20),
                    present.count
                )
            )
        }
    }

    private fun printReceiptTotals(receipt: ReceiptResult) {
        println(ConstantText.DOUBLE_DASHES.text)
        println(
            String.format(
                ConstantText.RECEIPT_FORMAT_TOTAL_PRICE.text,
                receipt.totalCountAndPrice.count,
                receipt.totalCountAndPrice.price
            )
        )
        println(String.format(ConstantText.RECEIPT_FORMAT_PROMOTION_PRICE.text, -receipt.promotionDiscount))
        println(String.format(ConstantText.RECEIPT_FORMAT_MEMBERSHIP_PRICE.text, -receipt.membershipDiscount))
        println(String.format(ConstantText.RECEIPT_FORMAT_RESULT_PRICE.text, receipt.resultPrice))
        println()
    }



}