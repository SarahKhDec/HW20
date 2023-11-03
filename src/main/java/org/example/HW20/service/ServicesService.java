package org.example.HW20.service;

import org.example.HW20.entity.Services;

import java.util.List;

public interface ServicesService {

    Services save(Services services);
    List<Services> findAll();
    Services findById(Long id);
}
