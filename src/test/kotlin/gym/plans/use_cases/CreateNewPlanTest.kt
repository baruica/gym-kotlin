package gym.plans.use_cases

import gym.plans.domain.Price
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

        assertEquals(newPlanId, newPlan.id.toString())
        assertEquals(Price(300), newPlan.price)
    }
}
