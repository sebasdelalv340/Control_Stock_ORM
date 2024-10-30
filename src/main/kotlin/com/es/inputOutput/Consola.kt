package com.es.inputOutput

import org.example.com.es.inputOutput.IInput
import org.example.com.es.inputOutput.IOutput

class Consola: IInput, IOutput {
    override fun imprimirMensaje(mensaje: String, salto: Boolean) {
        if (salto) {
            println(mensaje)
        } else {
            print(mensaje)
        }
    }

    override fun pedirEntero(mensaje: String): Int{
        imprimirMensaje(mensaje, false)
        val num = readln().toInt()
        return num
    }

    override fun pedirFloat(mensaje: String): Float{
        imprimirMensaje(mensaje, false)
        val num = readln().toFloat()
        return num
    }

    override fun pedirString(mensaje: String): String {
        imprimirMensaje(mensaje, false)
        val texto = readln()
        return texto
    }

    fun menuPrincipal() {
        imprimirMensaje("Menú:", true)
        imprimirMensaje("1. Dar de alta un producto.", true)
        imprimirMensaje("2. Dar de baja un producto", true)
        imprimirMensaje("3. Modificar el nombre de un producto.", true)
        imprimirMensaje("4. Modificar el stock de un producto.", true)
        imprimirMensaje("5. Obtener un producto.", true)
        imprimirMensaje("6. Obtener los productos con stock.", true)
        imprimirMensaje("7. Obtener los productos sin stock.", true)
        imprimirMensaje("8. Obtener un proveedor.", true)
        imprimirMensaje("9. Obtener todos los proveedores.", true)
        imprimirMensaje("10. Salir", true)
        imprimirMensaje("¿Qué desea hacer?: ", false)
    }
}