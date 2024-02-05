package com.example.controllers;

import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entities.Estudiante;
import com.example.services.CursoService;
import com.example.services.EstudianteService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {

    private final EstudianteService estudianteService;
    private final CursoService cursoService;

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

    // formulario de alta
    @GetMapping("/frmAltaModificacion")
    public String formularioAltaModificacion(Model model) {

        Estudiante estudiante = new Estudiante();
        
        model.addAttribute("estudiante", estudiante);

        // tambien los departamentos
        model.addAttribute("cursos", 
            cursoService.dameCursos());
        
        return "views/frmAltaModificacion";
    }

    @PostMapping("/persistir")
    @Transactional
    public String persistirEstudiante (
        @ModelAttribute(name = "estudiante") Estudiante estudiante,
        @RequestParam(name = "numerosTel", required = false) String telefonosRecibidos,
        @RequestParam(name = "direccionesCorreo", required = false) String correosRecibidos) {

        
        return "redirect:/all";
    }

}
