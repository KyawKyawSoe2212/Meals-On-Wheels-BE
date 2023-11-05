package com.demo.MOW.Controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.MOW.Dto.MessageResponse;
import com.demo.MOW.Entity.Meal;
import com.demo.MOW.Entity.User;
import com.demo.MOW.Service.MealService;
import com.demo.MOW.Service.UserService;

@RestController
@RequestMapping("/api/meals")
public class MealController {
    @Autowired
    private MealService mealService;
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Meal> createMeal(@RequestBody Meal meal, Principal principal) {
    	String email = principal.getName();
        Optional<User> userOptional = userService.findUserByEmail(email);
        
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Meal postedMeal = mealService.postMeal(meal, user);
            return ResponseEntity.ok(postedMeal);
        } else {
            throw new EntityNotFoundException("User not found with email: " + email);
        }
    }
    
    @GetMapping
    public ResponseEntity<List<Meal>> getAllMeals() {
        List<Meal> meals = mealService.getAllMeals();
        return ResponseEntity.ok(meals);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> deleteMeals(@PathVariable long id) {
    	mealService.deleteMealById(id);
    	return ResponseEntity.ok(new MessageResponse("Meal is deleted successfully!"));
    }
    
}

