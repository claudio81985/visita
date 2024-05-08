package com.analista.proyecto.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.analista.proyecto.Model.Interno;
import com.analista.proyecto.Model.Modulo;
import com.analista.proyecto.Repository.IInternoRepository;

@Service
public class InternoServiceImpl implements IInternoService {

  
   @Autowired
   IInternoRepository internoRepository;

   @Override
   public void borrar(Interno interno) {
      internoRepository.delete(interno);
   }

   @Override
   public List<Interno> buscarTodo() {
      return internoRepository.buscarSoloHabilitados();
   }

   @Override
   public List<Interno> buscarPor(String criterio) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'buscarPor'");
   }

   @Override
   public Interno buscarPorId(Long id) {
      return internoRepository.findById(id).orElse(null);
   }

   public Interno guardar(Interno interno) {
      Interno internoGuardado = internoRepository.save(interno);
        return internoGuardado;
   }

     @Override
    public List<Interno> buscarPorModulo(Modulo modulo) {
        return internoRepository.findByModulo(modulo);
    }

}
