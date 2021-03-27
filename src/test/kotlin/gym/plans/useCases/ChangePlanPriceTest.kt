package gym.plans.useCases

import gym.plans.domain.Plan
import gym.plans.domain.Price
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ChangePlanPriceTest {

    @Test
    fun handle() {
        val repository = InMemoryPlanRepository()
        val planId = repository.nextId()

        repository.store(
            Plan.new(planId, 450, 12)
        )

        val tested = ChangePlanPrice(repository)

        val plan = tested.handle(
            ChangePriceOfPlanCommand(planId, 400)
        )

        assertEquals(Price(400), plan.price)
    }
}
