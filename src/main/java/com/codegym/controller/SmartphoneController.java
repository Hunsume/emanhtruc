package com.codegym.controller;

import com.codegym.model.Smartphone;
import com.codegym.service.ISmartphoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
import java.util.function.IntFunction;

@RestController
@CrossOrigin("*")
@RequestMapping("/smartphones")
public class SmartphoneController {
    @Autowired
    private ISmartphoneService smartphoneService;

    @GetMapping
    public ResponseEntity<Page<Smartphone>> allPhones(@RequestParam(name = "q") Optional<String> q, @PageableDefault(size = 3) Pageable pageable) {
        Page<Smartphone> smartphoneList;
        if (q.isPresent()) {
            smartphoneList =smartphoneService.findAllByProducerContaining(q.get(), pageable);
        } else {
           smartphoneList = smartphoneService.findAll(pageable);
        }
        if (smartphoneList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(smartphoneList, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<Smartphone> createSmartphone(@RequestBody Smartphone smartphone) {
        return new ResponseEntity<>(smartphoneService.save(smartphone),HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Smartphone> deleteSmartphone(@PathVariable Long id) {
        Optional<Smartphone> smartphoneOptional = smartphoneService.findById(id);
        if (!smartphoneOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        smartphoneService.remove(id);
        return new ResponseEntity<>(smartphoneOptional.get(), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Smartphone> findPhoneByName(@PathVariable Long id) {
        Optional<Smartphone> phone1 = smartphoneService.findById(id);
        if (phone1.isPresent()) {
            return new ResponseEntity<>(phone1.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Smartphone> editSmartphone(@PathVariable Long id, @RequestBody Smartphone smartphone) {
        Optional<Smartphone> phone = smartphoneService.findById(id);
        if (phone.isPresent()) {
            smartphone.setId(phone.get().getId());
            return new ResponseEntity<>(smartphoneService.save(smartphone),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
