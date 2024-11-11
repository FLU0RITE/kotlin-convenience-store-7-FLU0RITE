package store.util

import java.text.DecimalFormat

enum class Constants(val text:String){
    INTRODUCE("안녕하세요. W편의점입니다.\n" + "현재 보유하고 있는 상품입니다.\n"),
    QUESTION_INPUT_ITEM("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])"),
    QUESTION_MEMBERSHIP_DISCOUNT("멤버십 할인을 받으시겠습니까? (Y/N)"),
    QUESTION_RETRY("감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)"),
    DOUBLE_DASH_WITH_CONVENIENCE_STORE("==============W 편의점================"),
    DOUBLE_DASH_WITH_GIFT("=============증\t\t 정==============="),
    DOUBLE_DASHES("======================================"),
    NAME_COUNT_PRICE(String.format("%-16s %-5s %-10s", "상품명", "수량", "금액")),
    RECEIPT_FORMAT_WITH_COUNT("%s%-7d %-,10d"),
    RECEIPT_FORMAT_PRESENTS("%s%-5d"),
    RECEIPT_FORMAT_TOTAL_PRICE("총구매액\t\t\t%-5d   %,d"),
    RECEIPT_FORMAT_PROMOTION_PRICE("행사할인\t\t\t\t\t%,d"),
    RECEIPT_FORMAT_MEMBERSHIP_PRICE("멤버십할인\t\t\t\t\t%,d"),
    RECEIPT_FORMAT_RESULT_PRICE("내실돈\t\t\t\t\t\t%,d"),
    QUESTION_ADD_PROMOTION("현재 %s은(는) 1개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)"),
    QUESTION_NO_PROMOTION("현재 %s %d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)"),
    STOCK_OUTPUT("- %s %,d원 %d개 %s"),
    STOCK_WITH_ZERO_ITEM_OUTPUT("- %s %,d원 재고 없음 %s"),
    STOCK_WITH_ZERO_ITEM_OUTPUT_NO_DISCOUNT("- %s %,d원 재고 없음"),
    NULL_STRING("null"),
    EMPTY_SPACE(""),
    COMMA(","),
    ANSWER_YES("Y"),
    ANSWER_NO("N"),
    OCLOCK(" 00:00:00"),
    OCLOCK_MINUS_ONE(" 23:59:59"),
}