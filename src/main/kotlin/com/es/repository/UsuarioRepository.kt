package org.example.com.es.repository

import org.example.com.es.inputOutput.IOutput
import org.example.com.es.model.Usuario
import org.example.com.es.service.EntityManagerService

class UsuarioRepository(val consola: IOutput) {

    fun createUser(user: Usuario) {
        val em = EntityManagerService.createEM()
        try {
            em.transaction.begin()
            em.persist(user)
            em.transaction.commit()
            consola.imprimirMensaje("Usuario creado", true)
        } catch (e: Exception) {
            consola.imprimirMensaje("Error: ${e.message}", true)
            em.transaction.rollback()
        } finally {
            em.close()
        }
    }


    fun readUser(nombre: String): Usuario? {
        val em = EntityManagerService.createEM()
        try {
            em.transaction.begin()
            val userFromBD: Usuario = em.find(Usuario::class.java, nombre)
            em.transaction.commit()
            em.close()
            return userFromBD
        } catch (e: Exception) {
            consola.imprimirMensaje("Error: ${e.message}", true)
            em.transaction.rollback()
            em.close()
        } finally {
            em.close()
        }
        return null
    }


    fun updateUser(user: Usuario) {
        val em = EntityManagerService.createEM()
        try {
            em.transaction.begin()
            val user = em.find(Usuario::class.java, user)
            if (user != null) {
                em.merge(user)
                em.transaction.commit()
                consola.imprimirMensaje("Usuario actualizado", true)
            }
        } catch (e: Exception) {
            consola.imprimirMensaje("Error: ${e.message}", true)
            em.transaction.rollback()
        } finally {
            em.close()
        }
    }

    fun deleteUser(nameUser: String) {
        val em = EntityManagerService.createEM()
        try {
            em.transaction.begin()
            val user = em.find(Usuario::class.java, nameUser)
            em.remove(user)
            em.transaction.commit()
            consola.imprimirMensaje("Usuario borrado", true)
        } catch (e: Exception) {
            consola.imprimirMensaje("Error: ${e.message}", true)
            em.transaction.rollback()
        } finally {
            em.close()
        }
    }
}