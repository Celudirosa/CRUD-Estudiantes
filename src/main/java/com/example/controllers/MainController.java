package com.example.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.entities.Correo;
import com.example.entities.Curso;
import com.example.entities.Estudiante;
import com.example.entities.Horario;
import com.example.entities.Telefono;
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
        @RequestParam(name = "direccionesCorreo", required = false) String correosRecibidos,
        @RequestParam(name = "file", required = false) MultipartFile imagen) {

        if (!imagen.isEmpty()) {
            Path imageFolder = Path.of("src/main/resources/static/images");

            Path rutaAbsoluta = imageFolder.toAbsolutePath();

            Path rutaCompleta = Path.of(rutaAbsoluta + "/" + imagen.getOriginalFilename());

            try {
                byte[] byteImage = imagen.getBytes();
                Files.write(rutaCompleta, byteImage);

                estudiante.setFoto(imagen.getOriginalFilename());

            } catch (IOException e) {
            }
        }

        // procesar los telefonos
        if (telefonosRecibidos != null) {
            String[] arrayTelefonos = telefonosRecibidos.split(";");
            List<String> numerosTelefonos = Arrays.asList(arrayTelefonos);

            List<Telefono> telefonos = new ArrayList<>();
            
            numerosTelefonos.stream()
                .forEach(numeroTelefono -> {
                    telefonos.add(Telefono.builder()
                    .telefono(numeroTelefono)
                    .estudiante(estudiante)
                    .build());
                });

            estudiante.setTelefonos(telefonos);
        }

        // procesar los correos
        if (correosRecibidos != null) {
            String[] arrayCorreos = correosRecibidos.split(";");
            List<String> direccionesCorreos = Arrays.asList(arrayCorreos);

            List<Correo> correos = new ArrayList<>();
            
            direccionesCorreos.stream()
                .forEach(direccionCorreo -> {
                    correos.add(Correo.builder()
                    .correo(direccionCorreo)
                    .estudiante(estudiante)
                    .build());
                });
            
            estudiante.setCorreos(correos);
        }

        estudianteService.persistirEstudiante(estudiante);

        return "redirect:/all";
    }

    // eliminar el estudiante
    @GetMapping("/eliminar/{id}")
    @Transactional
    public String eliminarEstudiante(@PathVariable(name = "id", required = true)
        int idEstudiante) {
        
        estudianteService.eliminarEstudiante(idEstudiante);

        return "redirect:/all";
    }

    // actualizar el estudiante
    @GetMapping("/actualizar/{id}")
    @Transactional
    public String actualizarEstudiante(@PathVariable(name = "id", required = true) 
        int idEstudiante, Model model) {

        Estudiante estudiante = estudianteService.dameUnEstudiante(idEstudiante);
        model.addAttribute("estudiante", estudiante);

        List<Curso> cursos = cursoService.dameCursos();
        model.addAttribute("cursos", cursos);

        if (estudiante.getTelefonos() != null) {
            String numerosTelefono = estudiante.getTelefonos().stream()
                .map(Telefono::getTelefono)
                .collect(Collectors.joining(";"));

            model.addAttribute("numerosTelefono", numerosTelefono);
        }

        if (estudiante.getCorreos() != null) {
            String direccionesCorreos = estudiante.getCorreos().stream()
                .map(Correo::getCorreo)
                .collect(Collectors.joining(";"));

            model.addAttribute("direccionesCorreos", direccionesCorreos);
        }

        return "views/frmAltaModificacion";
    }

    // listado del turno DIURNO
    @GetMapping("/all-diurno/{idCurso}")
    public String dameEstudiantesDiurno(Model model) {
            
        List<Estudiante> estudiantesDiurno = cursoService.dameEstudiantesPorHorario(Horario.DIURNO);
        model.addAttribute("estudiantes", estudiantesDiurno);
        return "views/listadoEstudiantes";

    }

    // listado de alumnos por curso
    @GetMapping("/estudiantes/agrupados")
    public String obtenerEstudiantesAgrupadosPorCurso(Model model) {
        Map<Curso, List<Estudiante>> estudiantesAgrupados = estudianteService.dameTodosLosEstudiantes().stream()
            .collect(Collectors.groupingBy(Estudiante::getCurso));
        model.addAttribute("estudiantesAgrupados", estudiantesAgrupados);

        return "views/listaEstudiantesAgrupados";
    }


}
