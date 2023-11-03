package org.example.HW20.repository;

import org.example.HW20.entity.Expert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ExpertRepository extends JpaRepository<Expert, Long> {

    Optional<Expert> findByEmail(String email);

    @Query("select e from Expert e where e.status='NEW'")
    List<Expert> findAllByStatus();

    @Query("select e from Expert e where e.id= ?1 and e.status='NEW'")
    Optional<Expert> findExpertById(Long id);

    @Query("select e from Expert e where e.email= ?1 and e.status='ACCEPTED'")
    Optional<Expert> findByEmailAndStatus(String email);
}
