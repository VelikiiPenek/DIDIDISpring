package com.example.Salon.repositories;

import com.example.Salon.models.TipYslygi;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TipYslygiRepostiory extends CrudRepository<TipYslygi,Long> {

    public List<TipYslygi> findBytipYslygi(String TipYslygi);
}
