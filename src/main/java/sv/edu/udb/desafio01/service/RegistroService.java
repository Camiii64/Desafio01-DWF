package sv.edu.udb.desafio01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sv.edu.udb.desafio01.entity.Registro;
import sv.edu.udb.desafio01.repository.RegistroRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class RegistroService {

    private final RegistroRepository registroRepository;

    @Autowired
    public RegistroService(RegistroRepository registroRepository) {
        this.registroRepository = registroRepository;
    }

    // Insertar 100 registros
    public void insertarRegistros() {
        List<Registro> registros = IntStream.rangeClosed(1, 100)
                .mapToObj(i -> Registro.builder()
                        .nombre("Nombre " + i)
                        .descripcion("Descripción " + i)
                        .build())
                .collect(Collectors.toList());
        registroRepository.saveAll(registros);
    }

    // Contar registros
    public long contarRegistros() {
        return registroRepository.count();
    }

    // Leer primeros 25 registros
    public List<Registro> leerPrimeros25() {
        return registroRepository.findAll()
                .stream()
                .limit(25)
                .collect(Collectors.toList());
    }

    // Modificar registro con ID especifico
    public void modificarRegistro(Long id, String nuevoNombre, String nuevaDescripcion) {
        Optional<Registro> registroOpt = registroRepository.findById(id);
        if (registroOpt.isPresent()) {
            Registro registro = registroOpt.get();
            registro.setNombre(nuevoNombre);
            registro.setDescripcion(nuevaDescripcion);
            registroRepository.save(registro);
        }
    }

    // Obtener registro por ID
    public Registro obtenerRegistro(Long id) {
        return registroRepository.findById(id).orElse(null);
    }

    // Borrar registro por ID
    public void borrarRegistro(Long id) {
        registroRepository.deleteById(id);
    }
}
