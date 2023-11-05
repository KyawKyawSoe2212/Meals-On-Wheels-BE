package com.demo.MOW.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.MOW.Entity.MealOrder;
import com.demo.MOW.Repository.MealOrderRepository;

@Service
public class MealOrderService {
    @Autowired
    private MealOrderRepository mealOrderRepository;
    
    public MealOrder createOrder(MealOrder mealOrder) {
        return mealOrderRepository.save(mealOrder);
    }
    
    public List<MealOrder> getOrdersByUserName(String orderUserName) {
        return mealOrderRepository.findByOrderUserName(orderUserName);
    }

    public List<MealOrder> getAllOrders() {
        return mealOrderRepository.findAll();
    }

    public MealOrder getOrderById(long id) {
        return mealOrderRepository.findById(id).orElse(null);
    }

    public void deleteOrderById(long id) {
        mealOrderRepository.deleteById(id);
    }

    // Add more methods as needed
}

