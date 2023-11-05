package com.demo.MOW.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.MOW.Entity.Meal;

public interface MealRepository extends JpaRepository<Meal, Long> {
}

