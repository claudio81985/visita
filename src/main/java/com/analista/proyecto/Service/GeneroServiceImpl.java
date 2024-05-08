package com.analista.proyecto.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.analista.proyecto.Model.Genero;
import com.analista.proyecto.Repository.IGeneroRepository;



@Service
public class GeneroServiceImpl implements IGeneroService {

    @Autowired
    private IGeneroRepository generoRepository;

    @Override
    public void guardar(Genero genero) {
       generoRepository.save(genero);
    }

    @Override
    public List<Genero> buscarTodos() {
       return generoRepository.findAll();
    }

    @Override
    public List<Genero> buscarPor(String criterio) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPor'");
    }

    @Override
    public Genero buscarPorId(Long id) {
      return generoRepository.findById(id).orElse(null);
    }

    
}
