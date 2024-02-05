package com.example;

import java.time.LocalDate;
import java.time.Month;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.entities.Correo;
import com.example.entities.Curso;
import com.example.entities.Estudiante;
import com.example.entities.Genero;
import com.example.entities.Horario;
import com.example.entities.Telefono;
import com.example.services.CorreoService;
import com.example.services.CursoService;
import com.example.services.EstudianteService;
import com.example.services.TelefonoService;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class CrudEstudiantesApplication implements CommandLineRunner {

	private final CursoService cursoService;
	private final EstudianteService estudianteService;
	private final TelefonoService telefonoService;
	private final CorreoService correoService;

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
			.fechaAlta(LocalDate.of(2000, Month.DECEMBER, 23))
			.totalAsignaturas(8)
			.foto("Foto de Celia")
			.curso(cursoService.dameUnCurso(1))
			.build();

		Estudiante est2 = Estudiante.builder()
			.nombre("Isabel")
			.primerApellido("Álvarez")
			.segundoApellido("Díaz")
			.genero(Genero.MUJER)
			.fechaAlta(LocalDate.of(2000, Month.DECEMBER, 23))
			.totalAsignaturas(8)
			.foto("Foto de Isabel")
			.curso(cursoService.dameUnCurso(1))
			.build();

		estudianteService.persistirEstudiante(est1);
		estudianteService.persistirEstudiante(est2);

		// creamos los telefonos
		Telefono telefono1Estudiante1 = Telefono.builder()
			.telefono("639147900")
			.estudiante(estudianteService.dameUnEstudiante(1))
			.build();

		Telefono telefono2Estudiante1 = Telefono.builder()
			.telefono("675060388")
			.estudiante(estudianteService.dameUnEstudiante(1))
			.build();

		Telefono telefono1Estudiante2 = Telefono.builder()
			.telefono("765936578")
			.estudiante(estudianteService.dameUnEstudiante(1))
			.build();

		telefonoService.persistirTelefono(1, telefono1Estudiante1);
		telefonoService.persistirTelefono(1, telefono2Estudiante1);
		telefonoService.persistirTelefono(2, telefono1Estudiante2);

		// creamos los correos
		Correo correo1Empleado1 = Correo.builder()
			.correo("celia@mola.com")
			.build();

		Correo correo2Empleado1 = Correo.builder()
			.correo("celudirosa@mola.com")
			.build();

		Correo correo1Empleado2 = Correo.builder()
			.correo("isabel@mola.com")
			.build();

		correoService.persistirCorreo(1, correo1Empleado1);
		correoService.persistirCorreo(1, correo2Empleado1);
		correoService.persistirCorreo(2, correo1Empleado2);


	}

}