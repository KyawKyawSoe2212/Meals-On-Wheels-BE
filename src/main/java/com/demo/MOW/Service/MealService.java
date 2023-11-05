package com.demo.MOW.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.MOW.Entity.Meal;
import com.demo.MOW.Entity.User;
import com.demo.MOW.Repository.MealRepository;

@Service
public class MealService {
    @Autowired
    private MealRepository mealRepository;

    @Autowired
    private UserService userService;
    
    public Meal postMeal(Meal meal, User user) {
        meal.setPostedUser(user);
        return mealRepository.save(meal);
    }
    public List<Meal> getAllMeals() {
        return mealRepository.findAll();
    }

    public Meal getMealById(long id) {
        return mealRepository.findById(id).orElse(null);
    }

    public void deleteMealById(long id) {
        mealRepository.deleteById(id);
    }
    
    public Meal updateMeal(Long id, Meal updatedMeal) {
        Meal meal = getMealById(id);
        meal.setMealName(updatedMeal.getMealName());
        meal.setDescription(updatedMeal.getDescription());
        meal.setPreparation(updatedMeal.getPreparation());

        return mealRepository.save(meal);
    }
}

