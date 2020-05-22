package gym.plans.use_cases

import gym.plans.domain.Plan
import gym.plans.domain.PlanId
import gym.plans.domain.PlanPriceChanged
import gym.plans.infrastructure.PlanInMemoryRepository
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ChangePlanPriceTest {

    @Test
    fun handle() {
        val repository = PlanInMemoryRepository()
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
