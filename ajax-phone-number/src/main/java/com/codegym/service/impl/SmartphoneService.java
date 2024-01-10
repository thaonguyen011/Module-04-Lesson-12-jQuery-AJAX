package com.codegym.service.impl;

import com.codegym.model.Smartphone;
import com.codegym.repository.ISmartphoneRepository;
import com.codegym.service.ISmartphoneService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SmartphoneService implements ISmartphoneService {
    private final ISmartphoneRepository iSmartphoneRepository;

    public SmartphoneService(ISmartphoneRepository iSmartphoneRepository) {
        this.iSmartphoneRepository = iSmartphoneRepository;
    }

    @Override
    public Iterable<Smartphone> findAll() {
        return iSmartphoneRepository.findAll();
    }

    @Override
    public Optional<Smartphone> findById(Long id) {
        return iSmartphoneRepository.findById(id);
    }

    @Override
    public Smartphone save(Smartphone smartphone) {
        return iSmartphoneRepository.save(smartphone);
    }

    @Override
    public void remove(Long id) {
        iSmartphoneRepository.deleteById(id);
    }
}
