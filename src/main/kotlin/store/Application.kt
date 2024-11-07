package store

import store.model.Stock
import store.view.InputView

fun main() {
    InputView().readItem(Stock().getContents())
}
