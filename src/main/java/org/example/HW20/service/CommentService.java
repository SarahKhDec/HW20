package org.example.HW20.service;

import org.example.HW20.entity.Comments;

import java.util.List;

public interface CommentService {

    Comments create(Comments comments);
    List<Comments> showExpertScore(Comments comments);
}
