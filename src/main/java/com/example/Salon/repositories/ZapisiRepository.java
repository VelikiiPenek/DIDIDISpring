package com.example.Salon.repositories;

import com.example.Salon.models.AdresSalona;
import com.example.Salon.models.Zapisi;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ZapisiRepository extends CrudRepository<Zapisi,Long> {

    public List<Zapisi> findBydataZapisi(String AdresSalona);
}
