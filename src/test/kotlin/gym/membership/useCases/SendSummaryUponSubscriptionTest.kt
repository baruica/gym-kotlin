package gym.membership.useCases

import gym.membership.domain.EmailAddress
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.booleans.shouldBeTrue

internal class SendSummaryUponSubscriptionTest : AnnotationSpec() {

    @Test
    fun handle() {

        val emailAddress = "luke@gmail.com"
        val startDate = "2018-06-05"
        val endDate = "2018-07-05"
        val price = 250

        val mailer = InMemoryMailer()

        val tested = SendSummaryUponSubscription(mailer)

        tested.handle(
            SendSummaryUponSubscriptionCommand(
                emailAddress,
                startDate,
                endDate,
                price
            )
        )

        mailer.subscriptionSummaryEmailWasSentTo(
            EmailAddress(emailAddress),
            startDate,
            endDate,
            price
        ).shouldBeTrue()
    }
}
