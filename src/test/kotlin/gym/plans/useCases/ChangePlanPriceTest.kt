package gym.plans.useCases

import gym.plans.domain.Plan
import gym.plans.domain.Price
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe

class ChangePlanPriceTest : AnnotationSpec() {

    @Test
    fun handle() {
        val repository = InMemoryPlanRepository()
        val planId = repository.nextId()

        repository.store(
            Plan.new(planId, 450, 12)
        )

        val tested = ChangePlanPrice.Handler(repository)

        val plan = tested(
            ChangePlanPrice(planId, 400)
        )

        plan.price shouldBe Price(400)
    }
}
