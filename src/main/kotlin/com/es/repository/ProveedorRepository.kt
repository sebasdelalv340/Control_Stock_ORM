package org.example.com.es.repository

import com.es.inputOutput.Consola
import org.example.com.es.model.Producto
import org.example.com.es.model.Proveedor
import org.example.com.es.service.EntityManagerService

class ProveedorRepository(val consola: Consola) {

    fun createProveedor(proveedor: Proveedor) {
        val em = EntityManagerService.createEM()
        try {
            em.transaction.begin()
            em.persist(proveedor)
            em.transaction.commit()
            consola.imprimirMensaje("Proveedor creado", true)
        } catch (e: Exception) {
            consola.imprimirMensaje("Error: ${e.message}", true)
            em.transaction.rollback()
        } finally {
            em.close()
        }
    }


    fun readProducto(nombreProveedor: String): Proveedor? {
        val em = EntityManagerService.createEM()
        try {
            em.transaction.begin()
            val proveedorFromBD: Proveedor = em.find(Proveedor::class.java, nombreProveedor)
            if (proveedorFromBD != null) {
                em.transaction.commit()
                em.close()
                return proveedorFromBD
            } else {
                consola.imprimirMensaje("Proveedor no encontrado", true)
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


    fun updateProveedor(proveedor: Proveedor) {
        val em = EntityManagerService.createEM()
        try {
            em.transaction.begin()
            val existeProveedor = em.find(Proveedor::class.java, proveedor.id)
            if (existeProveedor != null) {
                em.merge(proveedor)
                em.transaction.commit()
                consola.imprimirMensaje("Proveedor actualizado", true)
            } else {
                consola.imprimirMensaje("Proveedor no encontrado", true)
            }
        } catch (e: Exception) {
            consola.imprimirMensaje("Error: ${e.message}", true)
            em.transaction.rollback()
        } finally {
            em.close()
        }
    }


    fun deleteProveedor(proveedor: Proveedor) {
        val em = EntityManagerService.createEM()
        try {
            em.transaction.begin()
            val existeProveedor = em.find(Proveedor::class.java, proveedor.id)
            if(existeProveedor != null) {
                em.remove(existeProveedor)
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

    fun getProveedorProducto(idProducto: String): Proveedor? {
        val em = EntityManagerService.createEM()
        try {
            em.transaction.begin()
            val productoFromBD: Producto = em.find(Producto::class.java, idProducto)
            if (productoFromBD != null) {
                val proveedorFromBD: Proveedor = em.find(Proveedor::class.java, productoFromBD.proveedor)
                em.transaction.commit()
                em.close()
                return proveedorFromBD
            } else {
                consola.imprimirMensaje("Proveedor no encontrado", true)
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

    fun getTodosProveedores(): List<Proveedor?> {
        val em = EntityManagerService.createEM()
        try {
            em.transaction.begin()
            val query = em.createQuery("SELECT p FROM Proveedor p", Proveedor::class.java)
            val proveedores = query.resultList
            em.transaction.commit()
            return proveedores
        } catch (e: Exception) {
            consola.imprimirMensaje("Error: ${e.message}", true)
            em.transaction.rollback()
            return emptyList()
        } finally {
            em.close()
        }
    }
}