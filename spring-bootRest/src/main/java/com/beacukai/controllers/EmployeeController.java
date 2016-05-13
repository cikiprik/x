/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beacukai.controllers;

import com.beacukai.dto.ResponseObject;
import com.beacukai.entity.Employee;
import com.beacukai.services.EmployeeService;
import java.util.concurrent.Callable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Student10
 */
@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService empService;

    @RequestMapping(value = "/{page}", method = RequestMethod.GET)
    public Callable<ResponseObject> findAll(@PathVariable("page") int page) {
        // non blocking
        return new Callable<ResponseObject>() {
            @Override
            public ResponseObject call() throws Exception {
                return empService.findAll(page);
            }
        };
        
        

    }
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Callable<ResponseObject> findAllData() {
        // non blocking
        return new Callable<ResponseObject>() {
            @Override
            public ResponseObject call() throws Exception {
                return empService.findByAllData();
            }
        };
        
        

    }

    @RequestMapping(method = RequestMethod.POST)
    public Callable<ResponseObject> insert(@RequestBody Employee emp) {
        return new Callable<ResponseObject>() {
            @Override
            public ResponseObject call() throws Exception {
               return empService.insert(emp);
            }
        };
        
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public Callable<ResponseObject> findbyName(@PathVariable("name") String name) {
        return new Callable<ResponseObject>() {
            @Override
            public ResponseObject call() throws Exception {
               return empService.findByName(name);
            }
        };
        
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    public Callable<ResponseObject> findbyId(@PathVariable("id") Long id) {
        return new Callable<ResponseObject>() {
            @Override
            public ResponseObject call() throws Exception {
                return empService.findById(id);
            }
        };
        
    }
    
    
    @RequestMapping(method = RequestMethod.PUT)
    public Callable<ResponseObject> update(@RequestBody Employee emp) {
        return new Callable<ResponseObject>() {
            @Override
            public ResponseObject call() throws Exception {
               return empService.update(emp);
            }
        };
        
    }
    
}
