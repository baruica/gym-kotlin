package gym.plans.use_cases

import gym.plans.domain.PlanEvent.NewPlanCreated
import gym.plans.infrastructure.PlanInMemoryRepository
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CreateNewPlanTest {

    @Test
    fun handle() {
        val planRepository = PlanInMemoryRepository()

        val tested = CreateNewPlan(planRepository)

        val events = tested.handle(
            CreateNewPlanCommand(300, 1)
        )

        assertEquals(
            events.last(),
            NewPlanCreated(
                events.last().planId,
                300,
                1
            )
        )
    }
}
