package com.example.services;

import java.util.List;
import java.util.Map;

import com.example.entities.Curso;
import com.example.entities.Estudiante;

public interface EstudianteService {

    public List<Estudiante> dameTodosLosEstudiantes();
    public Estudiante dameUnEstudiante(int idEstudiante);
    public void eliminarEstudiante(int idEstudiante);
    public void persistirEstudiante(Estudiante estudiante);
    public void actualizarEstudiante(Estudiante estudiante);
    Map<Curso, List<Estudiante>> obtenerEstudiantesAgrupadosPorCurso();
}
