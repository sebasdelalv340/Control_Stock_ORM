package org.example.com.es.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table


@Entity
@Table(name="proveedores")
data class Proveedor(

    @Column(name = "nombre", nullable = false, unique = true, length = 50)
    val nombre: String,

    @Column(name = "direccion", nullable = false)
    val direccion: String,

    @OneToMany(mappedBy = "proveedor")
    val productos: List<Producto>? = null,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
) {
    override fun toString(): String {
        return "\nProveedor:\n" +
                "\t- ID: $id\n" +
                "\t- Nombre: $nombre\n" +
                "\t- Dirección: $direccion\n" +
                "\t- Productos: $productos\n"
    }
}
