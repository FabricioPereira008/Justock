package com.justeam.justock_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.justeam.justock_api.model.FinishOrder;

@Repository
public interface FinishOrderRepository extends JpaRepository<FinishOrder, Long> {}