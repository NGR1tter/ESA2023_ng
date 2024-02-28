package com.example.demo.jms;

import com.example.demo.jms.entities.WatchDog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WatchDogRepository extends CrudRepository<WatchDog, Integer> {
}
