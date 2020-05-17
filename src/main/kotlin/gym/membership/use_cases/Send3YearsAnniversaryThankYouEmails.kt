package gym.membership.use_cases

import gym.membership.domain.Mailer
import gym.membership.domain.MemberEvent
import gym.membership.domain.MemberRepository
import java.time.LocalDate

class Send3YearsAnniversaryThankYouEmails(
    private val memberRepository: MemberRepository,
    private val mailer: Mailer
) {
    fun handle(command: Send3YearsAnniversaryThankYouEmailsCommand): List<MemberEvent> {

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

        return threeYearsAnniversaryMembers.values.map {
            it.raisedEvents.last()
        }
    }
}