package gym.membership.use_cases

import gym.membership.domain.EmailAddress
import gym.membership.domain.Member
import gym.membership.domain.MemberEvent.NewMemberSubscribed
import gym.membership.domain.MemberEvent.WelcomeEmailWasSentToNewMember
import gym.membership.domain.MemberId
import gym.membership.infrastructure.InMemoryMailer
import gym.membership.infrastructure.MemberInMemoryRepository
import gym.subscriptions.domain.SubscriptionId
import org.junit.Test
import java.time.LocalDate
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SendWelcomeEmailToNewMemberTest {

    @Test
    fun handle() {

        val memberId = MemberId("abc")
        val email = "bob@gmail.com"
        val subscriptionId = SubscriptionId("def")
        val startDate = LocalDate.now()
        val member = Member(
            memberId,
            EmailAddress(email),
            subscriptionId,
            startDate
        )
        val memberRepository = MemberInMemoryRepository()
        memberRepository.store(member)

        val mailer = InMemoryMailer()

        val tested = SendWelcomeEmailToNewMember(memberRepository, mailer)

        val events = tested.handle(
            NewMemberSubscribed(
                memberId.toString(),
                email,
                subscriptionId.toString(),
                startDate.toString()
            )
        )

        assertEquals(
            events.last(),
            WelcomeEmailWasSentToNewMember(
                memberId.toString(),
                email,
                subscriptionId.toString()
            )
        )
        assertTrue(mailer.sentEmails.containsValue("Thank you for subscribing bob@gmail.com !"))
    }
}
