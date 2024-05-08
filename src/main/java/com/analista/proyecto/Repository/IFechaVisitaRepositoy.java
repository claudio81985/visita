package com.analista.proyecto.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.analista.proyecto.Model.FechaVisita;
import com.analista.proyecto.Model.Visita;

public interface IFechaVisitaRepositoy extends JpaRepository<FechaVisita, Long> {

    List<FechaVisita> findByVisita(Visita visita);

    List<FechaVisita> findByVisitaOrderByFechaDesc(Visita visita);

}
