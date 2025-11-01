package com.petcare1.crud_mvc.controller;
// Define el paquete donde se encuentra el controlador, para organizar la aplicación y que Spring lo detecte.

import com.petcare1.crud_mvc.model.ComidaGatito;
// Importa la clase de entidad que se va a usar en las vistas y formularios.

import com.petcare1.crud_mvc.service.ComidaGatitoService;
// Importa el servicio que contiene la lógica de negocio para manejar comidas de gatitos.

import org.springframework.stereotype.Controller;
// Importa la anotación @Controller para indicar que esta clase maneja solicitudes HTTP y devuelve vistas.

import org.springframework.ui.Model;
// Importa Model, que sirve para pasar datos de la clase Java a la vista (HTML).

import org.springframework.web.bind.annotation.*;
// Importa anotaciones para mapear rutas HTTP como @GetMapping, @PostMapping, @PathVariable y @ModelAttribute.

@Controller
// Marca la clase como controlador de Spring MVC, lo que permite manejar solicitudes web y devolver vistas.

@RequestMapping("/comidas")
// Define la ruta base para todas las solicitudes dentro de este controlador.
// Por ejemplo, "/comidas", "/comidas/nuevo", "/comidas/editar/{id}", etc.

public class ComidaGatitoController {

    private final ComidaGatitoService service;
    // Servicio inyectado para poder acceder a los métodos de negocio (CRUD) de comidas de gatitos.

    public ComidaGatitoController(ComidaGatitoService service) {
        this.service = service;
    }
    // Constructor que recibe el servicio. Spring lo inyecta automáticamente al crear el bean.

    // Listar todas las comidas
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("comidas", service.listarTodas());
        return "lista"; // coincide con lista.html
    }
    // Método que maneja solicitudes GET a "/comidas":
    // - Obtiene todas las comidas usando el servicio.
    // - Agrega la lista al modelo para la vista con el atributo "comidas".
    // - Devuelve el nombre de la plantilla HTML "lista.html".

    // Mostrar formulario para agregar nueva comida
    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("comida", new ComidaGatito());
        return "form"; // coincide con form.html
    }
    // Método que maneja GET a "/comidas/nuevo":
    // - Crea un objeto vacío de ComidaGatito para llenar el formulario.
    // - Lo agrega al modelo con el nombre "comida".
    // - Devuelve la vista "form.html" para mostrar el formulario.

    // Guardar nueva comida
    @PostMapping
    public String guardar(@ModelAttribute("comida") ComidaGatito comida) {
        service.guardar(comida);
        return "redirect:/comidas";
    }
    // Método que maneja POST a "/comidas":
    // - Recibe los datos del formulario mapeados a un objeto ComidaGatito.
    // - Llama al servicio para guardar la nueva comida.
    // - Redirige a la lista de comidas con "redirect:/comidas".

    // Mostrar formulario para editar
    @GetMapping("/editar/{id}")
    public String mostrarEditar(@PathVariable Long id, Model model) {
        ComidaGatito comida = service.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));
        model.addAttribute("comida", comida);
        return "form";
    }
    // Método que maneja GET a "/comidas/editar/{id}":
    // - Obtiene la comida por ID usando el servicio.
    // - Si no existe, lanza excepción.
    // - Agrega la comida al modelo para rellenar el formulario.
    // - Devuelve la vista "form.html" para editar.

    // Guardar cambios de edición
    @PostMapping("/{id}")
    public String actualizar(@PathVariable Long id, @ModelAttribute("comida") ComidaGatito comidaActualizada) {
        service.actualizar(id, comidaActualizada);
        return "redirect:/comidas";
    }
    // Método que maneja POST a "/comidas/{id}":
    // - Recibe los datos actualizados del formulario.
    // - Llama al servicio para actualizar la comida existente.
    // - Redirige nuevamente a la lista de comidas.

    // Eliminar comida
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return "redirect:/comidas";
    }
    // Método que maneja GET a "/comidas/eliminar/{id}":
    // - Llama al servicio para eliminar el registro por ID.
    // - Redirige a la lista de comidas.
}




