package gym.plans.use_cases

import gym.plans.domain.Plan
import gym.plans.domain.PlanId
import gym.plans.infrastructure.PlanInMemoryRepository
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ChangePlanPriceTest {

    @Test
    fun handle() {
        val repository = PlanInMemoryRepository()
        val planId = repository.nextId()

        repository.store(
            Plan.new(PlanId(planId), 450, 12)
        )

        val tested = ChangePlanPrice(repository)

        val plan = tested.handle(
            ChangePriceOfPlanCommand(planId, 400)
        )

        assertEquals(400, plan.price.amount)
    }
}
