package com.demo.MOW.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.MOW.Entity.MealOrder;
import com.demo.MOW.Service.MealOrderService;

@RestController
@RequestMapping("/api/orders")
public class MealOrderController {
    @Autowired
    private MealOrderService mealOrderService;

    @PostMapping
    public ResponseEntity<MealOrder> createOrder(@RequestBody MealOrder mealOrder) {
        MealOrder createdOrder = mealOrderService.createOrder(mealOrder);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    @GetMapping("/user/{userName}")
    public ResponseEntity<List<MealOrder>> getOrdersByUserName(@PathVariable String userName) {
        List<MealOrder> orders = mealOrderService.getOrdersByUserName(userName);
        return ResponseEntity.ok(orders);
    }

}

