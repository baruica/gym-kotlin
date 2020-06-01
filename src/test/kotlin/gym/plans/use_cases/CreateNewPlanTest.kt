package gym.plans.use_cases

import gym.plans.infrastructure.PlanInMemoryRepository
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CreateNewPlanTest {

    @Test
    fun handle() {
        val repository = PlanInMemoryRepository()
        val newPlanId = repository.nextId()

        val tested = CreateNewPlan(repository)

        val newPlan = tested.handle(
            CreateNewPlanCommand(
                newPlanId,
                300,
                1
            )
        )

        assertEquals(newPlan.id.toString(), newPlanId)
        assertEquals(newPlan.price.amount, 300)
    }
}
