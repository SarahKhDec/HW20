package org.example.HW20.controller;

import org.example.HW20.dto.comment.CommentForExpertDto;
import org.example.HW20.dto.comment.CreateCommentDto;
import org.example.HW20.dto.comment.GetCommentDto;
import org.example.HW20.dto.comment.GetCommentForExpertDto;
import org.example.HW20.entity.Comments;
import org.example.HW20.mappers.CommentMapperImpl;
import org.example.HW20.service.CommentServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/comment")
public class CommentController {

    private final CommentServiceImpl commentService;
    private final CommentMapperImpl commentMapper;

    @PostMapping("/create")
    public GetCommentDto create(@Valid @RequestBody CreateCommentDto createCommentDto) {
        Comments comments = commentMapper.commentDtoToComment(createCommentDto);
        return commentMapper.commentToDto(commentService.create(comments));
    }

    @PostMapping("/showMyScore")
    public List<GetCommentForExpertDto> showScore(@Valid @RequestBody CommentForExpertDto commentForExpertDto) {
        Comments comments = commentMapper.commentForExpertToComment(commentForExpertDto);
        return commentMapper.commentToExpertScoreDto(commentService.showExpertScore(comments));
    }
}
