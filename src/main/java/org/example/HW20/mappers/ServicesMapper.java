package org.example.HW20.mappers;

import org.example.HW20.dto.service.GetServicesDto;
import org.example.HW20.dto.service.ServicesDto;
import org.example.HW20.entity.Services;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ServicesMapper {

    Services dtoToServices(ServicesDto servicesDto);
    GetServicesDto servicesToDto(Services services);

    List<GetServicesDto> servicesListToDtoList(List<Services> services);
}
