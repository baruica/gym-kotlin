package fr.craft.gym.subscriptions.use_cases

import fr.craft.gym.membership.domain.EmailAddress
import fr.craft.gym.plans.infrastructure.PlanInMemoryRepository
import fr.craft.gym.subscriptions.domain.ChosenPlan
import fr.craft.gym.subscriptions.infrastructure.SubscriptionInMemoryRepository
import org.junit.Test
import java.time.LocalDate
import kotlin.test.assertEquals

class SubscribeToPlanTest {

    @Test
    fun handle() {
        val subscriptionRepository = SubscriptionInMemoryRepository()
        val planRepository = PlanInMemoryRepository()
        val subscriptionId = subscriptionRepository.nextId()

        val tested = SubscribeToPlan(subscriptionRepository)

        val event = tested.handle(
            SubscribeToPlanCommand(
                ChosenPlan(
                    planRepository.nextId(),
                    500,
                    12,
                    "yearly plan for 500 euros"
                ),
                subscriptionId,
                LocalDate.parse("2018-12-18"),
                false,
                EmailAddress("bob@mail.com")
            )
        )

        assertEquals(subscriptionId, event.subscriptionId)
        assertEquals(350, subscriptionRepository.get(subscriptionId).price)
        assertEquals("yearly plan for 500 euros", subscriptionRepository.get(subscriptionId).chosenPlan.description)
    }
}
