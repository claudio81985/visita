package com.analista.proyecto.Service;

import java.util.List;

import com.analista.proyecto.Model.Motivo;

public interface IMotivoService {

    public void guardar(Motivo motivo);

    public List<Motivo> buscarTodos();

    public List<Motivo> buscarPor(String criterio);

    public Motivo buscarPorId(Long id);
    

}
