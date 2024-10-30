package org.example.com.es.repository

import org.example.com.es.inputOutput.IOutput
import org.example.com.es.model.Producto
import org.example.com.es.service.EntityManagerService

class ProductoRepository(val consola: IOutput) {

    fun createProducto(producto: Producto) {
        val em = EntityManagerService.createEM()
        try {
            em.transaction.begin()
            em.persist(producto)
            em.transaction.commit()
            consola.imprimirMensaje("Producto creado", true)
        } catch (e: Exception) {
            consola.imprimirMensaje("Error: ${e.message}", true)
            em.transaction.rollback()
        } finally {
            em.close()
        }
    }


    fun readProducto(idProducto: String): Producto? {
        val em = EntityManagerService.createEM()
        try {
            em.transaction.begin()
            val productoFromBD: Producto = em.find(Producto::class.java, idProducto)
            if (productoFromBD != null) {
                em.transaction.commit()
                em.close()
                return productoFromBD
            } else {
                consola.imprimirMensaje("Producto no encontrado", true)
            }
        } catch (e: Exception) {
            consola.imprimirMensaje("Error: ${e.message}", true)
            em.transaction.rollback()
            em.close()
        } finally {
            em.close()
        }
        return null
    }


    fun updateProducto(producto: Producto) {
        val em = EntityManagerService.createEM()
        try {
            em.transaction.begin()
            val existeProducto = em.find(Producto::class.java, producto.id)
            if (existeProducto != null) {
                em.merge(producto)
                em.transaction.commit()
                consola.imprimirMensaje("Producto actualizado", true)
            } else {
                consola.imprimirMensaje("Producto no encontrado", true)
            }
        } catch (e: Exception) {
            consola.imprimirMensaje("Error: ${e.message}", true)
            em.transaction.rollback()
        } finally {
            em.close()
        }
    }


    fun deleteProducto(producto: Producto) {
        val em = EntityManagerService.createEM()
        try {
            em.transaction.begin()
            val existeProducto = em.find(Producto::class.java, producto.id)
            if(existeProducto != null) {
                em.remove(existeProducto)
                em.transaction.commit()
                consola.imprimirMensaje("Producto borrado", true)
            } else {
                consola.imprimirMensaje("Producto no encontrado", true)
            }
        } catch (e: Exception) {
            consola.imprimirMensaje("Error: ${e.message}", true)
            em.transaction.rollback()
        } finally {
            em.close()
        }
    }

    fun getProductosConStock(): List<Producto?> {
        val em = EntityManagerService.createEM()
        try {
            em.transaction.begin()
            val query = em.createQuery("SELECT p FROM productos p WHERE p.stock > 0", Producto::class.java)
            val productos = query.resultList
            em.transaction.commit()
            return productos
        } catch (e: Exception) {
            consola.imprimirMensaje("Error: ${e.message}", true)
            em.transaction.rollback()
            return emptyList()
        } finally {
            em.close()
        }
    }


    fun getProductosSinStock(): List<Producto> {
        val em = EntityManagerService.createEM()
        try {
            em.transaction.begin()
            val query = em.createQuery("SELECT p FROM productos p WHERE p.stock = 0", Producto::class.java)
            val productos = query.resultList
            em.transaction.commit()
            return productos
        } catch (e: Exception) {
            consola.imprimirMensaje("Error: ${e.message}", true)
            em.transaction.rollback()
            return emptyList()
        } finally {
            em.close()
        }
    }
}