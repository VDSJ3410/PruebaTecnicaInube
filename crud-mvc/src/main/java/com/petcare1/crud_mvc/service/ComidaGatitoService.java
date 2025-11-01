package com.petcare1.crud_mvc.service;
// Define el paquete donde se encuentra el service. Sirve para organizar la aplicación y que Spring lo detecte como componente.

import com.petcare1.crud_mvc.model.ComidaGatito;
// Importa la clase de entidad que vamos a manipular.

import com.petcare1.crud_mvc.repository.ComidaGatitoRepository;
// Importa el repositorio para acceder a la base de datos.

import org.springframework.stereotype.Service;
// Importa la anotación @Service para indicar que esta clase es un **componente de servicio** en Spring.

import java.util.List;
import java.util.Optional;
// Importa clases de utilidad de Java: List para colecciones y Optional para valores que pueden estar vacíos.

@Service
// Marca la clase como un **bean de Spring de tipo servicio**, lo que permite inyección automática en controladores o en otros servicios.

public class ComidaGatitoService {

    private final ComidaGatitoRepository repository;
    // Declaración del repositorio como atributo final para usarlo dentro de la clase.
    // Es final porque se asigna una sola vez en el constructor.

    public ComidaGatitoService(ComidaGatitoRepository repository){
        this.repository = repository;
    }
    // Constructor que recibe el repositorio. Permite inyección de dependencias de Spring.
    // Así, el service puede acceder a los métodos CRUD del repositorio.

    public List<ComidaGatito> listarTodas(){
        return repository.findAll();
    }
    //Método para listar todos los registros de la tabla `COMIDA_GATITO`.
    // `findAll()` viene de JpaRepository y devuelve todos los registros como lista.

    public Optional<ComidaGatito> buscarPorId(Long id){
        return repository.findById(id);
    }
    // Método para buscar un registro por su ID.
    // Devuelve un Optional que puede contener un objeto o estar vacío si no existe.

    public ComidaGatito guardar(ComidaGatito comida){
        if (comida.getNombreGatito() == null || comida.getHoraComida() == null){
            throw new IllegalArgumentException("El nombre del gatito y la hora de comida son obligatorios.");
        }
        return repository.save(comida);
    }
    // Método para guardar un nuevo registro.
    // Valida que los campos `nombreGatito` y `horaComida` no sean nulos.
    // `save()` persiste el objeto en la base de datos.

    public ComidaGatito actualizar(Long id, ComidaGatito comidaActualizada){
        return repository.findById(id).map(comida -> {
            comida.setNombreGatito(comidaActualizada.getNombreGatito());
            comida.setHoraComida(comidaActualizada.getHoraComida());
            comida.setTipoComida(comidaActualizada.getTipoComida());
            comida.setResponsable(comidaActualizada.getResponsable());
            return repository.save(comida);
        }).orElseThrow(() -> new RuntimeException("Registro no encontrado"));
    }
    // Método para actualizar un registro existente:
    // - Busca el registro por ID.
    // - Si existe, actualiza los campos con los valores de `comidaActualizada`.
    // - Guarda los cambios con `save()`.
    // - Si no existe el registro, lanza un RuntimeException.

    public void eliminar(Long id){
        if (!repository.existsById(id)) {
            throw new RuntimeException("Registro no encontrado");
        }
        repository.deleteById(id);
    }
    // Método para eliminar un registro por su ID:
    // - Primero verifica si existe el registro.
    // - Si existe, lo elimina.
    // - Si no, lanza un RuntimeException.
}
