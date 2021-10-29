package com.reserve.seatReserve.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.reserve.seatReserve.model.Reserve;

@Repository
public interface ReserveRepository extends MongoRepository<Reserve, Long> {

}
