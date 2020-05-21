package gym.plans.use_cases

import gym.plans.domain.Plan
import gym.plans.domain.PlanPriceChanged
import gym.plans.infrastructure.PlanInMemoryRepository
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ChangePlanPriceTest {

    @Test
    fun handle() {
        val planRepository = PlanInMemoryRepository()
        val planId = planRepository.nextId()

        planRepository.store(
            Plan(planId, 450, 12)
        )

        val tested = ChangePlanPrice(planRepository)

        val events = tested.handle(
            ChangePriceOfPlanCommand(planId.toString(), 400)
        )

        assertEquals(
            events.last(),
            PlanPriceChanged(planId.toString(), 450, 400)
        )
    }
}
