package gym.membership.use_cases

import gym.fifthOfJune
import gym.membership.domain.Member
import gym.membership.domain.MemberId
import gym.membership.infrastructure.InMemoryMailer
import gym.membership.infrastructure.MemberInMemoryRepository
import gym.subscriptions.domain.SubscriptionId
import org.junit.Test
import java.time.LocalDate
import java.util.*
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class Send3YearsAnniversaryThankYouEmailsTest {

    @Test
    fun handle() {
        val memberRepository = MemberInMemoryRepository()

        val memberJulie = buildMember("julie@gmail.com", fifthOfJune().minusYears(3))
        memberRepository.store(memberJulie)

        val memberBob = buildMember("bob@gmail.com", fifthOfJune().minusYears(2))
        memberRepository.store(memberBob)

        val memberLuke = buildMember("luke@gmail.com", fifthOfJune().minusYears(3))
        memberRepository.store(memberLuke)

        val mailer = InMemoryMailer()

        val tested = Send3YearsAnniversaryThankYouEmails(memberRepository, mailer)

        val event = tested.handle(
            Send3YearsAnniversaryThankYouEmailsCommand("2018-06-05")
        )

        assertTrue(mailer.sentEmails.containsValue("Thank you for your loyalty julie@gmail.com !"))
        assertFalse(mailer.sentEmails.containsValue("Thank you for your loyalty bob@gmail.com !"))
        assertTrue(mailer.sentEmails.containsValue("Thank you for your loyalty luke@gmail.com !"))
        assertTrue(event.memberIds.contains(memberJulie.id.toString()))
        assertFalse(event.memberIds.contains(memberBob.id.toString()))
        assertTrue(event.memberIds.contains(memberLuke.id.toString()))
    }

    private fun buildMember(email: String, startDate: LocalDate): Member = Member(
        MemberId(UUID.randomUUID().toString()),
        email,
        SubscriptionId("def"),
        startDate
    )
}
