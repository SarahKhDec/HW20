package org.example.HW20.controller;

import org.example.HW20.dto.subservice.GetSubServiceDto;
import org.example.HW20.dto.subservice.SubServiceDto;
import org.example.HW20.dto.subservice.UpdateSubServiceDto;
import org.example.HW20.entity.SubService;
import org.example.HW20.mappers.SubServiceMapperImpl;
import org.example.HW20.service.SubServiceServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/subService")
public class SubServiceController {

    private final SubServiceServiceImpl service;
    private final SubServiceMapperImpl subServiceMapper;

    @PostMapping("/create")
    public GetSubServiceDto create(@Valid @RequestBody SubServiceDto subServiceDto) {
        SubService subService = subServiceMapper.dtoToSubService(subServiceDto);
        return subServiceMapper.subServiceToDto(service.create(subService));
    }

    @GetMapping("/findById/{id}")
    public GetSubServiceDto findById(@PathVariable Long id) {
        return subServiceMapper.subServiceToDto(service.findById(id));
    }

    @GetMapping("/findByService/{id}")
    public List<GetSubServiceDto> findAllByServiceId(@PathVariable Long id) {
        return subServiceMapper.subServiceListToDtoList(service.findAllByServiceId(id));
    }

    @PutMapping("/update")
    public GetSubServiceDto update(@Valid @RequestBody UpdateSubServiceDto updateSubServiceDto) {
        SubService founded = service.findById(updateSubServiceDto.getId());
        SubService subService = subServiceMapper.updateSubServiceDtoToDto(updateSubServiceDto, founded);
        return subServiceMapper.subServiceToDto(service.update(subService));
    }
}
