package gym.membership.use_cases

import gym.membership.domain.Member
import gym.membership.domain.MemberEvent
import gym.membership.domain.MemberId
import gym.membership.infrastructure.InMemoryMailer
import gym.membership.infrastructure.MemberInMemoryRepository
import gym.subscriptions.domain.SubscriptionId
import org.junit.Test
import java.time.LocalDate
import kotlin.test.assertTrue

class SendWelcomeEmailToNewMemberTest {

    @Test
    fun handle() {

        val memberId = MemberId("abc")
        val email = "bob@gmail.com"
        val member = Member(
            memberId,
            email,
            SubscriptionId("def"),
            LocalDate.now()
        )
        val memberRepository = MemberInMemoryRepository()
        memberRepository.store(member)

        val mailer = InMemoryMailer()

        val tested = SendWelcomeEmailToNewMember(memberRepository, mailer)

        val events = tested.handle(
            MemberEvent.NewMemberSubscribed(memberId.toString(), email)
        )

        assertTrue(events.last() is MemberEvent.WelcomeEmailWasSentToNewMember)
        assertTrue(mailer.sentEmails.containsValue("Thank you for subscribing bob@gmail.com !"))
    }
}
