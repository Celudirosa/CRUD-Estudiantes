package com.example;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

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

		// cursoService.persistirCurso(curso1);
		// cursoService.persistirCurso(curso2);
		// cursoService.persistirCurso(curso3);
		// cursoService.persistirCurso(curso4);

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

		Estudiante est3 = Estudiante.builder()
			.nombre("Amparo")
			.primerApellido("Martinez")
			.segundoApellido("Salazar")
			.genero(Genero.MUJER)
			.fechaAlta(LocalDate.of(2000, Month.DECEMBER, 23))
			.totalAsignaturas(8)
			.foto("Foto de Amparo")
			.curso(cursoService.dameUnCurso(2))
			.build();

		Estudiante est4 = Estudiante.builder()
			.nombre("Martina")
			.primerApellido("Scanzzani")
			.genero(Genero.MUJER)
			.fechaAlta(LocalDate.of(2000, Month.DECEMBER, 23))
			.totalAsignaturas(8)
			.foto("Foto de Martina")
			.curso(cursoService.dameUnCurso(2))
			.build();
			
		Estudiante est5 = Estudiante.builder()
			.nombre("Ana")
			.primerApellido("Gomez")
			.segundoApellido("Lavado")
			.genero(Genero.MUJER)
			.fechaAlta(LocalDate.of(2000, Month.DECEMBER, 23))
			.totalAsignaturas(8)
			.foto("Foto de Ana")
			.curso(cursoService.dameUnCurso(3))
			.build();

		Estudiante est6 = Estudiante.builder()
			.nombre("Laura")
			.primerApellido("Garrido")
			.genero(Genero.MUJER)
			.fechaAlta(LocalDate.of(2000, Month.DECEMBER, 23))
			.totalAsignaturas(8)
			.foto("Foto de Laura")
			.curso(cursoService.dameUnCurso(3))
			.build();

		Estudiante est7 = Estudiante.builder()
			.nombre("Juan")
			.primerApellido("Gomez")
			.segundoApellido("Lavado")
			.genero(Genero.HOMBRE)
			.fechaAlta(LocalDate.of(2000, Month.DECEMBER, 23))
			.totalAsignaturas(8)
			.foto("Foto de Juan")
			.curso(cursoService.dameUnCurso(4))
			.build();

		Estudiante est8 = Estudiante.builder()
			.nombre("Adrian")
			.primerApellido("Garrido")
			.genero(Genero.HOMBRE)
			.fechaAlta(LocalDate.of(2000, Month.DECEMBER, 23))
			.totalAsignaturas(8)
			.foto("Foto de Adrian")
			.curso(cursoService.dameUnCurso(4))
			.build();

		// estudianteService.persistirEstudiante(est1);
		// estudianteService.persistirEstudiante(est2);
		// estudianteService.persistirEstudiante(est3);
		// estudianteService.persistirEstudiante(est4);
		// estudianteService.persistirEstudiante(est5);
		// estudianteService.persistirEstudiante(est6);
		// estudianteService.persistirEstudiante(est7);
		// estudianteService.persistirEstudiante(est8);

		// creamos los telefonos
		List<Telefono> telefonosEstudiante1 = new ArrayList<>(); 

		Telefono telefono1Estudiante1 = Telefono.builder()
			.telefono("639147900")
			.estudiante(est1)
			.build();

		Telefono telefono2Estudiante1 = Telefono.builder()
			.telefono("675060388")
			.estudiante(est1)
			.build();

		List<Telefono> telefonosEstudiante2 = new ArrayList<>(); 

		Telefono telefono1Estudiante2 = Telefono.builder()
			.telefono("765936578")
			.estudiante(est2)
			.build();

		// telefonoService.persistirTelefono(1, telefono1Estudiante1);
		// telefonoService.persistirTelefono(1, telefono2Estudiante1);
		// telefonoService.persistirTelefono(2, telefono1Estudiante2);

		telefonosEstudiante1.add(telefono1Estudiante1);
		telefonosEstudiante1.add(telefono2Estudiante1);

		telefonosEstudiante2.add(telefono1Estudiante2);

		est1.setTelefonos(telefonosEstudiante1);
		est2.setTelefonos(telefonosEstudiante2);

		// creamos los correos
		List<Correo> correosEstudiante1 = new ArrayList<>();

		Correo correo1Estudiante1 = Correo.builder()
			.correo("celia@mola.com")
			.build();

		Correo correo2Estudiante1 = Correo.builder()
				.correo("celudirosa@mola.com")
				.build();

		correosEstudiante1.add(correo1Estudiante1);
		correosEstudiante1.add(correo2Estudiante1);

		est1.setCorreos(correosEstudiante1);

		// correos estudiante 2
		List<Correo> correosEstudiante2 = new ArrayList<>();

		Correo correo1Estudiante2 = Correo.builder()
			.correo("isabel@mola.com")
			.build();

		correosEstudiante2.add(correo1Estudiante2);

		// correos estudiante 3
		List<Correo> correosEstudiante3 = new ArrayList<>();

		Correo correo1Estudiante3 = Correo.builder()
			.correo("amparo@mola.com")
			.build();
		
		correosEstudiante3.add(correo1Estudiante3);

		// correos estudiante 4
		List<Correo> correosEstudiante4 = new ArrayList<>();

		Correo correo1Estudiante4 = Correo.builder()
			.correo("martina@mola.com")
			.build();

		correosEstudiante4.add(correo1Estudiante4);

		// correos estudiante 5
		List<Correo> correosEstudiante5 = new ArrayList<>();

		Correo correo1Estudiante5 = Correo.builder()
			.correo("ana@mola.com")
			.build();

		correosEstudiante5.add(correo1Estudiante5);

		// correos estudiante 6
		List<Correo> correosEstudiante6 = new ArrayList<>();

		Correo correo1Estudiante6 = Correo.builder()
			.correo("laura@mola.com")
			.build();

		correosEstudiante6.add(correo1Estudiante6);

		// correos estudiante 7
		List<Correo> correosEstudiante7 = new ArrayList<>();

		Correo correo1Estudiante7 = Correo.builder()
			.correo("juan@mola.com")
			.build();
		
		correosEstudiante7.add(correo1Estudiante7);

		// correos estudiante 8
		List<Correo> correosEstudiante8 = new ArrayList<>();

		Correo correo1Estudiante8 = Correo.builder()
			.correo("adrian@mola.com")
			.build();

		correosEstudiante8.add(correo1Estudiante8);

		// correoService.persistirCorreo(1, correo1Estudiante1);
		// correoService.persistirCorreo(1, correo2Estudiante1);
		// correoService.persistirCorreo(2, correo1Estudiante2);
		// correoService.persistirCorreo(3, correo1Estudiante3);
		// correoService.persistirCorreo(4, correo1Estudiante4);
		// correoService.persistirCorreo(5, correo1Estudiante5);
		// correoService.persistirCorreo(6, correo1Estudiante6);
		// correoService.persistirCorreo(7, correo1Estudiante7);
		// correoService.persistirCorreo(8, correo1Estudiante8);

		est1.setCorreos(correosEstudiante1);
		est2.setCorreos(correosEstudiante2);
		est3.setCorreos(correosEstudiante3);
		est4.setCorreos(correosEstudiante4);
		est5.setCorreos(correosEstudiante5);
		est6.setCorreos(correosEstudiante6);
		est7.setCorreos(correosEstudiante7);
		est8.setCorreos(correosEstudiante8);

		estudianteService.persistirEstudiante(est1);
		estudianteService.persistirEstudiante(est2);
		estudianteService.persistirEstudiante(est3);
		estudianteService.persistirEstudiante(est4);
		estudianteService.persistirEstudiante(est5);
		estudianteService.persistirEstudiante(est6);
		estudianteService.persistirEstudiante(est7);
		estudianteService.persistirEstudiante(est8);

	}

}