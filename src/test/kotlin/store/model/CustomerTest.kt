import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import store.model.Customer
import store.model.Order
import store.util.ConstantText

class CustomerTest {

    @Test
    fun `주문 생성 테스트_유효한 입력`() {
        val customer = Customer()
        val line = "[콜라-3],[에너지바-5]"

        customer.makeOrder(line)

        val orders = customer.getOrder()
        assertEquals(2, orders.size)
        assertEquals("콜라", orders[0].name)
        assertEquals(3, orders[0].count)
        assertEquals("에너지바", orders[1].name)
        assertEquals(5, orders[1].count)
    }

    @Test
    fun `주문 생성 테스트_유효하지 않은 입력`() {
        val customer = Customer()
        val line = "잘못된입력"

        assertThrows(IllegalArgumentException::class.java) {
            customer.makeOrder(line)
        }
    }

    @Test
    fun `주문 설정 테스트`() {
        val customer = Customer()
        val orders = mutableListOf(
            Order("사이다", 2),
            Order("에너지바", 5)
        )

        customer.setOrder(orders)

        val result = customer.getOrder()
        assertEquals(2, result.size)
        assertEquals("사이다", result[0].name)
        assertEquals(2, result[0].count)
        assertEquals("에너지바", result[1].name)
        assertEquals(5, result[1].count)
    }

    @Test
    fun `응답 테스트_YES 입력`() {
        val customer = Customer()

        val result = customer.answer(ConstantText.ANSWER_YES.text)

        assertTrue(result)
    }

    @Test
    fun `응답 테스트_NO 입력`() {
        val customer = Customer()

        val result = customer.answer(ConstantText.ANSWER_NO.text)

        assertFalse(result)
    }

    @Test
    fun `응답 테스트_잘못된 입력`() {
        val customer = Customer()

        assertThrows(IllegalArgumentException::class.java) {
            customer.answer("invalid")
        }
    }
}
