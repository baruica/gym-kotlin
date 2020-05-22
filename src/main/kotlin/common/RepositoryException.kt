package common

class RepositoryException(override val message: String) : Throwable(message) {

    companion object {
        fun notFound(aggregateId: AggregateId): RepositoryException {
            return RepositoryException("${aggregateId::class.simpleName?.removeSuffix("Id")} [$aggregateId] not found.")
        }
    }
}
