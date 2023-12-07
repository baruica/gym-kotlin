package gym.plans.useCases

import Id
import gym.plans.domain.Plan
import gym.plans.domain.Price
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class ChangePlanPriceTest : StringSpec({

    "handle" {
        val repository = InMemoryPlanRepository()
        val planId = Id(repository.nextId())

        repository.store(
            Plan.new(planId, 450, 12)
        )

        val tested = ChangePlanPrice.Handler(repository)

        val plan = tested(
            ChangePlanPrice(planId, 400)
        )

        plan.price shouldBe Price(400)
    }
})
