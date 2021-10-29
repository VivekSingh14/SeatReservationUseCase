package com.desk.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.desk.app.model.Desk;

@Repository
public interface DeskRepository extends MongoRepository<Desk, Long>{

}
