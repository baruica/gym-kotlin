package gym.subscriptions.useCases

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe

class SubscribeToPlanTest : AnnotationSpec() {

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

        subscription.id.toString() shouldBe subscriptionId
        subscription.startDate.toString() shouldBe "2018-12-18"
        subscription.price.amount shouldBe 450.0
    }
}
