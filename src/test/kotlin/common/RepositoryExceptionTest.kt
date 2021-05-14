package common

import gym.membership.domain.MemberId
import gym.plans.domain.PlanId
import gym.subscriptions.domain.SubscriptionId
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe

internal class RepositoryExceptionTest : AnnotationSpec() {

    @Test
    fun `has a message that tells which type of aggregate is not found`() {
        RepositoryException.notFound(MemberId("member 42")).message shouldBe "Member [member 42] not found."
        RepositoryException.notFound(PlanId("plan 42")).message shouldBe "Plan [plan 42] not found."
        RepositoryException.notFound(SubscriptionId("subscription 42")).message shouldBe "Subscription [subscription 42] not found."
    }
}
