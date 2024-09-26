package com.Rest.request;

import com.Rest.model.Category;
import lombok.Data;

import java.util.List;

@Data

public class CreateFoodrequest {

    private String name;
    private String description;
    private long price;

    private Category category;
    private List<String> images;
    private long restaurantId;
    private boolean vegetarian;
    private boolean seasonal;
    private List<String> ingredients;




}
