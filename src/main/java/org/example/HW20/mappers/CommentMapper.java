package org.example.HW20.mappers;

import org.example.HW20.dto.comment.CommentForExpertDto;
import org.example.HW20.dto.comment.CreateCommentDto;
import org.example.HW20.dto.comment.GetCommentDto;
import org.example.HW20.dto.comment.GetCommentForExpertDto;
import org.example.HW20.entity.Comments;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    Comments commentDtoToComment(CreateCommentDto createCommentDto);
    GetCommentDto commentToDto(Comments comments);
    Comments commentForExpertToComment(CommentForExpertDto commentForExpertDto);
    List<GetCommentForExpertDto> commentToExpertScoreDto(List<Comments> comments);
    GetCommentForExpertDto commentToExpertScoreDto(Comments comments);
}
