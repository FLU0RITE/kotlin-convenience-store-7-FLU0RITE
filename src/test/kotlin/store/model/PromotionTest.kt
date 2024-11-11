import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import store.model.*

class PromotionTest {

    @Test
    fun 이벤트_가져오기() {
        val promotion = Promotion()
        assertNotNull(promotion.getEvents())
    }

    @Test
    fun 이벤트_검색_성공() {
        val promotion = Promotion()
        val event = promotion.selectPromotion("MD추천상품")
        assertNotNull(event)
        assertEquals("MD추천상품", event?.name)
    }

    @Test
    fun 이벤트_검색_실패() {
        val promotion = Promotion()
        val event = promotion.selectPromotion("없는이벤트")
        assertNull(event)
    }

    @Test
    fun 프로모션_적용_성공() {
        val promotion = Promotion()
        val stock = Stock()
        val order = Order("콜라", 5)

        val result = promotion.checkStockToGivePromotion(stock, order)
        assertEquals(1, result)
    }

    @Test
    fun 프로모션_적용_실패_재고_부족() {
        val promotion = Promotion()
        val stock = Stock()
        val order = Order("콜라", 15)

        val result = promotion.checkStockToGivePromotion(stock, order)
        assertEquals(2, result)
    }

    @Test
    fun 프로모션_적용_불가능() {
        val promotion = Promotion()
        val stock = Stock()
        val order = Order("콜라", 5)

        val result = promotion.checkStockToGivePromotion(stock, order)
        assertEquals(1, result)
    }

}
