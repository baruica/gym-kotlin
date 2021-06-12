package gym.membership.useCases

import gym.membership.domain.Mailer
import gym.membership.domain.Member
import gym.membership.domain.MemberRepository

class Send3YearsAnniversaryThankYouEmails(
    private val memberRepository: MemberRepository,
    private val mailer: Mailer,
) {
    fun handle(command: Send3YearsAnniversaryThankYouEmailsCommand): Member {

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
