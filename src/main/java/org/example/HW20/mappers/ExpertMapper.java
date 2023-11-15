package org.example.HW20.mappers;

import org.example.HW20.dto.expert.*;
import org.example.HW20.entity.Expert;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExpertMapper {

    GetExpertDto expertToDto(Expert expert);
//    @Mapping(target = "imageUrl", ignore = true)
    Expert expertDtoToExpert(CreateExpertDto createExpertDto);
    List<GetExpertDto> expertListToDtoList(List<Expert> expertList);

    Expert changePasswordDtoToExpert(ChangeExpertPasswordDto changeExpertPasswordDto);
    GetExpertSubServiceDto expertToGetSubServiceDto(Expert expert);
    Expert expertSubServiceToExpert(ExpertToSubServiceDto expertToSubServiceDto);
    Expert saveImageDtoToExpert(SaveImageDto saveImageDto);
}
