package fr.craft.gym.membership.use_cases

import fr.craft.gym.membership.domain.Mailer
import fr.craft.gym.membership.domain.MemberRepository
import fr.craft.gym.membership.domain.ThreeYearsAnniversaryThankYouEmailsSent

class Send3YearsAnniversaryThankYouEmails(
    private val memberRepository: MemberRepository,
    private val mailer: Mailer
) {
    fun handle(command: Send3YearsAnniversaryThankYouEmailsCommand): ThreeYearsAnniversaryThankYouEmailsSent {

        val threeYearsAnniversaryMembers = memberRepository.threeYearsAnniversaryMembers(command.asOfDate)

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
