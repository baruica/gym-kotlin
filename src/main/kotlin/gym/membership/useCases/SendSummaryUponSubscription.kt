package gym.membership.useCases

import gym.membership.domain.EmailAddress
import gym.membership.domain.Mailer

data class SendSummaryUponSubscription(
    val email: String,
    val startDate: String,
    val endDate: String,
    val price: Int
) {
    class Handler(
        private val mailer: Mailer,
    ) {
        operator fun invoke(command: SendSummaryUponSubscription) {

            mailer.sendSubscriptionSummary(
                EmailAddress(command.email),
                command.startDate,
                command.endDate,
                command.price
            )
        }
    }
}
