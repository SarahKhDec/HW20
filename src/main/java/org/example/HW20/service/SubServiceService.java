package org.example.HW20.service;

import org.example.HW20.entity.SubService;

import java.util.List;

public interface SubServiceService {

    SubService create(SubService subService);
    SubService findById(Long id);
    List<SubService> findAllByServiceId(Long id);
    SubService update(SubService subService);
}
