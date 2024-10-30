package org.example.com.es.service

import jakarta.persistence.EntityManager
import jakarta.persistence.EntityManagerFactory
import jakarta.persistence.Persistence

object EntityManagerService {
    val emf: EntityManagerFactory = Persistence.createEntityManagerFactory("unidadMySQL")
    fun createEM(): EntityManager {
        return if (emf.isOpen) {
            emf.createEntityManager()
        } else {
            Persistence.createEntityManagerFactory("unidadMySQL").createEntityManager()
        }
    }
}