package org.example

import com.es.inputOutput.Consola
import com.es.service.ProveedorService
import jakarta.persistence.EntityManager
import jakarta.persistence.EntityManagerFactory
import jakarta.persistence.Persistence
import org.example.com.es.repository.ProductoRepository
import org.example.com.es.repository.ProveedorRepository
import org.example.com.es.repository.UsuarioRepository
import org.example.com.es.service.ProductoService
import org.example.com.es.service.UsuarioService

fun main() {
    val consola = Consola()

    // Repositorios
    val usuarioRepository: UsuarioRepository = UsuarioRepository(consola)
    val productoRepository: ProductoRepository = ProductoRepository(consola)
    val proveedorRepository: ProveedorRepository = ProveedorRepository(consola)

    // Servicios
    val usuarioService: UsuarioService = UsuarioService(usuarioRepository, consola)
    val productoService: ProductoService = ProductoService(productoRepository)
    val proveedorService: ProveedorService = ProveedorService(proveedorRepository)

    // Gestor de la app
    val appManager: AppManager = AppManager(
        consola,
        usuarioService,
        productoService,
        proveedorService
    )

    val emf: EntityManagerFactory = Persistence.createEntityManagerFactory("unidadMySQL")
    val em: EntityManager = emf.createEntityManager()

    appManager.app()

}