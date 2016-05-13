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
public class ResponseData implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private long totalRows;
    private Object rows;
    
    public ResponseData(){
        
    }

    public ResponseData(long totalRows, Object rows) {
        this.totalRows = totalRows;
        this.rows = rows;
    }
    

    public long getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(long totalRows) {
        this.totalRows = totalRows;
    }

    public Object getRows() {
        return rows;
    }

    public void setRows(Object rows) {
        this.rows = rows;
    }
    
    
}
