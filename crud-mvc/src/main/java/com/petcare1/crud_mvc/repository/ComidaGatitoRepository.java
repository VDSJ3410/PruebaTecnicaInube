package com.petcare1.crud_mvc.repository;
// Define el paquete donde se encuentra la interfaz. Sirve para organizar el proyecto y que Spring la detecte correctamente.

import com.petcare1.crud_mvc.model.ComidaGatito;
// Importa la clase de entidad `ComidaGatito` que vamos a manejar en el repositorio.

import org.springframework.data.jpa.repository.JpaRepository;
// Importa la interfaz `JpaRepository` de Spring Data JPA, que proporciona métodos CRUD listos para usar (save, findAll, findById, delete, etc.).

import org.springframework.stereotype.Repository;
// Importa la anotación `@Repository` que indica que esta interfaz es un componente de acceso a datos (DAO).

@Repository
// Marca la interfaz como un **bean de Spring** de tipo repositorio. Esto permite la inyección automática en servicios y controladores.

public interface ComidaGatitoRepository extends JpaRepository<ComidaGatito, Long> {
    // Extiende JpaRepository:
    // - `ComidaGatito`: la entidad que maneja.
    // - `Long`: el tipo de dato de la clave primaria (ID) de la entidad.
    // Esto permite usar todos los métodos CRUD y de paginación sin escribir código adicional.
}

