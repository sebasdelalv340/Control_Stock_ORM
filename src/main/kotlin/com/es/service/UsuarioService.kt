package org.example.com.es.service

import com.es.inputOutput.Consola
import org.example.com.es.model.Usuario
import org.example.com.es.repository.UsuarioRepository

class UsuarioService(val repository: UsuarioRepository, val consola: Consola) {

    private fun comprobarUsuario(userInput: String): Usuario? {
        val usuario = repository.readUser(userInput)
        if (usuario != null) {
            return usuario
        } else {
            consola.imprimirMensaje("El usuario introducido no existe.", true)
        }
        return null
    }


    private fun registroUsuario(userInput: String, passInput: String): Boolean {
        val usuario = comprobarUsuario(userInput)
        if (usuario != null && (passInput == usuario.password)) {
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

    fun nuevoUsuario() {
        var correcto = true
        do {
            val nombreUsuario = pedirNombreNoVacio()
            var nuevoUsuario = repository.readUser(nombreUsuario)
            if (nuevoUsuario != null) {
                consola.imprimirMensaje("El nombre de usuario ya existe.", true)
            } else {
                val nuevaPassword = pedirPasswordNoVacia()
                nuevoUsuario = Usuario(nombreUsuario, nuevaPassword)
                repository.createUser(nuevoUsuario)
                correcto = false
            }
        } while (correcto)
    }

    private fun pedirNombreNoVacio(): String {
        var correcto = true
        var nuevoNombre = ""
        while (correcto) {
            nuevoNombre = consola.pedirString("Introduzca nuevo nombre de usuario: ")
            if (nuevoNombre.isEmpty()) {
                consola.imprimirMensaje("El nombre de usuario no puede estar en blanco. Inténtalo de nuevo.", true)
            } else {
                correcto = false
                return nuevoNombre
            }
        }
        return nuevoNombre
    }

    private fun pedirPasswordNoVacia(): String {
        var correcto = true
        var nuevaPassword = ""
        while (correcto) {
            nuevaPassword = consola.pedirString("Introduzca nueva contraseña: ")
            if (nuevaPassword.isEmpty()) {
                consola.imprimirMensaje("La contraseña no puede estar en blanco. Inténtalo de nuevo.", true)
            } else {
                correcto = false
                return nuevaPassword
            }
        }
        return nuevaPassword
    }

}