package gym.plans.use_cases

import gym.plans.domain.Plan.YearlyPlan
import gym.plans.domain.PlanEvent
import gym.plans.domain.PlanId
import gym.plans.domain.Price
import gym.plans.infrastructure.PlanInMemoryRepository
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ChangePlanPriceTest {

    @Test
    fun handle() {
        val planRepository = PlanInMemoryRepository()
        val planId = planRepository.nextId()

        planRepository.store(
            YearlyPlan(planId.toString(), 450)
        )

        val tested = ChangePlanPrice(planRepository)

        val events = tested.handle(
            ChangePriceOfPlanCommand(planId.toString(), 400)
        )

        val plan = planRepository.get(
            PlanId(events.last().aggregateId)
        )
        assertEquals(Price(400), plan.price)
        assertTrue(events.last() is PlanEvent.PlanPriceChanged)
    }
}
