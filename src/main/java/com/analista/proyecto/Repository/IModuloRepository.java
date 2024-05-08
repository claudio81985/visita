package com.analista.proyecto.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.analista.proyecto.Model.Modulo;



public interface IModuloRepository extends JpaRepository<Modulo, Long> {
    
}
