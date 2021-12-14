package com.vacacoin.ordersAndCartms.repositories;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.vacacoin.ordersAndCartms.models.Buy;

public interface BuyRepository extends MongoRepository<Buy, Integer>{
}
