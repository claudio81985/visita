package com.analista.proyecto.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.analista.proyecto.Model.Parentesco;
import com.analista.proyecto.Repository.IParentescoRepository;

@Service
public class ParentescoServiceImpl implements IParentescoService {

    @Autowired
    IParentescoRepository parentescoRepository;

    @Override
    public void guardar(Parentesco parentesco) {
        parentescoRepository.save(parentesco);
    }

    @Override
    public List<Parentesco> buscarTodo() {
       return parentescoRepository.findAll();
    }

    @Override
    public List<Parentesco> buscarPor(String criterio) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPor'");
    }

    @Override
    public Parentesco buscarPorId(Long id) {
        return parentescoRepository.findById(id).orElse(null);
    }

}
