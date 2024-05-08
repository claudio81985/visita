package com.analista.proyecto.Service;

import java.util.List;

import com.analista.proyecto.Model.Parentesco;


public interface IParentescoService {

    public void guardar(Parentesco parentesco);

    public List<Parentesco> buscarTodo();

    public List<Parentesco> buscarPor(String criterio);

    public Parentesco buscarPorId(Long id);


}
