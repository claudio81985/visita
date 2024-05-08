package com.analista.proyecto.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.analista.proyecto.Model.Genero;

public interface IGeneroRepository extends JpaRepository<Genero, Long> {
    
}
