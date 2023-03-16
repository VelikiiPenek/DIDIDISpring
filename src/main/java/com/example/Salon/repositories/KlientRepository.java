package com.example.Salon.repositories;

import com.example.Salon.models.AdresSalona;
import com.example.Salon.models.Klient;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface KlientRepository extends CrudRepository<Klient,Long> {

    public List<Klient> findBysurename(String Surename);
}
