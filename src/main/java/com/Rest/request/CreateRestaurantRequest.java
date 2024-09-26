package com.Rest.request;

import com.Rest.model.Address;
import com.Rest.model.ContactInformation;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data

public class CreateRestaurantRequest {

    private Long id;
    private String name;
    private String description;
    private String CuisineType;
    private Address address;
    private ContactInformation contactInformation;
    private String openingHours;
    private List<String> images;


}

