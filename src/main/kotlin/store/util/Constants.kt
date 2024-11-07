package store.util

import java.text.DecimalFormat

object Constants {
    val INTRODUCE = "안녕하세요. W편의점입니다.\n" +
            "현재 보유하고 있는 상품입니다.\n"
    val QUESTION_INPUT_ITEM = "구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])"
    val QUESTION_MEMBERSHIP_DISCOUNT = "멤버십 할인을 받으시겠습니까? (Y/N)"
    val QUESTION_RETRY = "감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)"
    val DOUBLE_DASH_WITH_CONVENIENCE_STORE = "==============W 편의점================"
    val DOUBLE_DASH_WITH_GIFT = "=============증\t정==============="
    val DOUBLE_DASHES = "===================================="
    val DASH = "-"
    val QUESTION_ADD_PROMOTION = "현재 %s은(는) 1개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)"
    val QUESTION_NO_PROMOTION = "현재 %s %d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)"
    val STOCK_OUTPUT = "- %s %,d원 %s개 %s"
    val NULL_STRING = "null"
    val EMPTY_SPACE = ""
    val COMMA = ","
    val OPEN_SQUARE_BRACKET = "["
    val CLOSED_SQUARE_BRACKET = "]"
}