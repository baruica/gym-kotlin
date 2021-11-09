package gym.membership.useCases

import gym.membership.domain.EmailAddress
import gym.membership.domain.Mailer

data class SendSummaryUponSubscriptionCommand(
    val email: String,
    val startDate: String,
    val endDate: String,
    val price: Int
)

class SendSummaryUponSubscription(
    private val mailer: Mailer,
) {
    operator fun invoke(command: SendSummaryUponSubscriptionCommand) {

        mailer.sendSubscriptionSummary(
            EmailAddress(command.email),
            command.startDate,
            command.endDate,
            command.price
        )
    }
}
