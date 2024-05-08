package com.analista.proyecto.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.analista.proyecto.Model.Elemento;
import com.analista.proyecto.Model.Interno;
import com.analista.proyecto.Repository.IElementoRepository;

@Service
public class ElementoServiceImpl implements IElementoService {

    @Autowired
    IElementoRepository elementoRepository;

    @Override
    public Elemento guardar(Elemento elemento) {
        Elemento elementoGuardado = elementoRepository.save(elemento);
        return elementoGuardado;

    }

    

    @Override
    public List<Elemento> buscarTodos() {
        return elementoRepository.findAll();
    }

    @Override
    public List<Elemento> buscarPor(String criterio) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPor'");
    }

    @Override
    public Elemento buscarPorId(Long id) {
        return elementoRepository.findById(id).orElseThrow(null);
    }



    @Override
    public List<Elemento> obtenerElementosPorInterno(Interno interno) {
        return elementoRepository.findByInterno(interno);
    }

}
