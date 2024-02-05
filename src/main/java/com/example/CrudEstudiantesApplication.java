package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.entities.Curso;
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
			.descripcion("Curso de Java")
			.horario(Horario.DIURNO)
			.build();

		cursoService.persistirCurso(curso1);
	}


}
