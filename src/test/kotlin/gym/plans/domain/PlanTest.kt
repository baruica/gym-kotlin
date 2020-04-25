package gym.plans.domain

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class PlanTest {

    @Test
    fun `a plan cannot have a duration other than 1 month or 12 months`() {
        assertFailsWith<IllegalArgumentException> {
            Plan(PlanId("abc"), 400, 4)
        }
    }

    @Test
    fun `must have a valid price`() {
        assertFailsWith<IllegalArgumentException> {
            Plan(PlanId("abc"), -10, 1)
        }
    }

    @Test
    fun `can change its price`() {
        val tested = Plan(PlanId("abc"), 400, 1)
        tested.changePrice(500)

        assertEquals(Price(500), tested.price)
    }
}
