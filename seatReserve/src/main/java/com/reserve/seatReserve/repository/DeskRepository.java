package com.reserve.seatReserve.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.reserve.seatReserve.model.Desk;

@Repository
public interface DeskRepository extends MongoRepository<Desk, Long>{

}
