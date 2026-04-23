package com.sistema.academia.service;

import com.sistema.academia.model.ItemExercicio;
import com.sistema.academia.repository.ItemExercicioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemExercicioService {

    private final ItemExercicioRepository itemExercicioRepository;

    public ItemExercicioService(ItemExercicioRepository itemExercicioRepository){
        this.itemExercicioRepository=itemExercicioRepository;
    }

    public List<ItemExercicio> Listar(){
        return itemExercicioRepository.findAll();
    }

    public Optional<ItemExercicio> BuscarPorId(Long id){
        return itemExercicioRepository.findById(id);
    }

    public ItemExercicio salvar(ItemExercicio itemExercicio){
        return itemExercicioRepository.save(itemExercicio);
    }

    public void deletar(Long id){
        itemExercicioRepository.deleteById(id);
    }


}
