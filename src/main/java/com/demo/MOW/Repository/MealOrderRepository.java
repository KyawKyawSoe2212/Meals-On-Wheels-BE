package com.demo.MOW.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.MOW.Entity.MealOrder;

public interface MealOrderRepository extends JpaRepository<MealOrder, Long> {
    List<MealOrder> findByOrderUserName(String orderUserName);
}

