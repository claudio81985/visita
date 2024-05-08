package com.analista.proyecto.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.analista.proyecto.Model.Modulo;
import com.analista.proyecto.Repository.IModuloRepository;

@Service
public class ModuloServiceImpl implements IModuloService {

   @Autowired
   IModuloRepository moduloRepository;

  @Override
  public void guardar(Modulo modulo) {
    moduloRepository.save(modulo);
  }

  @Override
  public List<Modulo> buscarTodos() {
    return moduloRepository.findAll();
  }

  @Override
  public List<Modulo> buscarPor(String criterio) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'buscarPor'");
  }

  @Override
  public Modulo buscarPorId(Long id) {
    return moduloRepository.findById(id).orElse(null);
  }

   
    
}
