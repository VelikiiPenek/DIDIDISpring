package com.example.Salon.repositories;

import com.example.Salon.models.AdresSalona;
import com.example.Salon.models.Sotrydniki;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SotrydnikiRepository extends CrudRepository<Sotrydniki,Long> {

    public List<Sotrydniki> findBysurename(String Surename);
}
