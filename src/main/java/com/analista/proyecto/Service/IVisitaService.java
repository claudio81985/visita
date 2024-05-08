package com.analista.proyecto.Service;
import java.util.List;

import com.analista.proyecto.Model.Interno;
import com.analista.proyecto.Model.Visita;


public interface IVisitaService  {

    public List<Visita> buscarTodo();

    public List<Visita> buscarPor(String criterio);

    public Visita buscarPorId(Long id);
    
    public Visita guardar(Visita visita);

    public List<Visita> obtenerVisitasPorInterno(Interno interno);
    
}