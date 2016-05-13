/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beacukai.repository;

import com.beacukai.entity.Users;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author Student10
 */
public interface UsersRepo extends PagingAndSortingRepository<Users, Long> {

    @Query("select e from Users e where e.email like %?1%")
    public Users findByEmail(String email);

    @Query("select e from Users e where e.email like %?1% and e.password like %?2% ")
    public Users findByEmailAndPassword(String email, String password);
}
