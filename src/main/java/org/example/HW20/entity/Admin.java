package org.example.HW20.entity;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Admin extends BaseUser {

    String role;

    public Admin() {
        this.setRole(Admin.class.getSimpleName());
    }
}
