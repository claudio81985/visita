package com.analista.proyecto.Service;

import java.util.List;

import com.analista.proyecto.Model.Interno;
import com.analista.proyecto.Model.Modulo;



public interface IInternoService {
    
    public List<Interno> buscarTodo();

    public List<Interno> buscarPor(String criterio);

    public Interno buscarPorId(Long id);

    public Interno guardar(Interno interno);

    void borrar(Interno interno);

     List<Interno> buscarPorModulo(Modulo modulo);


}
