package com.analista.proyecto.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.analista.proyecto.Model.Interno;
import com.analista.proyecto.Model.Visita;
import com.analista.proyecto.Repository.IVisitaRepository;



@Service
public class VisitaServiceImpl implements IVisitaService{

    @Autowired
    IVisitaRepository visitaRepository;

    @Override
    public List<Visita> buscarTodo() {
        return visitaRepository.findAll();
    }

    @Override
    public List<Visita> buscarPor(String criterio) {
        return visitaRepository.buscarPor(criterio);
    }

    @Override
    public Visita buscarPorId(Long id) {
       return visitaRepository.findById(id).orElse(null);
    }

    @Override
    public Visita guardar(Visita visita) {
        Visita visitaGuardado = visitaRepository.save(visita);
        return visitaGuardado;
    }

    @Override
    public List<Visita> obtenerVisitasPorInterno(Interno interno) {
        return visitaRepository.findByInterno(interno);
    }

  
}
