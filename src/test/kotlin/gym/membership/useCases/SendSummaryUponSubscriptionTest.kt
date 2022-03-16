package gym.membership.useCases

import gym.membership.domain.EmailAddress
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.booleans.shouldBeTrue
import java.time.LocalDate

internal class SendSummaryUponSubscriptionTest : AnnotationSpec() {

    @Test
    fun handle() {

        val emailAddress = "luke@gmail.com"
        val startDate = LocalDate.parse("2018-06-05")
        val endDate = LocalDate.parse("2018-07-05")
        val price = 250

        val mailer = InMemoryMailer()

        val tested = SendSummaryUponSubscription.Handler(mailer)

        tested(
            SendSummaryUponSubscription(
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
