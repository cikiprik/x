/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beacukai.repository;

import com.beacukai.entity.Employee;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author Student10
 */
public interface EmployeeRepo extends PagingAndSortingRepository<Employee, Long>{
    @Query("select e from Employee e where e.nama like %?1%")
    public List<Employee> findByName(String name);
    @Query("select e from Employee e")
    public List<Employee> findByAll(Pageable pageRequest);
}
