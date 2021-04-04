package gym.subscriptions.useCases

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class SubscribeToPlanTest {

    @Test
    fun handle() {
        val repository = InMemorySubscriptionRepository()
        val subscriptionId = repository.nextId()

        val tested = SubscribeToPlan(repository)

        val subscription = tested.handle(
            SubscribeToPlanCommand(
                subscriptionId,
                500,
                12,
                "2018-12-18",
                false
            )
        )

        assertEquals(subscriptionId, subscription.id.toString())
        assertEquals("2018-12-18", subscription.startDate.toString())
        assertEquals(450.0, subscription.price.amount)
    }
}
