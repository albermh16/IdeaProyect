package es.daw.foodexpressapi.entity;

import es.daw.foodexpressapi.dto.DishesDTO;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Table(name = "restaurants")
@Getter
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String address;

    @Column(length = 20)
    private String phone;




}
