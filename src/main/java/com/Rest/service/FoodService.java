package com.Rest.service;

import com.Rest.model.Category;
import com.Rest.model.Food;
import com.Rest.model.Restaurant;
import com.Rest.request.CreateFoodrequest;

import java.util.List;

public interface FoodService {

    public Food createFood(CreateFoodrequest req, Category category, Restaurant restaurant);

    void deleteFood(Long foodId) throws Exception;

    public List<Food> getRestaurantFood(Long restaurantId, boolean isVegetarian,boolean isNonveg, boolean isSeasonal,String foodCategory);

    public List<Food> searchFood(String keyword);

    public Food findFoodById(Long foodId)throws Exception;

    public Food updateAvailabilityStatus(Long foodId)throws Exception;

}
