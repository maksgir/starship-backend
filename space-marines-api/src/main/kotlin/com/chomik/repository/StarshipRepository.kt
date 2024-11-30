package com.chomik.repository

import com.chomik.configuration.DatabaseSessionManager
import com.chomik.domain.Starship
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import org.hibernate.Session

@ApplicationScoped
class StarshipRepository {

    @Inject
    private lateinit var databaseSessionManager: DatabaseSessionManager

    fun createStarship(starship: Starship): Starship {
        return executeWithSession { session ->
            session.persist(starship)
            starship
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
            throw Exception("An error occurred during database operation", e)
        } finally {
            if (session.isOpen) {
                databaseSessionManager.closeSession(session)
            }
        }
    }
}
