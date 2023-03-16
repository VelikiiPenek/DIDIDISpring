package com.example.Salon.repositories;

import com.example.Salon.models.AdresSalona;
import com.example.Salon.models.Salon;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SalonRepository extends CrudRepository<Salon,Long> {

    public List<Salon> findBynaimenovanieSalona(String NaimenovanieSalona);
}
