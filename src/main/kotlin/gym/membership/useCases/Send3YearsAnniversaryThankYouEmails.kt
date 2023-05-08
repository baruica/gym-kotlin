package gym.membership.useCases

import Id
import gym.membership.domain.Mailer
import gym.membership.domain.Member
import gym.membership.domain.MemberRepository

data class Send3YearsAnniversaryThankYouEmails(
    val memberId: Id<String>,
    val newSubscriptionPrice: Double,
) {
    class Handler(
        private val memberRepository: MemberRepository,
        private val mailer: Mailer,
    ) {
        operator fun invoke(command: Send3YearsAnniversaryThankYouEmails): Member {

            return memberRepository.get(
                command.memberId
            ).also {
                mailer.send3YearsAnniversaryEmail(
                    it,
                    command.newSubscriptionPrice
                )
            }
        }
    }
}
