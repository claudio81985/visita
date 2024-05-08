package com.analista.proyecto.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.analista.proyecto.Model.Motivo;
import com.analista.proyecto.Repository.IMotivoRepository;


@Service
public class MotivoServiceImpl implements IMotivoService{

    @Autowired
    IMotivoRepository motivoRepository;

    @Override
    public void guardar(Motivo motivo) {
      motivoRepository.save(motivo);
    }

    @Override
    public List<Motivo> buscarTodos() {
       return motivoRepository.findAll();
    }

    @Override
    public List<Motivo> buscarPor(String criterio) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPor'");
    }

    @Override
    public Motivo buscarPorId(Long id) {
       return motivoRepository.findById(id).orElse(null);
    }

}
