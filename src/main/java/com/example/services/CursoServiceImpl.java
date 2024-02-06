package com.example.services;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dao.CursoDao;
import com.example.entities.Curso;
import com.example.entities.Estudiante;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CursoServiceImpl implements CursoService {
    
    private final CursoDao cursoDao;

    @Override
    public List<Curso> dameCursos() {
        return cursoDao.findAll();
    }

    @Override
    public Curso dameUnCurso(int idCurso) {
        return cursoDao.findById(idCurso).get();
    }

    @Override
    public void persistirCurso(Curso curso) {
        cursoDao.save(curso);
    }

    @Override
    public List<Estudiante> dameEstudiantesPorCurso(int idCurso) {
        Curso cursoDiurno = cursoDao.findById(idCurso).get();

        if (cursoDao != null) {
            Curso curso = cursoDiurno;
            return curso.getEstudiantes();
        } else {
            // Manejar el caso en el que no se encuentre el curso
            return Collections.emptyList();
        }
    }

}
