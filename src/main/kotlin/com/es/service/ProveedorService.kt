package com.es.service

import org.example.com.es.model.Proveedor
import org.example.com.es.repository.ProveedorRepository

class ProveedorService(
    val repository: ProveedorRepository
) {
    fun createProveedor(nombre: String?, direccion: String?): Proveedor? {
        nombre?.length?.let {
            if(it <= 50) {
                if (direccion != null) {
                    return Proveedor(nombre, direccion)
                }
            }
        }
        return null
    }

    fun getProveedorProducto(idProducto: String): Proveedor? {
        return repository.getProveedorProducto(idProducto)
    }

    fun getTodosProveedores(): List<Proveedor?> {
        return repository.getTodosProveedores()
    }
}