package com.example.Salon.repositories;

import com.example.Salon.models.Nabori;
import com.example.Salon.models.TipYslygi;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NaboriRepository extends CrudRepository<Nabori,Long> {

    public List<Nabori> findBynameKit(String NameKit);
}
