package com.Rest.service;

import com.Rest.dto.RestaurantDto;
import com.Rest.model.Address;
import com.Rest.model.Restaurant;
import com.Rest.model.User;
import com.Rest.repository.AddressRepository;
import com.Rest.repository.RestaurantRepository;
import com.Rest.repository.UserRepository;
import com.Rest.request.CreateRestaurantRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service

public class RestaurantServiceImp implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Restaurant createRestaurant(CreateRestaurantRequest req, User user) {
        Address address = addressRepository.save(req.getAddress());

        Restaurant restaurant = new Restaurant();
        restaurant.setAddress(address);
        restaurant.setContactInformation(req.getContactInformation());
        restaurant.setCuisineType(req.getCuisineType());
        restaurant.setDescription(req.getDescription());
        restaurant.setImages(req.getImages());
        restaurant.setName(req.getName());
        restaurant.setOpeningHours(req.getOpeningHours());
        restaurant.setRegistrationDate(LocalDateTime.now());
        restaurant.setOwner(user);


        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updateRequest) throws Exception {

        Restaurant restaurant=findRestaurantById(restaurantId);

        if(restaurant.getCuisineType()!=null){
            restaurant.setCuisineType(updateRequest.getCuisineType());

        }

        if(restaurant.getDescription()!=null){
            restaurant.setDescription(updateRequest.getDescription());
        }

        if(restaurant.getName()!=null){
            restaurant.setName(updateRequest.getName());
        }


        return restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteRestaurant(Long restaurantId) throws Exception {

        Restaurant restaurant=findRestaurantById(restaurantId);

        restaurantRepository.delete(restaurant);


    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> searchRestaurant(String keyword) {
        return restaurantRepository.findBySearchQuery(keyword);
    }

    @Override
    public Restaurant findRestaurantById(Long id) throws Exception {
        Optional<Restaurant> opt=restaurantRepository.findById(id);

        if(opt.isEmpty()){
            throw new Exception("Restaurant not found"+ id);
        }
        return opt.get();
    }

    @Override
    public Restaurant getRestaurantByUserId(Long userId) throws Exception {
        Restaurant restaurant=restaurantRepository.findByOwnerId(userId);

        if(restaurant==null){
            throw new Exception("Restaurant not found with ownerId"+ userId);
        }

        return restaurant;
    }

    @Override
    public RestaurantDto addToFavourites(Long restaurantId, User user) throws Exception {

        Restaurant restaurant=findRestaurantById(restaurantId);

        RestaurantDto dto=new RestaurantDto();
        dto.setDescription(restaurant.getDescription());
        dto.setImages(restaurant.getImages());
        dto.setTitle(restaurant.getName());
        dto.setId(restaurantId);
//
//        boolean isFavorited = false;
//        List<RestaurantDto> favorites =user.getFavourites();
//        for(RestaurantDto favorite : favorites) {
//            if (favorite.getId().equals(restaurantId)){
//                isFavorited=true;
//                break;
//            }
//        }
//
//        if(isFavorited){
//            favorites.removeIf(favorite -> favorite.getId().equals(restaurantId));
//        } else {
//            favorites.add(dto);
//        }

        if(user.getFavourites().contains(dto)){
            user.getFavourites().remove(dto);
        }
        else user.getFavourites().add(dto);


        userRepository.save(user);
        return dto;
    }

    @Override
    public Restaurant updateRestaurantStatus(Long id) throws Exception {

        Restaurant restaurant=findRestaurantById(id);
        restaurant.setOpen(!restaurant.isOpen());
        return restaurantRepository.save(restaurant);
    }
}
