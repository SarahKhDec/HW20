package org.example.HW20.service;

import org.example.HW20.entity.Services;
import org.example.HW20.entity.SubService;
import org.example.HW20.exceptions.subservice.SubServiceExistException;
import org.example.HW20.exceptions.subservice.SubServiceNotFoundException;
import org.example.HW20.repository.SubServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubServiceServiceImpl implements SubServiceService {

    private final SubServiceRepository subServiceRepository;
    private final ServicesServiceImpl service;



    @Override
    public SubService create(SubService subService) {
        try {
            Services services = service.findById(subService.getServices().getId());
            subService.setServices(services);
            return subServiceRepository.save(subService);
        } catch (DataIntegrityViolationException e) {
            throw new SubServiceExistException("a sub service already exists with this name in this service.");
        }
    }

    @Override
    public SubService findById(Long id) {
        return subServiceRepository.findById(id).orElseThrow(
                () -> new SubServiceNotFoundException("no sub service found with this ID.")
        );
    }

    @Override
    public List<SubService> findAllByServiceId(Long id) {
        return subServiceRepository.findAllByServices(id);
    }

    @Override
    public SubService update(SubService subService) {
        return subServiceRepository.save(subService);
    }
}
