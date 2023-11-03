package org.example.HW20.entity;

import org.example.HW20.entity.enumeration.ExpertStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Expert extends BaseUser {

    @Enumerated(EnumType.STRING)
    ExpertStatus status;

    byte[] imageUrl;
    Long credit;
    Integer score;

    String role;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "expert_sub_service",
            joinColumns = @JoinColumn(name = "expert_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "sub_service_id", referencedColumnName = "id"))
    Set<SubService> subServiceSet;

    @Transient
    SubService subService;

    @OneToMany(mappedBy = "expert")
    List<Offers> offers;

    @OneToMany(mappedBy = "expert")
    List<Comments> comments;

    public void addSubService(SubService subService) {
        subServiceSet.add(subService);
        subService.getExpertSet().add(this);
    }

    public void removeSubService(SubService subService) {
        subServiceSet.remove(subService);
        subService.getExpertSet().remove(this);
    }

    public Expert() {
        this.status = ExpertStatus.NEW;
        this.score = 0;
        this.credit = 100_000L;
        this.setRole(Expert.class.getSimpleName());
        this.subServiceSet = new HashSet<>();
        this.offers = new ArrayList<>();
        this.comments = new ArrayList<>();
    }
}
