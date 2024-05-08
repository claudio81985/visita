package com.analista.proyecto.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.analista.proyecto.Model.Elemento;
import com.analista.proyecto.Model.Interno;

public interface IElementoRepository extends JpaRepository<Elemento, Long>{

    List<Elemento> findByInterno(Interno interno);

}
