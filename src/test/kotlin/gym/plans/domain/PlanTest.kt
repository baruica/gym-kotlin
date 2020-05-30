package gym.plans.domain

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class PlanTest {

    @Test
    fun `its duration cannot be anything but 1 month or 12 months`() {
        assertFailsWith<IllegalArgumentException> {
            Plan.new(PlanId("abc"), 400, 4)
        }
    }

    @Test
    fun `its price cannot be negative`() {
        assertFailsWith<IllegalArgumentException> {
            Plan.new(PlanId("abc"), -10, 1)
        }
    }

    @Test
    fun `can change its price`() {
        val tested = Plan.new(PlanId("abc"), 400, 1)
        tested.changePrice(500)

        assertEquals(Price(500), tested.price)
    }
}
