/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beacukai.services;

import com.beacukai.dto.ResponseData;
import com.beacukai.dto.ResponseObject;
import com.beacukai.entity.LoginLog;
import com.beacukai.entity.Users;
import com.beacukai.repository.LoginLogRepo;
import com.beacukai.repository.UsersRepo;
import java.util.Collection;
import java.util.Date;
import javax.transaction.Transactional;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Student10
 */
@Service("usrService")
@Transactional
public class UsersService {

    @Autowired
    private UsersRepo usrRepo;

    @Autowired
    private LoginLogRepo loginLogRepo;

    public ResponseObject findByEmail(String email) {
        Users users = usrRepo.findByEmail(email);
        if (users != null) {
            return new ResponseObject(true, "Users By Email", new ResponseData(1, users));
        } else {
            return new ResponseObject(false, "Users By Email Not Found", null);
        }
    }

    public ResponseObject findByEmailAndPassword(String email, String password) {
        Users users = usrRepo.findByEmailAndPassword(email, password);
        if (users != null) {

            LoginLog log = loginLogRepo.lastLog(users.getId());
            if (log == null) {
                log = new LoginLog();
                log.setUsers(users);
            }
            log.setLoginTime(new Date());
            loginLogRepo.save(log);

            return new ResponseObject(true, "Users By Email and Password", new ResponseData(1, users));
        } else {
            return new ResponseObject(false, "Users By Email and Password Not Found", null);
        }
    }

    public ResponseObject register(Users users) {
        if (usrRepo.findByEmail(users.getEmail()) == null) {
            Users savedUsers = usrRepo.save(users);
            return new ResponseObject(true, "Users Saved", new ResponseData(1, savedUsers));
        } else {
            return new ResponseObject(false, "Please use another email", null);
        }
    }

    public ResponseObject update(Users users) {
        Users savedUsers = usrRepo.save(users);
        return new ResponseObject(true, "User Updated", new ResponseData(1, savedUsers));
    }

    public Users findById(Long id) {
        return usrRepo.findOne(id);
    }
}
