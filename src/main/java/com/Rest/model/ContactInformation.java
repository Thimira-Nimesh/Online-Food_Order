package com.Rest.model;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data



public class ContactInformation {



    private String email;
    private String phone;
    private String twitter;
    private String facebook;

}
