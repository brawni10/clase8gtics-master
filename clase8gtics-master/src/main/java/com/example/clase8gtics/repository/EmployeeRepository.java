package com.example.clase8gtics.repository;

import com.example.clase8gtics.dto.EmpleadosPorPaisDto;
import com.example.clase8gtics.dto.EmpleadosRegionDto;
import com.example.clase8gtics.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query(value = "select r.regionDescription as regiondescription, count(e.employeeid) as cantidadempleados " +
            "FROM Region r\n" +
            "inner join Territories t on (r.regionid = t.regionid)\n" +
            "inner join EmployeeTerritories et on (t.territoryid = et.territoryid)\n" +
            "inner join Employees e on (e.employeeid = et.employeeid)\n" +
            "group by r.regionid\n", nativeQuery = true)
    List<EmpleadosRegionDto> obtenerEmpleadosPorRegion();

    @Query(value = "SELECT country as pais, count(*) as cantidad FROM Employees GROUP BY country",
            nativeQuery = true)
    List<EmpleadosPorPaisDto> obtenerEmpleadosPorPais();

    @Query(value = "SELECT country as pais, count(*) as cantidad FROM Employees WHERE country = ?1 GROUP BY country",
            nativeQuery = true)
    List<EmpleadosPorPaisDto> obtenerEmpleadosPorPais(String nombrePais);
}

