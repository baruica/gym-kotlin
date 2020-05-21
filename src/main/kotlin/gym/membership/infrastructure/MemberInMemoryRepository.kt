package gym.membership.infrastructure

import common.InMemoryRepository
import gym.membership.domain.Email
import gym.membership.domain.Member
import gym.membership.domain.MemberId
import gym.membership.domain.MemberRepository
import java.time.LocalDate

class MemberInMemoryRepository : InMemoryRepository(), MemberRepository {

    override fun findByEmail(email: Email): Member? {
        return aggregates.filter {
            (it.value as Member).email == email
        }.values.firstOrNull() as Member?
    }

    override fun threeYearsAnniversaryMembers(asOfDate: LocalDate): Map<MemberId, Member> {
        return aggregates.filter {
            (it.value as Member).isThreeYearsAnniversary(asOfDate)
        } as Map<MemberId, Member>
    }
}
