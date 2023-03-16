package com.example.Salon.repositories;

import com.example.Salon.models.AdresSalona;
import com.example.Salon.models.Doljnosti;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AdresSalonaRepository extends CrudRepository<AdresSalona,Long> {
    public List<AdresSalona> findByadresSalona(String AdresSalona);
}
