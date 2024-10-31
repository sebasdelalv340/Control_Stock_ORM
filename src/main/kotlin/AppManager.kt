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
        var existeUsuario = false
        while (true) {
            consola.menuUsuario()
            when (readLine()?.toIntOrNull()) {
                1 -> {
                    existeUsuario = userService.pedirRegistro()
                }

                2 -> userService.nuevoUsuario()
                3 -> {
                    println("Saliendo del menú. ¡Hasta luego!")
                    Thread.sleep(3000)
                    break
                }
            }
            menuPrincipal(existeUsuario)
        }
    }

    private fun menuPrincipal(existeUsuario: Boolean) {
        if (existeUsuario) {
            var correcto = true
            while (correcto) {
                consola.menuPrincipal()
                when (readLine()?.toIntOrNull()) {
                    1 -> altaProducto()
                    2 -> bajaProducto()
                    3 -> modificarNombreProducto()
                    4 -> modificarStockProducto()
                    5 -> getProducto()
                    6 -> getProductosConStock()
                    7 -> getProductosSinStock()
                    8 -> getProveedorProducto()
                    9 -> getTodosProveedores()
                    10 -> {
                        correcto = false
                    }

                    else -> println("Opción no válida. Inténtalo de nuevo.")
                }
            }
        }
    }


    private fun altaProducto() {
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

    private fun bajaProducto() {
        val id = consola.pedirString("Introduce el ID del producto: ")
        productoService.eliminarProducto(id)
    }


    private fun modificarNombreProducto() {
        val idProducto = consola.pedirString("Introduzca el ID del producto que desea eliminar: ")
        val nuevoNombre = consola.pedirString("Introduce el nuevo nombre del producto: ")

        productoService.modificarNombreProducto(idProducto, nuevoNombre)
    }


    private fun modificarStockProducto() {
        val idProducto = consola.pedirString("Introduzca el ID del producto que desea modificar: ")
        val nuevoStock = consola.pedirString("Introduce el nuevo valor del stock del producto: ")

        productoService.modificarNombreProducto(idProducto, nuevoStock)
    }

    private fun getProducto() {
        val idProducto = consola.pedirString("Introduzca el ID del producto: ")
        val producto = productoService.buscarProducto(idProducto)
        if (producto != null) {
            consola.imprimirMensaje(producto.toString(), true)
        }
    }

    private fun getProductosConStock() {
        val listaProductos = productoService.getProductosConStock()
        if (listaProductos.isEmpty()) {
            consola.imprimirMensaje("No existen productos con stock.", true)
        } else {
            listaProductos.forEach { consola.imprimirMensaje(it.toString(), true) }
        }
    }

    private fun getProductosSinStock() {
        val listaProductos = productoService.getProductosSinStock()
        if (listaProductos.isEmpty()) {
            consola.imprimirMensaje("No existen productos sin stock.", true)
        } else {
            listaProductos.forEach { consola.imprimirMensaje(it.toString(), true) }
        }
    }

    private fun getProveedorProducto() {
        val idProducto = consola.pedirString("Introduzca el ID del producto: ")
        val proveedor = proveedorService.getProveedorProducto(idProducto)
        if (proveedor != null) {
            consola.imprimirMensaje(proveedor.toString(), true)
        }
    }

    private fun getTodosProveedores() {
        proveedorService.getTodosProveedores().forEach {
            consola.imprimirMensaje(it.toString(), true)
        }
    }


}