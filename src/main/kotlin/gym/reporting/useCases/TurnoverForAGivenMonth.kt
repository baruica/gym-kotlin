package gym.reporting.useCases

import gym.reporting.Turnover
import gym.subscriptions.domain.SubscriptionRepository
import java.time.LocalDate

data class TurnoverForAGivenMonth(val asOfDate: LocalDate)

class TurnoverForAGivenMonthHandler(
    private val subscriptionRepository: SubscriptionRepository
) {
    operator fun invoke(command: TurnoverForAGivenMonth): Turnover {

        return Turnover.monthly(
            subscriptionRepository.onGoingSubscriptions(command.asOfDate)
        )
    }
}
