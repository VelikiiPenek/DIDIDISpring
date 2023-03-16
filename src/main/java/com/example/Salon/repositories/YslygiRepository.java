package com.example.Salon.repositories;

import com.example.Salon.models.Nabori;
import com.example.Salon.models.Yslygi;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface YslygiRepository extends CrudRepository<Yslygi,Long> {

    public List<Yslygi> findBynameYslygi(String NameYslygi);
}
