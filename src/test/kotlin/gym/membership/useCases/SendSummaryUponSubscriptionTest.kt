package gym.membership.useCases

import gym.membership.domain.EmailAddress
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

internal class SendSummaryUponSubscriptionTest {

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

        assertTrue(
            mailer.subscriptionSummaryEmailWasSentTo(
                EmailAddress(emailAddress),
                startDate,
                endDate,
                price
            )
        )
    }
}
