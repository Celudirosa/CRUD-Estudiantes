package com.example.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entities.Curso;
import com.example.entities.Estudiante;

@Repository
public interface EstudianteDao extends JpaRepository<Estudiante, Integer> {

    List<Estudiante> findByNombre(String nombre);

    @Query("SELECT e.curso, COLLECT(e) FROM Estudiante e GROUP BY e.curso")
    // "SELECT e.curso, COLLECT(e) FROM Estudiante e GROUP BY e.curso": Esto
    // es una consulta JPQL. Aquí, e es un alias para la entidad Estudiante. 
    // Estás seleccionando dos cosas: e.curso (el curso al que pertenece el 
    // estudiante) y COLLECT(e) (una colección de estudiantes). Luego, estás
    // agrupando los resultados por e.curso.
    // Map<Curso, List<Estudiante>>: Indica que el resultado de esta 
    // consulta será un Map donde las claves son instancias de la entidad 
    // Curso y los valores son listas de estudiantes (List<Estudiante>).
    Map<Curso, List<Estudiante>> obtenerEstudiantesAgrupadosPorCurso();

}
