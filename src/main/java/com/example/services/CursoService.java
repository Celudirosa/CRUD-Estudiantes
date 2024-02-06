package com.example.services;

import java.util.List;

import com.example.entities.Curso;
import com.example.entities.Estudiante;

public interface CursoService {

    public List<Curso> dameCursos();
    public Curso dameUnCurso(int idCurso);
    public void persistirCurso(Curso curso);
    public List<Estudiante> dameEstudiantesPorCurso(int idCurso);

}
