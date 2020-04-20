package gym.plans.use_cases

import gym.plans.domain.PlanPeriodicity
import gym.plans.infrastructure.PlanInMemoryRepository
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
