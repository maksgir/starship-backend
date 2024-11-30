package com.chomik.repository

import com.chomik.configuration.DatabaseSessionManager
import com.chomik.domain.Excep
import com.chomik.domain.QueryParams
import com.chomik.domain.SpaceMarine

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import org.hibernate.Session
import java.util.Locale

import kotlin.Exception

@ApplicationScoped
class SpaceMarinesRepository {

    @Inject
    private lateinit var databaseSessionManager: DatabaseSessionManager

    fun save(spaceMarine: SpaceMarine) {
        executeWithSession { session ->
            session.persist(spaceMarine)
        }
    }

    fun findById(spaceMarineId: Long): SpaceMarine? {
        return executeWithSession { session ->
            session.createQuery("SELECT y FROM SpaceMarine y WHERE y.id = :id", SpaceMarine::class.java)
                .setParameter("id", spaceMarineId)
                .singleResultOrNull
        }
    }

    fun getSpaceMarines(queryParams: QueryParams): List<SpaceMarine> {
        return executeWithSession { session ->
            session.createQuery(
                "SELECT y FROM SpaceMarine y ${constructWhereClose(queryParams)} ${constructSortClause(queryParams)}",
                SpaceMarine::class.java
            )
                .setFirstResult((queryParams.page - 1) * queryParams.size)
                .setMaxResults(queryParams.size)
                .resultList
        }
    }

    fun update(spaceMarine: SpaceMarine) {
        executeWithSession { session ->
            session.merge(spaceMarine)
        }
    }

    fun deleteById(spaceMarineId: Long): Int {
        return executeWithSession { session ->
            session.createMutationQuery("DELETE FROM SpaceMarine y WHERE y.id = :id")
                .setParameter("id", spaceMarineId)
                .executeUpdate()
        }
    }

    private inline fun <T> executeWithSession(action: (Session) -> T): T {
        val session: Session = databaseSessionManager.getSession()
        return try {
            session.beginTransaction()
            val result = action(session)
            session.transaction.commit()
            result
        } catch (e: Exception) {
            e.printStackTrace()
            if (session.transaction.isActive) {
                session.transaction.rollback()
            }
            throw Excep(
                message = "An error occurred during database operation",
                cause = e,
            )
        } finally {
            if (session.isOpen) {
                databaseSessionManager.closeSession(session)
            }
        }
    }

    private fun constructWhereClose(queryParams: QueryParams): String {
        var where = "WHERE 1 = 1"

        if (!queryParams.filters.isNullOrEmpty()) {
            queryParams.filters.forEach { filter ->
                where += " and y.${filter.columnName} ${filter.comparisonSymbol.symbol} ${filter.value}"
            }
        }

        return where
    }

    private fun constructSortClause(queryParams: QueryParams): String {
        var sort = ""

        if (!queryParams.sortBy.isNullOrEmpty()) {
            sort = " ORDER BY"
            queryParams.sortBy.forEachIndexed { i, elem ->
                sort += " y.${elem.columnName}"
                if (queryParams.sortOrder != null) {
                    sort += " ${queryParams.sortOrder.name.lowercase(Locale.getDefault())}"
                }
                if (i != queryParams.sortBy.size - 1) {
                    sort += ","
                }
            }
        }

        return sort
    }
}
