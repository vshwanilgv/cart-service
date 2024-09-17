package com.example.cart_service.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private int quantity;
    private double price;

    public void setId(Long id) {

        this.id = id;
    }

    public void setProductId(Long id){
        this.productId=productId;
    }

    public void setQuantity(int quantity){
        this.quantity=quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
