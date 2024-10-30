package org.example

import com.es.service.ProveedorService
import com.es.inputOutput.Consola
import org.example.com.es.service.ProductoService
import org.example.com.es.service.UsuarioService

class AppManager(
    val consola: Consola,
    val userService: UsuarioService,
    val productoService: ProductoService,
    val proveedorService: ProveedorService
) {

    fun app() {
        if(userService.pedirRegistro()) {
            while (true) {
                consola.menuPrincipal()
                when (readLine()?.toIntOrNull()) {
                    1 -> altaProducto()
                    2 -> bajaProducto()
                    3 -> modificarNombreProducto()
                    4 -> modificarStockProducto()
                    5 -> getProducto()
                    6 -> println("Has elegido la opción 6")
                    7 -> println("Has elegido la opción 7")
                    8 -> println("Has elegido la opción 6")
                    9 -> println("Has elegido la opción 7")
                    10 -> {
                        println("Saliendo del menú. ¡Hasta luego!")
                        Thread.sleep(3000)
                        break
                    }

                    else -> println("Opción no válida. Inténtalo de nuevo.")
                }
            }
        }
    }

    fun altaProducto() {
        val categoria = consola.pedirString("Introduce la categoria del producto: ")
        val nombre = consola.pedirString("Introduce el nombre del producto: ")
        val description = consola.pedirString("Introduce la descripción del producto: ")
        val precioSinIva = consola.pedirFloat("Introduce el precio sin iva del producto: ")
        val stock = consola.pedirEntero("Introduce el stock del producto: ")
        val nombreProveedor = consola.pedirString("Introduce el nombre del proveedor: ")
        val direccionProveedor = consola.pedirString("Introduce la dirección del proveedor: ")
        val proveedor = proveedorService.createProveedor(nombreProveedor, direccionProveedor)
        productoService.createProducto(categoria, nombre, description, precioSinIva, stock, proveedor)
    }

    fun bajaProducto() {
        val id = consola.pedirString("Introduce el ID del producto: ")
        productoService.eliminarProducto(id)
    }


    fun modificarNombreProducto() {
        val idProducto = consola.pedirString("Introduzca el ID del producto que desea eliminar: ")
        val nuevoNombre = consola.pedirString("Introduce el nuevo nombre del producto: ")

        productoService.modificarNombreProducto(idProducto, nuevoNombre)
    }


    fun modificarStockProducto() {
        val idProducto = consola.pedirString("Introduzca el ID del producto que desea modificar: ")
        val nuevoStock = consola.pedirString("Introduce el nuevo valor del stock del producto: ")

        productoService.modificarNombreProducto(idProducto, nuevoStock)
    }

    fun getProducto() {
        val idProducto = consola.pedirString("Introduzca el ID del producto: ")
        val producto = productoService.buscarProducto(idProducto)
        if (producto != null) {
            consola.imprimirMensaje(producto.toString(), true)
        }
    }

}