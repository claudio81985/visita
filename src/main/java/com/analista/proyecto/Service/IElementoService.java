package com.analista.proyecto.Service;

import java.util.List;

import com.analista.proyecto.Model.Elemento;
import com.analista.proyecto.Model.Interno;


public interface IElementoService {

    public Elemento guardar(Elemento elemento);

     public List<Elemento> buscarTodos();

    public List<Elemento> buscarPor(String criterio);

    public Elemento buscarPorId(Long id);

    public List<Elemento> obtenerElementosPorInterno(Interno interno);


}
