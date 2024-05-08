package com.analista.proyecto.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.analista.proyecto.Model.Interno;
import com.analista.proyecto.Model.Visita;

public interface IVisitaRepository extends JpaRepository<Visita, Long> {

    @Query("select v from Visita v Where v.apellido like %:criterio% or v.dni like %:criterio% and v.activo = true")
    List<Visita> buscarPor(@Param("criterio") String criterio);

    @Query("select v from Visita v Where v.activo = true")
    List<Visita> buscarSoloHabilitados();

    List<Visita> findByInterno(Interno interno);

}