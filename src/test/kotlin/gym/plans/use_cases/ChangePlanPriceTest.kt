package gym.plans.use_cases

import common.InMemoryRepository
import gym.plans.domain.Plan
import gym.plans.domain.PlanId
import gym.plans.domain.PlanPriceChanged
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ChangePlanPriceTest {

    @Test
    fun handle() {
        val repository = InMemoryRepository()
        val planId = repository.nextId()

        repository.store(
            Plan(PlanId(planId), 450, 12)
        )

        val tested = ChangePlanPrice(repository)

        val events = tested.handle(
            ChangePriceOfPlanCommand(planId, 400)
        )

        assertEquals(
            events.last(),
            PlanPriceChanged(planId, 450, 400)
        )
    }
}
