package com.miguellaher.mbtcexam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.miguellaher.mbtcexam.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    
}
