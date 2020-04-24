package gym.plans.use_cases

import gym.plans.domain.PlanId
import gym.plans.infrastructure.PlanInMemoryRepository
import org.junit.Test
import kotlin.test.assertEquals

class CreateNewPlanTest {

    @Test
    fun handle() {
        val planRepository = PlanInMemoryRepository()

        val tested = CreateNewPlan(planRepository)

        val planId = planRepository.nextId()

        val events = tested.handle(
            CreateNewPlanCommand(
                planId.toString(),
                300,
                1
            )
        )

        assertEquals(
            planId,
            planRepository.get(
                PlanId(events.first().aggregateId.toString())
            ).planId
        )
    }
}
