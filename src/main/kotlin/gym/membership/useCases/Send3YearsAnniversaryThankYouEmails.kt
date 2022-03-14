package gym.membership.useCases

import gym.membership.domain.Mailer
import gym.membership.domain.Member
import gym.membership.domain.MemberRepository

data class Send3YearsAnniversaryThankYouEmails(
    val memberId: String,
    val newSubscriptionPrice: Double,
) {
    class Handler(
        private val memberRepository: MemberRepository,
        private val mailer: Mailer,
    ) {
        operator fun invoke(command: Send3YearsAnniversaryThankYouEmails): Member {

            val threeYearsAnniversaryMember = memberRepository.get(
                command.memberId
            )

            mailer.send3YearsAnniversaryEmail(
                threeYearsAnniversaryMember,
                command.newSubscriptionPrice
            )

            return threeYearsAnniversaryMember
        }
    }
}
