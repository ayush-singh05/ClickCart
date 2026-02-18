package com.clickcart.ClickCart.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Embeddable
public class ShippingAddress {

    String street;
    String city;
    String state;
    String zipCode;
    String country;


}
