# 기능 구현 목록

1. [x] 입력
   2. [x] 상품, 수량
   3. [x] 프로모션 가능 상품 적게 가져온 경우 추가 여부
   4. [x] 프로모션 재고 부족 정가 결제 여부
   5. [x] 멤버십 할인 적용 여부
   6. [x] 추가 구매 여부
7. [ ] 출력
   8. [x] 환영인사
   9. [x] 상품명, 가격, 프로모션 이름, 재고
   10. [x] 수량 입력 메시지
   11. [x] 프로모션 가능 상품 적게 가져온 경우 추가 여부
   12. [x] 프로모션 재고 부족 정가 결제 여부
   13. [x] 멤버십 할인 적용 여부
   14. [ ] 구매 상품 내역, 증정 상품 내역, 금액 정보
   15. [x] 추가 구매 여부
16. [ ] 재고 계산
    17. [ ] 구매 시 차감
    18. [ ] 정보 제공
19. [ ] 할인
    20. [ ] 프로모션
        21. [x 날짜 확인
        22. [x] N + 1 개 증정
        23. [x] 중복 프로모션 적용하지 않음
        24. [x] 재고 내에서 적용
        25. [ ] 프로모션 재고 우선 차감, 프로모션 재고 부족 시 일반 재고 차감
        26. [x] 적게 가져온 프로모션 적용 상품 가능에 대한 고지
        27. [x] 재고 부족 혜택 못 받는 경우 정가 결제 고지
    28. [ ] 멤버십 할인
        29. [ ] 프로모션 미적용 금액의 30퍼센트 할인
        30. [ ] 프로모션 적용후 남은 금액에 대해 할인
        31. [ ] 최대 한도 8000원

> 구매자의 할인 혜택과 재고 상황을 고려하여 최종 결제 금액을 계산하고 안내하는 결제 시스템을 구현한다.

### 개요
+ 사용자 입력 (가격, 수량)-> 최종 결제 금액 계산
  + 총구매액 = 상품별 가격 x 수량 - 프로모션, 멤버십 할인
+ 영수증 출력
+ 영수증 출력 후 추가 구매 진행 여부 선택
+ 잘못된 값 입력
  + `IllegalArgumentException`를 발생시키고, "[ERROR]"로 시작하는 에러 메시지를 출력 후 그 부분부터 입력을 다시 받는다.
  + `Exception`이 아닌 `IllegalArgumentException`, `IllegalStateException` 등과 같은 명확한 유형을 처리한다.
+ 재고 관리
  + 재고 수량 고려 후 결제 가능 여부 확인
  + 구매 후 재고에서 차감
  + 다음 구매 시 최신 정보 제공
+ 프로모션 할인
  + 금일이 프로모션 기간 내면 할인
  + N개 구매시 1개 무료 증정
  + 한 물품에 한 프로모션
  + 재고 내에서만 적용
  + 프로모션 재고 우선 차감, 프로모션 재고 부족 시 일반 재고 차감
  + 적게 가져온 프로모션 적용 상품 가능에 대한 고지
  + 재고 부족 혜택 못 받는 경우 정가 결제 고지
+ 멤버십 할인
  + 프로모션 미적용 금액의 30퍼센트 할인
  + 프로모션 적용후 남은 금액에 대해 할인
  + 최대 한도 8000원
+ 영수증 출력
  + 구매 상품 내역 : 상품명, 수량, 가격
  + 증정 상품 내역 : 프로모션 무료 제공 목록
  + 금액 정보
    + 총구매액 : 총 수량, 총 금액
    + 행사할인 : 프로모션 할인 금액
    + 멤버십할인 : 멤버십에 추가로 할인된 금액
    + 내실돈 : 최종 결제 금액
+ 입력
  + 상품 목록과 행사 목록을 파일 입출력을 통해 불러온다.
  + `src/main/resources/products.md`과 `src/main/resources/promotions.md` 파일을 이용한다.
  + 두 파일 모두 내용의 형식을 유지한다면 값은 수정할 수 있다.
  + 구매할 상품과 수량을 입력 받는다. 상품명, 수량은 하이픈(-)으로, 개별 상품은 대괄호([])로 묶어 쉼표(,)로 구분한다.
    + [콜라-10],[사이다-3]
  + 프로모션 적용이 가능한 상품에 대해 고객이 해당 수량보다 적게 가져온 경우, 그 수량만큼 추가 여부를 입력받는다.
    + Y: 증정 받을 수 있는 상품을 추가한다.
    + N: 증정 받을 수 있는 상품을 추가하지 않는다.
  + 프로모션 재고가 부족하여 일부 수량을 프로모션 혜택 없이 결제해야 하는 경우, 일부 수량에 대해 정가로 결제할지 여부를 입력받는다.
    + Y: 일부 수량에 대해 정가로 결제한다.
    + N: 정가로 결제해야하는 수량만큼 제외한 후 결제를 진행한다.
  + 멤버십 할인 적용 여부를 입력 받는다.
    + Y: 멤버십 할인을 적용한다.
    + N: 멤버십 할인을 적용하지 않는다.
  + 추가 구매 여부를 입력 받는다.
    + Y: 재고가 업데이트된 상품 목록을 확인 후 추가로 구매를 진행한다.
    + N: 구매를 종료한다.
+ 출력
  + 환영 인사와 함께 상품명, 가격, 프로모션 이름, 재고를 안내한다. 만약 재고가 0개라면 재고 없음을 출력한다.
  + 프로모션 적용이 가능한 상품에 대해 고객이 해당 수량만큼 가져오지 않았을 경우, 혜택에 대한 안내 메시지를 출력한다.
    + 현재 {상품명}은(는) 1개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)
  + 프로모션 재고가 부족하여 일부 수량을 프로모션 혜택 없이 결제해야 하는 경우, 일부 수량에 대해 정가로 결제할지 여부에 대한 안내 메시지를 출력한다.
    + 현재 {상품명} {수량}개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)
  + 멤버십 할인 적용 여부를 확인하기 위해 안내 문구를 출력한다.
    + 멤버십 할인을 받으시겠습니까? (Y/N)
  + 구매 상품 내역, 증정 상품 내역, 금액 정보를 출력한다.
  + 추가 구매 여부를 확인하기 위해 안내 문구를 출력한다.
    + 감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)
  + 사용자가 잘못된 값을 입력했을 때, "[ERROR]"로 시작하는 오류 메시지와 함께 상황에 맞는 안내를 출력한다.
    + 구매할 상품과 수량 형식이 올바르지 않은 경우: [ERROR] 올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.
    + 존재하지 않는 상품을 입력한 경우: [ERROR] 존재하지 않는 상품입니다. 다시 입력해 주세요.
    + 구매 수량이 재고 수량을 초과한 경우: [ERROR] 재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.
    + 기타 잘못된 입력의 경우: [ERROR] 잘못된 입력입니다. 다시 입력해 주세요.





















