package fr.craft.gym.subscribing.use_cases

import fr.craft.gym.plans.infrastructure.PlanInMemoryRepository
import fr.craft.gym.subscribing.domain.ChosenPlan
import fr.craft.gym.subscribing.infrastructure.SubscriptionInMemoryRepository
import org.junit.Test
import java.time.LocalDate
import kotlin.test.assertEquals

class SubscribeToPlanHandlerTest {

    @Test
    fun handle() {
        val subscriptionRepository = SubscriptionInMemoryRepository()
        val planRepository = PlanInMemoryRepository()
        val subscriptionId = subscriptionRepository.nextId()

        val tested = SubscribeToPlanHandler(subscriptionRepository)

        val event = tested.handle(
            SubscribeToPlan(
                ChosenPlan(
                    planRepository.nextId(),
                    500,
                    12,
                    "yearly plan for 500 euros"
                ),
                subscriptionId,
                LocalDate.parse("2018-12-18"),
                false,
                "bob@mail.com"
            )
        )

        assertEquals(subscriptionId, event.subscription.id)
        assertEquals(350, event.subscription.price)
        assertEquals("yearly plan for 500 euros", event.subscription.chosenPlan.description)
    }
}
