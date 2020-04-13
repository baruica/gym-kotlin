package fr.craft.gym.plans.use_cases

import fr.craft.gym.plans.domain.PlanPeriodicity
import fr.craft.gym.plans.infrastructure.PlanInMemoryRepository
import org.junit.Test
import kotlin.test.assertEquals

class CreateNewPlanHandlerTest {

    @Test
    fun handle() {
        val planRepository = PlanInMemoryRepository()

        val tested = CreateNewPlanHandler(planRepository)

        val planId = planRepository.nextId()

        val event = tested.handle(
            CreateNewPlan(
                planId,
                300,
                PlanPeriodicity.MONTHLY
            )
        )

        assertEquals(planId, event.newPlan.id)
    }
}
