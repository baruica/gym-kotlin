package gym.plans.useCases

import gym.plans.domain.PlanId
import gym.plans.domain.Price
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe

class CreateNewPlanTest : AnnotationSpec() {

    @Test
    fun handle() {
        val repository = InMemoryPlanRepository()
        val newPlanId = repository.nextId()

        val tested = CreateNewPlan.Handler(repository)

        val newPlan = tested(
            CreateNewPlan(
                newPlanId,
                300,
                1
            )
        )

        newPlan.id shouldBe PlanId(newPlanId)
        newPlan.price shouldBe Price(300)
    }
}
