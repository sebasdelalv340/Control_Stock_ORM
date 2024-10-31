package org.example.com.es.service

import org.example.com.es.model.Producto
import org.example.com.es.model.Proveedor
import org.example.com.es.repository.ProductoRepository
import java.time.Instant
import java.util.Date

class ProductoService(val repository: ProductoRepository) {

    private fun construirProducto(
        categoria: String,
        nombre: String,
        descripcion: String,
        precioSinIva: Float,
        stock: Int,
        proveedor: Proveedor?
    ): Producto {
        val idProducto = createNombreProducto(categoria, nombre, proveedor)
        val precioConIva = calcularIva(precioSinIva)
        val fechaAlta = Date.from(Instant.now())
        val producto = Producto(
            idProducto,
            categoria,
            nombre,
            descripcion,
            precioSinIva,
            precioConIva,
            fechaAlta,
            stock,
            proveedor
        )
        return producto
    }


    private fun createNombreProducto(
        categoria: String,
        nombre: String,
        proveedor: Proveedor?
    ): String {
        val nombreProducto = categoria.take(3) + nombre.take(3) + proveedor?.nombre?.take(3)
        return nombreProducto
    }


    private fun calcularIva(precioSinIva: Float): Float {
        return (precioSinIva * 1.21).toFloat()
    }

    fun createProducto(
       categoria: String,
       nombre: String,
       descripcion: String,
       precioSinIva: Float,
       stock: Int,
       proveedor: Proveedor?
    ) {
        val producto = construirProducto(categoria, nombre, descripcion, precioSinIva, stock, proveedor)
        repository.createProducto(producto)
    }


    fun eliminarProducto(idProducto: String) {
        val producto = repository.readProducto(idProducto)
        if(producto != null) {
            repository.deleteProducto(producto)
        }
    }

    fun buscarProducto(idProducto: String): Producto? {
        val producto = repository.readProducto(idProducto)
        return producto
    }


    fun modificarNombreProducto(idProducto: String, nuevoNombre: String) {
        val producto = buscarProducto(idProducto)
        if (producto != null) {
             val nuevoProducto = construirProducto(
                 producto.categoria,
                 nuevoNombre,
                 producto.descripcion,
                 producto.precioSinIva,
                 producto.stock,
                 producto.proveedor
             )
            repository.updateProducto(nuevoProducto)
        }
    }

    fun modificarStockProducto(idProducto: String, stock: Int) {
        val producto = buscarProducto(idProducto)
        if (producto != null) {
            val nuevoProducto = construirProducto(
                producto.categoria,
                producto.nombre,
                producto.descripcion,
                producto.precioSinIva,
                stock,
                producto.proveedor
            )
            repository.updateProducto(nuevoProducto)
        }
    }

    fun getProductosConStock(): List<Producto?> {
        return repository.getProductosConStock()
    }

    fun getProductosSinStock(): List<Producto?> {
        return repository.getProductosSinStock()
    }
}