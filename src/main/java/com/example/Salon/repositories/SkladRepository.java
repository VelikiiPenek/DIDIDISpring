package com.example.Salon.repositories;

import com.example.Salon.models.Sklad;
import com.example.Salon.models.TipYslygi;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SkladRepository extends CrudRepository<Sklad,Long> {

    public List<Sklad> findByadresSklada(String adresSklada);
}
