package org.example.com.es.inputOutput

interface IInput {
    fun pedirEntero(mensaje: String): Int
    fun pedirFloat(mensjae: String): Float
    fun pedirString(mensaje: String): String
}