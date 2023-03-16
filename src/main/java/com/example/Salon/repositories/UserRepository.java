package com.example.Salon.repositories;

import com.example.Salon.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User,Long> {
     User findByUsername(String login);
}
