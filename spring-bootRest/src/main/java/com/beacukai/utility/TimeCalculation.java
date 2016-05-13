/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beacukai.utility;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author Student10
 */
public class TimeCalculation {
    static DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    static SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    public static double diffHour(Date logDate){
        LocalDateTime dateTime1 = LocalDateTime.parse(fmt.format(logDate), formater);
        LocalDateTime dateTime2 = LocalDateTime.parse(fmt.format(new Date()), formater);
        long diffMinutes = java.time.Duration.between(dateTime2, dateTime2).toMinutes();
        return (double) diffMinutes / 60;
    }
}
