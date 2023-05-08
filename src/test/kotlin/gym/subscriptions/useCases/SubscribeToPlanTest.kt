package gym.subscriptions.useCases

import Id
import gym.subscriptions.domain.Price
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate

class SubscribeToPlanTest : AnnotationSpec() {

    @Test
    fun handle() {
        val repository = InMemorySubscriptionRepository()
        val subscriptionId = Id(repository.nextId())

        val tested = SubscribeToPlan.Handler(repository)

        val subscription = tested(
            SubscribeToPlan(
                subscriptionId,
                500,
                12,
                LocalDate.parse("2018-12-18"),
                false
            )
        )

        subscription.id shouldBe subscriptionId
        subscription.startDate shouldBe LocalDate.parse("2018-12-18")
        subscription.price shouldBe Price(450)
    }
}
