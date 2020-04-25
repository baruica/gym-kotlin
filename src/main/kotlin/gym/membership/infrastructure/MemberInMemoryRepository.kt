package gym.membership.infrastructure

import gym.membership.domain.EmailAddress
import gym.membership.domain.Member
import gym.membership.domain.MemberId
import gym.membership.domain.MemberRepository
import java.time.LocalDate
import java.util.*

class MemberInMemoryRepository : MemberRepository {

    private val members = HashMap<MemberId, Member>()

    override fun nextId(): MemberId {
        return MemberId(UUID.randomUUID().toString())
    }

    override fun store(member: Member) {
        members[member.id] = member
    }

    override fun get(memberId: MemberId): Member {
        return members[memberId]
            ?: throw MemberRepositoryException.notFound(memberId)
    }

    override fun findByEmail(email: EmailAddress): Member? {
        return members.filterValues {
            it.email == email
        }.values.firstOrNull()
    }

    override fun threeYearsAnniversaryMembers(asOfDate: LocalDate): Map<MemberId, Member> {
        return members.filterValues {
            it.isThreeYearsAnniversary(asOfDate)
        }
    }
}
