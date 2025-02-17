package store.util

enum class ErrorMessage(val text:String) {
    ERROR_ITEM_INPUT("[ERROR] 올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요."),
    ERROR_NO_STOCK("[ERROR] 존재하지 않는 상품입니다. 다시 입력해 주세요."),
    ERROR_OVER_STOCK("[ERROR] 재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요."),
    ERROR_FILE_LOCATION("[ERROR] 파일 위치 오류"),
}