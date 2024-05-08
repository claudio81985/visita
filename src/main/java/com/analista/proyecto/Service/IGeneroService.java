package com.analista.proyecto.Service;

import java.util.List;

import com.analista.proyecto.Model.Genero;


public interface IGeneroService {
    
     public void guardar(Genero genero);

    public List<Genero> buscarTodos();

    public List<Genero> buscarPor(String criterio);

    public Genero buscarPorId(Long id);
}
