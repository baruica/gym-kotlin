package gym.subscriptions.use_cases

import gym.plans.domain.PlanId
import gym.subscriptions.infrastructure.SubscriptionInMemoryRepository
import org.junit.Test
import java.time.LocalDate
import kotlin.test.assertEquals

class SubscribeToPlanTest {

    @Test
    fun handle() {
        val subscriptionRepository = SubscriptionInMemoryRepository()

        val tested = SubscribeToPlan(subscriptionRepository)

        val event = tested.handle(
            SubscribeToPlanCommand(
                PlanId("abc"),
                500,
                12,
                LocalDate.parse("2018-12-18"),
                false,
                "bob@mail.com"
            )
        )

        assertEquals(350, subscriptionRepository.get(event.subscriptionId).price)
    }
}
