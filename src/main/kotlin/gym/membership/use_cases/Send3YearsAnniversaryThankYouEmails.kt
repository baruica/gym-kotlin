package gym.membership.use_cases

import gym.membership.domain.Mailer
import gym.membership.domain.MemberRepository
import gym.membership.domain.ThreeYearsAnniversaryThankYouEmailsSent
import java.time.LocalDate

class Send3YearsAnniversaryThankYouEmails(
    private val memberRepository: MemberRepository,
    private val mailer: Mailer
) {
    fun handle(command: Send3YearsAnniversaryThankYouEmailsCommand): ThreeYearsAnniversaryThankYouEmailsSent {

        val threeYearsAnniversaryMembers = memberRepository.threeYearsAnniversaryMembers(
            LocalDate.parse(command.asOfDate)
        )

        threeYearsAnniversaryMembers.mapValues {
            mailer.sendEmail(
                it.value.email,
                "Thank you for your loyalty ${it.value.email} !"
            )

            it.value.mark3YearsAnniversaryThankYouEmailAsSent()
        }

        return ThreeYearsAnniversaryThankYouEmailsSent(
            threeYearsAnniversaryMembers.keys.toList()
        )
    }
}
