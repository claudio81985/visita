package com.analista.proyecto.Service;

import java.util.List;

import com.analista.proyecto.Model.FechaVisita;
import com.analista.proyecto.Model.Visita;


public interface IFechaVisitaService {

     public void guardar(FechaVisita fechaVisita);

    public List<FechaVisita> buscarTodos();

    public List<FechaVisita> buscarPor(String criterio);

    public FechaVisita buscarPorId(Long id);

     public List<FechaVisita> obtenerFechaPorVisita(Visita visitas);

     public FechaVisita obtenerUltimaFechaPorVisita(Visita visita);


}
