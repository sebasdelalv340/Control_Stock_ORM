package com.es.service

import org.example.com.es.model.Proveedor

class ProveedorService {
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

    fun getProveedorProducto(){}

    fun getTodosProveedores(){
    }
}