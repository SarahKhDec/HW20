package org.example.HW20.service;

import org.example.HW20.entity.Services;
import org.example.HW20.exceptions.service.ServiceExistException;
import org.example.HW20.exceptions.service.ServiceNotFoundException;
import org.example.HW20.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicesServiceImpl implements ServicesService {

    private final ServiceRepository serviceRepository;

    @Override
    public Services save(Services services) {
        try {
            return serviceRepository.save(services);
        } catch (DataIntegrityViolationException e) {
            throw new ServiceExistException("a service already exists with this name.");
        }
    }

    @Override
    public List<Services> findAll() {
        return serviceRepository.findAll();
    }

    @Override
    public Services findById(Long id) {
        return serviceRepository.findById(id).orElseThrow(
                () -> new ServiceNotFoundException("no service found with this ID.")
        );
    }
}
