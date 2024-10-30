package org.example.com.es.service

import com.es.inputOutput.Consola
import org.example.com.es.model.Usuario
import org.example.com.es.repository.UsuarioRepository

class UsuarioService(val repository: UsuarioRepository, val consola: Consola) {

    private fun comprobarUsuario(userInput: String): Usuario? {
        val usuario = repository.readUser(userInput)
        if(usuario != null) {
            return usuario
        } else {
            consola.imprimirMensaje("El usuario introducido no existe.", true)
        }
        return null
    }


    private fun registroUsuario(userInput: String, passInput: String): Boolean {
        val usuario = comprobarUsuario(userInput)
        if(usuario != null && (passInput == usuario.password)) {
            return true
        } else {
            consola.imprimirMensaje("La contraseña introducida no es correcta.", true)
            return false
        }
    }

    fun pedirRegistro(): Boolean {
        var correcto = true
        do {
            val nombre = consola.pedirString("Introduzca su nombre de usuario: ")
            val password = consola.pedirString("Introduzca su contraseña: ")
            if (registroUsuario(nombre, password)) {
                correcto = false
                return true
            } else {
                return false
            }
        } while (correcto)
    }
}