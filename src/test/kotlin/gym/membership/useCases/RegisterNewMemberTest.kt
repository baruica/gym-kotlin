package gym.membership.useCases

import gym.membership.domain.EmailAddress
import io.kotest.core.spec.style.AnnotationSpec
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

        val tested = RegisterNewMember(repository, mailer)
        val member = tested.handle(
            RegisterNewMemberCommand(
                memberId,
                "subscriptionId def",
                "2018-06-05",
                emailAddress.value
            )
        )

        member?.let {
            it.id.toString() shouldBe memberId
            it.emailAddress shouldBe emailAddress

            mailer.welcomeEmailWasSentTo(emailAddress) shouldBe true
        }
    }
}
