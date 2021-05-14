package gym.plans.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe

class PlanTest : AnnotationSpec() {

    @Test
    fun `its duration cannot be anything but 1 month or 12 months`() {
        shouldThrow<IllegalArgumentException> {
            Plan.new("plan abc", 400, 4)
        }
    }

    @Test
    fun `its price cannot be negative`() {
        shouldThrow<IllegalArgumentException> {
            Plan.new("plan abc", -10, 1)
        }
    }

    @Test
    fun `can change its price`() {
        val tested = Plan.new("plan abc", 400, 1)
        tested.changePrice(500)

        tested.price shouldBe Price(500)
    }
}
