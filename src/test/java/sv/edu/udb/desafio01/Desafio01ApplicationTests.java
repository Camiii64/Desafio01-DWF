package sv.edu.udb.desafio01;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sv.edu.udb.desafio01.entity.Registro;
import sv.edu.udb.desafio01.service.RegistroService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Desafio01ApplicationTests {

	@Autowired
	private RegistroService registroService;

	// 🔹 Prueba #1 Insertar 100 registros
	@Test
	@Order(1)
	void insertar_registros_test() {
		registroService.insertarRegistros();
		long total = registroService.contarRegistros();
		assertEquals(100, total);
	}

	// 🔹 Prueba #2 Leer primeros 25 registros
	@Test
	@Order(2)
	void leer_registros_test() {
		List<Registro> lista = registroService.leerPrimeros25();
		assertEquals(25, lista.size());
	}

	// 🔹 Prueba #3 Modificar registro ID=50
	@Test
	@Order(3)
	void modificar_registro_test() {

		registroService.modificarRegistro(50L,
				"Nombre Modificado",
				"Descripción Modificada");

		Registro registro = registroService.obtenerRegistro(50L);

		assertEquals("Nombre Modificado", registro.getNombre());
	}

	// 🔹 Prueba #4 Borrar registro ID=75
	@Test
	@Order(4)
	void borrar_registro_test() {

		registroService.borrarRegistro(75L);

		Registro registro = registroService.obtenerRegistro(75L);

		assertNull(registro);
	}
}