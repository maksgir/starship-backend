package com.chomik.configuration

import com.chomik.domain.Chapter
import com.chomik.domain.SpaceMarine
import jakarta.enterprise.context.ApplicationScoped
import org.hibernate.Session
import org.hibernate.SessionFactory
import org.hibernate.boot.registry.StandardServiceRegistryBuilder
import org.hibernate.cfg.Configuration

@ApplicationScoped
class DatabaseSessionManager {

    private lateinit var sessionFactory: SessionFactory

    init {
        val configuration: Configuration =
            Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(SpaceMarine::class.java)
                .addAnnotatedClass(Chapter::class.java)
                // .addAnnotatedClass(Starship::class.java)
        StandardServiceRegistryBuilder()
            .applySettings(configuration.properties).build()
        configuration.buildSessionFactory()
        sessionFactory = configuration.buildSessionFactory()
    }

    fun getSession(): Session = sessionFactory.openSession()

    fun closeSession(session: Session) {
        if (session.isOpen) {
            session.close()
        }
    }
}
