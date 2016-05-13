/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beacukai.dto;

import java.io.Serializable;

/**
 *
 * @author Student10
 */
public class PushMessageObject implements Serializable{
    private Long userId;
    private String title;
    private String message;
    private Long employeeId;

    public PushMessageObject() {
    }

    public PushMessageObject(Long userId, String title, String message, Long employeeId) {
        this.userId = userId;
        this.title = title;
        this.message = message;
        this.employeeId = employeeId;
    }



    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
    
    
}
