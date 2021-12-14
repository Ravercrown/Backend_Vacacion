package com.vacacoin.ordersAndCartms.repositories;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.vacacoin.ordersAndCartms.models.Invoice;

public interface InvoiceRepository extends MongoRepository<Invoice, Integer>{
}
