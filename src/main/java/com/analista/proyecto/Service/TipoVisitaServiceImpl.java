package com.analista.proyecto.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.analista.proyecto.Model.TipoVisita;
import com.analista.proyecto.Repository.ITiposVisitasRepository;

@Service
public class TipoVisitaServiceImpl implements ITipoVisitaService{

    @Autowired
    ITiposVisitasRepository tiposVisitasRepository;

    @Override
    public void guardar(TipoVisita tipoVisita) {
        tiposVisitasRepository.save(tipoVisita);
    }

    @Override
    public List<TipoVisita> buscarTodos() {
        return tiposVisitasRepository.findAll();
    }

    @Override
    public List<TipoVisita> buscarPor(String criterio) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPor'");
    }

    @Override
    public TipoVisita buscarPorId(Long id) {
        return tiposVisitasRepository.findById(id).orElseThrow(null);
    }

}
