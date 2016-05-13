/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beacukai.controllers;

import com.beacukai.dto.LoginObject;
import com.beacukai.dto.ResponseObject;
import com.beacukai.entity.Users;
import com.beacukai.services.UsersService;
import java.util.concurrent.Callable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Student10
 */
@RestController
@RequestMapping("/api/v1/users")
public class UsersController {

    @Autowired
    private UsersService usrService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Callable<ResponseObject> login(@RequestBody LoginObject login) {
        return new Callable<ResponseObject>() {
            @Override
            public ResponseObject call() throws Exception {
                return usrService.findByEmailAndPassword(login.getEmail(),login.getPassword());
            }
        };

    }
    
     @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Callable<ResponseObject> register(@RequestBody Users users) {
        return new Callable<ResponseObject>() {
            @Override
            public ResponseObject call() throws Exception {
                return usrService.register(users);
            }
        };

    }
    
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Callable<ResponseObject> update(@RequestBody Users users){
        return new Callable<ResponseObject>(){
            @Override
            public ResponseObject call() throws Exception {
                return usrService.update(users);
            }
        };
    }
    
}
