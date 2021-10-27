package com.codegym.service;

import com.codegym.model.Smartphone;
import com.codegym.repository.ISmartphoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class SmartphoneService implements ISmartphoneService{

    @Autowired
    private ISmartphoneRepository smartphoneRepository;

    @Override
    public Page<Smartphone> findAll(Pageable pageable) {
        return smartphoneRepository.findAll(pageable);
    }

    @Override
    public Optional<Smartphone> findById(Long id) {
        return smartphoneRepository.findById(id);
    }

    @Override
    public Smartphone save(Smartphone smartPhone) {
        return smartphoneRepository.save(smartPhone);
    }

    @Override
    public void remove(Long id) {
        smartphoneRepository.deleteById(id);
    }
    @Override
    public Page<Smartphone> findAllByProducerContaining(String producer, Pageable pageable) {
        return smartphoneRepository.findAllByProducerContaining(producer,pageable);
    }
}
