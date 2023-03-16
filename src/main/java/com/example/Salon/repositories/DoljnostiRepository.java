package com.example.Salon.repositories;

import com.example.Salon.models.Doljnosti;
import com.example.Salon.models.Sklad;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DoljnostiRepository extends CrudRepository<Doljnosti,Long> {

    public List<Doljnosti> findBynamePost(String NamePost);
}
