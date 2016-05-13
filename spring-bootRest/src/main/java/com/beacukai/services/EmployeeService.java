/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beacukai.services;

import com.beacukai.dto.ResponseData;
import com.beacukai.dto.ResponseObject;
import com.beacukai.entity.Employee;
import com.beacukai.entity.LoginLog;
import com.beacukai.entity.Users;
import com.beacukai.repository.EmployeeRepo;
import com.beacukai.repository.LoginLogRepo;
import com.beacukai.repository.UsersRepo;
import com.beacukai.utility.TimeCalculation;
import java.util.Collection;
import javax.transaction.Transactional;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

/**
 *
 * @author Student10
 */
@Service("empService")
@Transactional
public class EmployeeService {
    @Autowired
    private EmployeeRepo empRepo;
    
    @Autowired
    private UsersRepo usrRepo;
    
    @Autowired
    private LoginLogRepo logRepo;
    
    private static final int PAGE_SIZE = 10;
    
    public ResponseObject insert(Employee emp){
        return new ResponseObject(true,"Saved",new ResponseData(1,empRepo.save(emp)));
    }
    
    public ResponseObject findAll(int page){
        
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users userLogin = usrRepo.findByEmail(user.getUsername());
        LoginLog log = logRepo.lastLog(userLogin.getId());
        
        if(TimeCalculation.diffHour(log.getLoginTime()) <= 0.001){
        PageRequest pageRequest = new PageRequest(page - 1, PAGE_SIZE);
        Collection<Employee> list = IteratorUtils.toList(empRepo.findAll(pageRequest).iterator());
        return new ResponseObject(true, "Employee List", new ResponseData(list.size(), list));
            
        } else {
            return new ResponseObject(false, "Please Login", null);
        }
        
        
    }
    public ResponseObject findByName(String name){
        Collection<Employee> list = IteratorUtils.toList(empRepo.findByName(name).iterator());
        return new ResponseObject(true, "Employee List By Name", new ResponseData(list.size(),list));
    }
    
    public ResponseObject findById(long id){
        return new ResponseObject(true, "Get One Record", new ResponseData(id, empRepo.findOne(id)));
    }
    
}
