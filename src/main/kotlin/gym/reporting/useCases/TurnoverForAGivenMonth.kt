package gym.reporting.useCases

import gym.reporting.Turnover
import gym.subscriptions.domain.SubscriptionRepository

class TurnoverForAGivenMonth(
    private val subscriptionRepository: SubscriptionRepository
) {
    operator fun invoke(command: TurnoverForAGivenMonthQuery): Turnover {

        return Turnover.monthly(
            subscriptionRepository.onGoingSubscriptions(command.asOfDate)
        )
    }
}
