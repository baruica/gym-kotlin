package gym.membership.infrastructure

import gym.membership.domain.MemberId

class MemberRepositoryException(override val message: String) : Throwable(message) {

    companion object {
        fun notFound(memberId: MemberId): MemberRepositoryException {
            return MemberRepositoryException("Member [$memberId] not found.")
        }
    }
}
