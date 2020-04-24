package gym.subscriptions.use_cases

import gym.subscriptions.domain.SubscriptionId
import gym.subscriptions.infrastructure.SubscriptionInMemoryRepository
import org.junit.Test
import kotlin.test.assertEquals

class SubscribeToPlanTest {

    @Test
    fun handle() {
        val subscriptionRepository = SubscriptionInMemoryRepository()

        val tested = SubscribeToPlan(subscriptionRepository)

        val events = tested.handle(
            SubscribeToPlanCommand(
                "planId abc",
                500,
                12,
                "2018-12-18",
                false,
                "bob@mail.com"
            )
        )

        assertEquals(
            350,
            subscriptionRepository.get(SubscriptionId(events.last().aggregateId.toString())).price
        )
    }
}
