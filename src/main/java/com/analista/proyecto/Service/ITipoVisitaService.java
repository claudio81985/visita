package com.analista.proyecto.Service;

import java.util.List;

import com.analista.proyecto.Model.TipoVisita;

public interface ITipoVisitaService {

    public void guardar (TipoVisita tipoVisita);


     public List<TipoVisita> buscarTodos();

    public List<TipoVisita> buscarPor(String criterio);

    public TipoVisita buscarPorId(Long id);


}
