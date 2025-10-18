package com.justeam.justock_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.justeam.justock_api.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {}