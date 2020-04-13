package fr.craft.gym.plans.use_cases

import fr.craft.gym.plans.domain.Plan.YearlyPlan
import fr.craft.gym.plans.domain.PlanId
import fr.craft.gym.plans.domain.Price
import fr.craft.gym.plans.infrastructure.PlanInMemoryRepository
import org.junit.Test
import kotlin.test.assertEquals

class ChangePriceOfPlanHandlerTest {

    @Test
    fun handle() {
        val planId = PlanId("abcdef")

        val planRepository = PlanInMemoryRepository()
        planRepository.store(
            YearlyPlan(
                planId,
                450
            )
        )

        val tested = ChangePriceOfPlanHandler(planRepository)

        val event = tested.handle(
            ChangePriceOfPlan(planId, 400)
        )

        assertEquals(Price(400), event.plan.price)
    }
}
