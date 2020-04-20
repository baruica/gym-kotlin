package gym.plans.use_cases

import gym.plans.domain.Plan.YearlyPlan
import gym.plans.domain.Price
import gym.plans.infrastructure.PlanInMemoryRepository
import org.junit.Test
import kotlin.test.assertEquals

class ChangePlanPriceTest {

    @Test
    fun handle() {
        val planRepository = PlanInMemoryRepository()
        val planId = planRepository.nextId()

        planRepository.store(
            YearlyPlan(planId, 450)
        )

        val tested = ChangePlanPrice(planRepository)

        val event = tested.handle(
            ChangePriceOfPlanCommand(planId, 400)
        )

        assertEquals(Price(400), event.plan.price)
    }
}
