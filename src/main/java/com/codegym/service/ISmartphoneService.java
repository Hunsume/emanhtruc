package com.codegym.service;

import com.codegym.model.Smartphone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ISmartphoneService {
    Page<Smartphone> findAll(Pageable pageable);

    Optional<Smartphone> findById(Long id);

    Smartphone save(Smartphone smartPhone);

    void remove(Long id);

    Page<Smartphone> findAllByProducerContaining(String producer, Pageable pageable);

}
