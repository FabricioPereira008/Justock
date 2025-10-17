package com.justeam.justock_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.justeam.justock_api.model.order;

@Repository
public interface orderRepository extends JpaRepository<order, Long> {}