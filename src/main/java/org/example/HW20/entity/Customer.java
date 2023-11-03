package org.example.HW20.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Customer extends BaseUser {

    Long credit;

    String role;

    @OneToMany(mappedBy = "customer")
    List<Orders> orders;

    @OneToMany(mappedBy = "customer")
    List<Comments> comments;

    public Customer() {
        this.credit = 100_000L;
        this.setRole(Customer.class.getSimpleName());
        this.orders = new ArrayList<>();
        this.comments = new ArrayList<>();
    }
}
