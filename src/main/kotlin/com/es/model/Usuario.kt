package org.example.com.es.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name ="usuarios")
data class Usuario(
    @Id
    @Column(name = "nombre")
    val nombreUsuario: String,

    @Column(name="password", nullable = false, length = 20)
    val password: String
)
