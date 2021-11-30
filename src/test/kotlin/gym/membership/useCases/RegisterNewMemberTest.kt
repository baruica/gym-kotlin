package gym.membership.useCases

import gym.membership.domain.EmailAddress
import gym.membership.domain.MemberId
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe

class RegisterNewMemberTest : AnnotationSpec() {

    @Test
    fun handle() {
        val repository = InMemoryMemberRepository()
        val memberId = repository.nextId()
        val emailAddress = EmailAddress("luke@gmail.com")

        repository.findByEmailAddress(emailAddress).shouldBeNull()

        val mailer = InMemoryMailer()

        val tested = RegisterNewMemberHandler(repository, mailer)
        val member = tested(
            RegisterNewMember(
                memberId,
                "subscriptionId def",
                "2018-06-05",
                emailAddress.value
            )
        )

        member?.let {
            it.id shouldBe MemberId(memberId)
            it.emailAddress shouldBe emailAddress

            mailer.welcomeEmailWasSentTo(emailAddress).shouldBeTrue()
        }
    }
}
