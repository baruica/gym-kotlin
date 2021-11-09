package gym.subscriptions.useCases

import gym.subscriptions.domain.Subscription
import gym.subscriptions.domain.SubscriptionRepository
import java.time.LocalDate

data class ApplyThreeYearsAnniversaryDiscountCommand(val asOfDate: String)

class ApplyThreeYearsAnniversaryDiscount(
    private val subscriptionRepository: SubscriptionRepository
) {
    operator fun invoke(command: ApplyThreeYearsAnniversaryDiscountCommand): List<Subscription> {

        val date = LocalDate.parse(command.asOfDate)

        val threeYearsAnniversarySubscriptions = subscriptionRepository.threeYearsAnniversarySubscriptions(date)

        threeYearsAnniversarySubscriptions.forEach {
            it.applyThreeYearsAnniversaryDiscount(date)
        }

        subscriptionRepository.storeAll(threeYearsAnniversarySubscriptions)

        return threeYearsAnniversarySubscriptions
    }
}
