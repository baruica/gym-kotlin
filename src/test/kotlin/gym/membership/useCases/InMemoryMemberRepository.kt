package gym.membership.useCases

import InMemoryRepository
import gym.membership.domain.EmailAddress
import gym.membership.domain.Member
import gym.membership.domain.MemberRepository
import java.time.LocalDate

class InMemoryMemberRepository : InMemoryRepository<Member>(), MemberRepository {

    override fun findByEmailAddress(emailAddress: EmailAddress): Member? {
        return items.filter {
            it.value.emailAddress == emailAddress
        }.values.firstOrNull()
    }

    override fun threeYearsAnniversaryMembers(date: LocalDate): List<Member> {
        return items.filter {
            it.value.isThreeYearsAnniversary(date)
        }.values.map { it }
    }
}
