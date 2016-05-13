/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beacukai.repository;

import com.beacukai.entity.LoginLog;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author Student10
 */
public interface LoginLogRepo extends PagingAndSortingRepository<LoginLog, Long>{
    @Query("select e from LoginLog e where e.users.id =?1")
    public LoginLog lastLog(Long userId);
}
