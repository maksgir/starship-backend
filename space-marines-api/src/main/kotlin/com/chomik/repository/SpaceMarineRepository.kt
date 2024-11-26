package com.chomik.repository

import com.chomik.configuration.DatabaseSessionManager
import com.chomik.domain.Excep
import com.chomik.domain.SpaceMarine

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import org.hibernate.Session

import kotlin.Exception

@ApplicationScoped
class SpaceMarinesRepository {

    @Inject
    private lateinit var databaseSessionManager: DatabaseSessionManager

    fun saveSpaceMarine(spaceMarine: SpaceMarine) {
        val session: Session = databaseSessionManager.getSession()
        try {
            session.beginTransaction()

            session.persist(spaceMarine)

            session.transaction.commit()
        } catch (e: Exception) {
            e.printStackTrace()
            if (session.transaction.isActive) {
                session.transaction.rollback()
            }
            throw Excep(
                message = "An error occurred while saving SpaceMarine entity",
                cause = e,
            )
        } finally {
            databaseSessionManager.closeSession(session)
        }
    }

    fun getSpaceMarine(spaceMarineId: Long): SpaceMarine? {
        val session: Session = databaseSessionManager.getSession()
        try {
            session.beginTransaction()

            return session.createQuery("SELECT y FROM SpaceMarine y WHERE y.id = :id", SpaceMarine::class.java)
                .setParameter("id", spaceMarineId)
                .singleResultOrNull

        } catch (e: Exception) {
            e.printStackTrace()
            if (session.transaction.isActive) {
                session.transaction.rollback()
            }
            throw Excep(
                message = "An error occurred while retrieving SpaceMarine entity with id = $spaceMarineId",
                cause = e,
            )
        } finally {
            if (session.isOpen) {
                databaseSessionManager.closeSession(session)
            }
        }
    }

    fun updateSpaceMarine(spaceMarine: SpaceMarine) {
        val session: Session = databaseSessionManager.getSession()
        try {
            session.beginTransaction()

            session.merge(spaceMarine)

            session.transaction.commit()
        } catch (e: Exception) {
            e.printStackTrace()
            if (session.transaction.isActive) {
                session.transaction.rollback()
            }
            throw Excep(
                message = "An error occurred while updating SpaceMarine entity with id = ${spaceMarine.id}",
                cause = e,
            )
        } finally {
            if (session.isOpen) {
                databaseSessionManager.closeSession(session)
            }
        }
    }

    fun deleteSpaceMarineById(spaceMarineId: Long): Int {
        val session: Session = databaseSessionManager.getSession()
        try {
            session.beginTransaction()

            return session
                .createMutationQuery("DELETE FROM SpaceMarine y WHERE y.id = :id")
                .setParameter("id", spaceMarineId)
                .executeUpdate()

        } catch (e: Exception) {
            e.printStackTrace()
            if (session.transaction.isActive) {
                session.transaction.rollback()
            }
            throw Excep(
                message = "An error occurred while deleting SpaceMarine entity with id = $spaceMarineId",
                cause = e,
            )
        } finally {
            if (session.isOpen) {
                databaseSessionManager.closeSession(session)
            }
        }
    }
}
