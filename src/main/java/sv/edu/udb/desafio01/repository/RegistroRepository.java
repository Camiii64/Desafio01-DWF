package sv.edu.udb.desafio01.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sv.edu.udb.desafio01.entity.Registro;

@Repository
public interface RegistroRepository extends JpaRepository<Registro, Long> {

    // Buscar por nombre exacto
    Optional<Registro> findByNombre(String nombre);

    //  Buscar por nombre que contenga texto
    List<Registro> findByNombreContaining(String texto);

    //  Buscar por descripción
    List<Registro> findByDescripcionContaining(String descripcion);

    //  Contar registros por nombre específico
    long countByNombre(String nombre);

    //  Eliminar por nombre
    void deleteByNombre(String nombre);

    //  Consulta personalizada JPQL
    @Query("SELECT r FROM Registro r WHERE r.id > :id")
    List<Registro> findRegistrosConIdMayorA(@Param("id") Long id);

  
}