package com.petcare1.crud_mvc.model;
// Define el paquete donde se encuentra la clase. Es importante para la organización y para que Spring pueda escanearla como entidad.

import jakarta.persistence.*;
// Importa todas las anotaciones y clases de JPA necesarias para mapear la clase a una tabla de base de datos.

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
// Importa anotaciones de Lombok que generan automáticamente código repetitivo:
// @Data: genera getters, setters, toString, equals y hashCode.
// @NoArgsConstructor: genera un constructor vacío.
// @AllArgsConstructor: genera un constructor con todos los atributos.

import java.time.LocalDateTime;
import java.time.LocalTime;
// Importa clases para manejar fechas y horas.

@Data
@NoArgsConstructor
@AllArgsConstructor
// Las anotaciones de Lombok explicadas arriba se aplican a la clase completa.

@Entity
// Marca la clase como entidad de JPA, es decir, que se mapeará a una tabla en la base de datos.

@Table(name = "COMIDA_GATITO")
// Especifica el nombre de la tabla en la base de datos. Si no se pone, JPA tomaría el nombre de la clase.

public class ComidaGatito {

    @Id
    // Indica que este campo es la clave primaria de la tabla.

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comida_seq")
    // Especifica que el valor del ID se generará automáticamente usando una secuencia de base de datos.

    @SequenceGenerator(name = "comida_seq", sequenceName = "COMIDA_GATITO_SEQ", allocationSize = 1)
    // Define la secuencia que se usará para generar los IDs. "allocationSize = 1" significa que se incrementa de uno en uno.

    private Long id;
    // Campo que almacena el ID único de cada registro.

    @Column(name = "NOMBRE_GATITO", nullable = false)
    // Define la columna en la tabla, su nombre y que no puede ser nula.
    private String nombreGatito;
    // Nombre del gatito.

    @Column(name = "HORA_COMIDA", nullable = false)
    // Columna que almacena la hora de la comida, obligatoria.
    private LocalTime horaComida;
    // Hora en la que se da la comida.

    @Column(name = "TIPO_COMIDA")
    // Columna que almacena el tipo de comida (opcional).
    private String tipoComida;

    @Column(name = "RESPONSABLE")
    // Columna que almacena el responsable de dar la comida (opcional).
    private String responsable;

    @Column(name = "FECHA_REGISTRO", nullable = false, updatable = false)
    // Columna para registrar cuándo se creó el registro. No puede ser nula y no se puede actualizar después.
    private LocalDateTime fechaRegistro;

    @PrePersist
    // Anotación de JPA que indica que el método se ejecutará **antes de insertar un nuevo registro** en la base de datos.
    public void prePersist() {
        fechaRegistro = LocalDateTime.now();
        // Asigna automáticamente la fecha y hora actual al crear un nuevo registro.
    }
}


