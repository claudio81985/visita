package com.analista.proyecto.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.analista.proyecto.Model.Interno;
import com.analista.proyecto.Model.Modulo;

public interface IInternoRepository extends JpaRepository<Interno, Long> {

    @Query("select i from Interno i Where i.nombreApellido like %:criterio% or i.dni like %:criterio% and i.activo = true")
    List<Interno> buscarPor(@Param("criterio") String criterio);

    @Query("select i from Interno i Where i.activo = true")
    List<Interno> buscarSoloHabilitados();

    List<Interno> findByModulo(Modulo modulo);

}
