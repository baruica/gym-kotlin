package gym.plans.useCases

import Id
import gym.plans.domain.Price
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CreateNewPlanTest : StringSpec({

    "handle" {
        val repository = InMemoryPlanRepository()
        val newPlanId = Id(repository.nextId())

        val tested = CreateNewPlan.Handler(repository)

        val newPlan = tested(
            CreateNewPlan(
                newPlanId,
                300,
                1
            )
        )

        newPlan.id shouldBe newPlanId
        newPlan.price shouldBe Price(300)
    }
})
