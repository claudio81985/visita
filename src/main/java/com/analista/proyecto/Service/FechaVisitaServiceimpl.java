package com.analista.proyecto.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.analista.proyecto.Model.FechaVisita;
import com.analista.proyecto.Model.Visita;
import com.analista.proyecto.Repository.IFechaVisitaRepositoy;

@Service
public class FechaVisitaServiceimpl implements IFechaVisitaService {

    @Autowired
    IFechaVisitaRepositoy fechaVisitaRepositoy;

    @Override
    public void guardar(FechaVisita fechaVisita) {
        fechaVisitaRepositoy.save(fechaVisita);
    }

    @Override
    public List<FechaVisita> buscarTodos() {
        return fechaVisitaRepositoy.findAll();
    }

    @Override
    public List<FechaVisita> buscarPor(String criterio) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPor'");
    }

    @Override
    public FechaVisita buscarPorId(Long id) {
        return fechaVisitaRepositoy.findById(id).orElseThrow(null);
    }

    @Override
    public List<FechaVisita> obtenerFechaPorVisita(Visita visitas) {
        return fechaVisitaRepositoy.findByVisita(visitas);
    }

    @Override
    public FechaVisita obtenerUltimaFechaPorVisita(Visita visita) {
      
        List<FechaVisita> fechas = fechaVisitaRepositoy.findByVisitaOrderByFechaDesc(visita);
        if (!fechas.isEmpty()) {
            return fechas.get(0);
        }
        return null;
    }

}
