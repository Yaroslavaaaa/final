package com.example.demo.repository;


import com.example.demo.entity.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
}
