package gym.plans.useCases

import gym.plans.domain.Price
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CreateNewPlanTest {

    @Test
    fun handle() {
        val repository = InMemoryPlanRepository()
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
