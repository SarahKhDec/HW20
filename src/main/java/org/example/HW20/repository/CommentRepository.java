package org.example.HW20.repository;

import org.example.HW20.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CommentRepository extends JpaRepository<Comments, Long> {

    @Query("select c from Comments c where c.expert.id= ?1")
    List<Comments> findExpertComment(Long id);
}
