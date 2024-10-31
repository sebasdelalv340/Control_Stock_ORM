package org.example.com.es.model

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import jakarta.persistence.Temporal
import jakarta.persistence.TemporalType
import java.util.Date


@Entity
@Table(name="productos")
class Producto(
    @Id
    @Column(name = "id_producto")
    val id: String,

    @Column(name = "categoria", nullable = false, length = 10)
    val categoria: String,

    @Column(name = "nombre", nullable = false, length = 10)
    val nombre: String,

    @Column(name = "descripcion")
    val descripcion: String,

    @Column(name = "precio_sin_iva", nullable = false)
    val precioSinIva: Float,

    @Column(name = "precio_con_iva", nullable = false)
    val precioConIva: Float,

    @Column(name = "fecha_alta", nullable = false)
    @Temporal(TemporalType.DATE)
    val fechaAlta: Date,

    @Column(name = "stock", nullable = false)
    val stock: Int,

    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "id_proveedor")
    val proveedor: Proveedor? = null
) {
    override fun toString(): String {
        return "\nProducto:\n" +
                "\t- ID: $id\n" +
                "\t- Categoría: $categoria\n" +
                "\t- Nombre: $nombre\n" +
                "\t- Descripción: $descripcion\n" +
                "\t- Precio sin IVA: $precioSinIva\n" +
                "\t- Precio con IVA: $precioConIva\n" +
                "\t- Fecha de alta: $fechaAlta\n" +
                "\t- Stock: $stock\n" +
                "\t- Proveedor: ${proveedor?.nombre}\n"
    }
}
