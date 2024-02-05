package com.example.controllers;

import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.services.EstudianteService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {

    private final EstudianteService estudianteService;

    private final Logger LOG = Logger.getLogger("MainController");

    // listado de todos los estudiantes
    @GetMapping("/all")
    public String dameEstudiantes(Model model) {
        
        model.addAttribute("estudiantes", estudianteService.dameTodosLosEstudiantes());
        
        return "views/listadoEstudiantes";
    }

    // detalles de cada estudiante por ID
    @GetMapping("/detalles/{id}")
    public String detallesEstudiante(@PathVariable(name = "id") int idEstudiante, Model model) {

        model.addAttribute(
            "estudiante", 
            estudianteService.dameUnEstudiante(idEstudiante));

        return "views/estudianteDetalles";
    }

}
