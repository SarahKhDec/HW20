package org.example.HW20.controller;

import org.example.HW20.dto.service.GetServicesDto;
import org.example.HW20.dto.service.ServicesDto;
import org.example.HW20.entity.Services;
import org.example.HW20.mappers.ServicesMapperImpl;
import org.example.HW20.service.ServicesServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/services")
public class ServicesController {

    private final ServicesServiceImpl service;
    private final ServicesMapperImpl servicesMapper;

    @PostMapping("/create")
    public GetServicesDto create(@Valid @RequestBody ServicesDto servicesDto) {
        Services services = servicesMapper.dtoToServices(servicesDto);
        return servicesMapper.servicesToDto(service.save(services));
    }

    @GetMapping("/findAll")
    public List<GetServicesDto> findAll() {
        return servicesMapper.servicesListToDtoList(service.findAll());
    }

    @GetMapping("/findById/{id}")
    public GetServicesDto findById(@PathVariable Long id) {
        return servicesMapper.servicesToDto(service.findById(id));
    }
}
