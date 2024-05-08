package com.analista.proyecto.Service;

import java.util.List;

import com.analista.proyecto.Model.Modulo;



public interface IModuloService {
    
     public void guardar(Modulo Modulo);

    public List<Modulo> buscarTodos();

    public List<Modulo> buscarPor(String criterio);

    public Modulo buscarPorId(Long id);
}
