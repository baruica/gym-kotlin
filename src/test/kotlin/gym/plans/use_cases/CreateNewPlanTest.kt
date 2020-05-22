package gym.plans.use_cases

import gym.plans.domain.NewPlanCreated
import gym.plans.infrastructure.PlanInMemoryRepository
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CreateNewPlanTest {

    @Test
    fun handle() {
        val repository = PlanInMemoryRepository()

        val tested = CreateNewPlan(repository)

        val events = tested.handle(
            CreateNewPlanCommand(300, 1)
        )

        assertEquals(
            events.last(),
            NewPlanCreated(
                events.last().aggregateId,
                300,
                1
            )
        )
    }
}
