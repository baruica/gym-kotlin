package gym.membership.use_cases

import gym.fifthOfJune
import gym.membership.domain.EmailAddress
import gym.membership.domain.Member
import gym.membership.domain.MemberId
import gym.membership.domain.ThreeYearsAnniversaryThankYouEmailSent
import gym.membership.infrastructure.InMemoryMailer
import gym.membership.infrastructure.MemberInMemoryRepository
import gym.subscriptions.domain.SubscriptionId
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.util.*
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class Send3YearsAnniversaryThankYouEmailsTest {

    @Test
    fun handle() {
        val memberRepository = MemberInMemoryRepository()

        val startDateJulie = fifthOfJune().minusYears(3)
        val memberJulie = newMember("julie@gmail.com", startDateJulie)
        memberRepository.store(memberJulie)

        val startDateBob = fifthOfJune().minusYears(2)
        val memberBob = newMember("bob@gmail.com", startDateBob)
        memberRepository.store(memberBob)

        val startDateLuke = fifthOfJune().minusYears(3)
        val memberLuke = newMember("luke@gmail.com", startDateLuke)
        memberRepository.store(memberLuke)

        val mailer = InMemoryMailer()

        val tested = Send3YearsAnniversaryThankYouEmails(memberRepository, mailer)

        val events = tested.handle(
            Send3YearsAnniversaryThankYouEmailsCommand("2018-06-05")
        )

        assertTrue(mailer.threeYearsAnniversaryWasSentTo("julie@gmail.com"))
        assertTrue(events.contains(ThreeYearsAnniversaryThankYouEmailSent(memberJulie.id.toString(), startDateJulie.toString())))

        assertFalse(mailer.threeYearsAnniversaryWasSentTo("bob@gmail.com"))
        assertFalse(events.contains(ThreeYearsAnniversaryThankYouEmailSent(memberBob.id.toString(), startDateBob.toString())))

        assertTrue(mailer.threeYearsAnniversaryWasSentTo("luke@gmail.com"))
        assertTrue(events.contains(ThreeYearsAnniversaryThankYouEmailSent(memberLuke.id.toString(), startDateLuke.toString())))
    }

    private fun newMember(email: String, startDate: LocalDate): Member = Member.register(
        MemberId(UUID.randomUUID().toString()),
        EmailAddress(email),
        SubscriptionId("def"),
        startDate
    )
}
