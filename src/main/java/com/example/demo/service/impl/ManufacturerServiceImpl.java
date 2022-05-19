package com.example.demo.service.impl;


import com.example.demo.entity.Item;
import com.example.demo.entity.Manufacturer;
import com.example.demo.repository.ManufacturerRepository;
import com.example.demo.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    @Autowired
    ManufacturerRepository manufacturerRepository;

    @Override
    public List<Manufacturer> findAllManufacturers() {
            return manufacturerRepository.findAll();
    }

    @Override
    public Manufacturer addManufacturer(Manufacturer manufacturer) {
        return manufacturerRepository.save(manufacturer);
    }

    @Override
    public Manufacturer getManufacturer(Long id) {
        return manufacturerRepository.getOne(id);
    }

    @Override
    public void deleteManufacturer(long id) {
        manufacturerRepository.deleteById(id);
    }

    @Override
    public Manufacturer saveManufacturer(Manufacturer manufacturer) {
        return manufacturerRepository.save(manufacturer);
    }

    @Override
    public Manufacturer findOneById(Long id) {
        return manufacturerRepository.findById(id).orElse(null);
    }
}
