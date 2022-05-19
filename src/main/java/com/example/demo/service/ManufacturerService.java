package com.example.demo.service;

import com.example.demo.entity.Manufacturer;

import java.util.List;

public interface ManufacturerService {

    List<Manufacturer> findAllManufacturers();
    Manufacturer addManufacturer(Manufacturer manufacturer);
    Manufacturer getManufacturer(Long id);
    void deleteManufacturer(long id);
    Manufacturer saveManufacturer(Manufacturer manufacturer);
    Manufacturer findOneById(Long id);

}
