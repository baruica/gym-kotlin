package fr.craft.gym.membership.domain

class MemberRepositoryException(override val message: String) : Throwable(message) {

    companion object {
        fun notFound(memberId: MemberId): MemberRepositoryException {
            return MemberRepositoryException("Member [$memberId] not found.")
        }
    }
}
