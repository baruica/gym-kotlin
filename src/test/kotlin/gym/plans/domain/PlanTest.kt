package gym.plans.domain

import Id
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe

class PlanTest : AnnotationSpec() {

    @Test
    fun `its duration cannot be anything but 1 month or 12 months`() {
        shouldThrow<IllegalArgumentException> {
            Plan.new(Id("plan abc"), 400, 4)
        }
    }

    @Test
    fun `its price cannot be negative`() {
        shouldThrow<IllegalArgumentException> {
            Plan.new(Id("plan abc"), -10, 1)
        }
    }

    @Test
    fun `can change its price`() {
        val plan = Plan.new(Id("plan abc"), 400, 1)
        val tested = plan.changePrice(500)

        tested.price shouldBe Price(500)
    }
}
