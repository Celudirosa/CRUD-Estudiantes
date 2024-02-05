package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.entities.Curso;
import com.example.entities.Estudiante;
import com.example.entities.Genero;
import com.example.entities.Horario;
import com.example.services.CursoService;
import com.example.services.EstudianteService;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class CrudEstudiantesApplication implements CommandLineRunner {

	private final CursoService cursoService;
	private final EstudianteService estudianteService;

	public static void main(String[] args) {
		SpringApplication.run(CrudEstudiantesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		// creamos los cursos
		Curso curso1 = Curso.builder()
			.descripcion("JAVA")
			.horario(Horario.DIURNO)
			.build();

		Curso curso2 = Curso.builder()
			.descripcion("FRANCÉS")
			.horario(Horario.DIURNO)
			.build();

		Curso curso3 = Curso.builder()
			.descripcion("JAVA")
			.horario(Horario.NOCTURNO)
			.build();

		Curso curso4 = Curso.builder()
			.descripcion("FRANCÉS")
			.horario(Horario.NOCTURNO)
			.build();

		cursoService.persistirCurso(curso1);
		cursoService.persistirCurso(curso2);
		cursoService.persistirCurso(curso3);
		cursoService.persistirCurso(curso4);

		// creamos los estudiantes
		Estudiante est1 = Estudiante.builder()
			.nombre("Celia")
			.primerApellido("Luque")
			.segundoApellido("Díaz")
			.genero(Genero.MUJER)
			.curso(cursoService.dameUnCurso(1))
			.build();

		estudianteService.persistirEstudiante(est1);
	}


}