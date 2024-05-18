package com.example.clase8gtics.repository;

import com.example.clase8gtics.entity.Shipper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipperRepository extends JpaRepository<Shipper, Integer> {

    List<Shipper> findByCompanyName(String nombre);

    @Query(value = "select * from Shippers where CompanyName = ?1",
            nativeQuery = true)
    List<Shipper> buscarTransPorCompName(String nombre);


}

