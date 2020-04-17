package fr.craft.gym.plans.use_cases

import fr.craft.gym.plans.domain.PlanPeriodicity
import fr.craft.gym.plans.infrastructure.PlanInMemoryRepository
import org.junit.Test
import kotlin.test.assertEquals

class CreateNewPlanTest {

    @Test
    fun handle() {
        val planRepository = PlanInMemoryRepository()

        val tested = CreateNewPlan(planRepository)

        val planId = planRepository.nextId()

        val event = tested.handle(
            CreateNewPlanCommand(
                planId,
                300,
                PlanPeriodicity.MONTHLY
            )
        )

        assertEquals(planId, event.newPlan.id)
    }
}
