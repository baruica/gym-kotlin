package gym.subscriptions.use_cases

import gym.subscriptions.domain.SubscriptionEvent.NewSubscription
import gym.subscriptions.infrastructure.SubscriptionInMemoryRepository
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class SubscribeToPlanTest {

    @Test
    fun handle() {
        val subscriptionRepository = SubscriptionInMemoryRepository()

        val tested = SubscribeToPlan(subscriptionRepository)

        val events = tested.handle(
            SubscribeToPlanCommand(
                500,
                12,
                "2018-12-18",
                false,
                "bob@mail.com"
            )
        )

        assertEquals(
            events.last(),
            NewSubscription(
                events.last().aggregateId,
                "2018-12-18",
                "bob@mail.com"
            )
        )
    }
}
