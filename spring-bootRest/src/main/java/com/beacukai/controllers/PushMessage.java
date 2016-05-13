/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beacukai.controllers;

import com.beacukai.dto.PushMessageObject;
import com.beacukai.entity.Users;
import com.beacukai.services.UsersService;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Student10
 */
@RestController
@RequestMapping("/api/v1/pushMessages")
public class PushMessage {

    @Autowired
    private UsersService userService;

    @RequestMapping(method = RequestMethod.POST)
    public String pushMessages(@RequestBody PushMessageObject push) {

        Users users = userService.findById(push.getUserId());
        String output = "";
        if (users.getNotifId() == null) {
            output = "{'messages':'GCM Not Send, notif_id null'}";
        } else {
            try {
                String jsonRespone;

                URL url = new URL("https://onesignal.com/api/v1/notifications");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setUseCaches(false);
                con.setDoOutput(true);
                con.setDoInput(true);

                con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                con.setRequestProperty("Authorization", "Basic NDIwZmI5YTAtNmQ1ZS00Y2EwLWIyZTQtNWEwYWZmMzNkZGE3");
                con.setRequestMethod("POST");

                String strJsonBody = "{"
                        + "\"app_id\": \"60645d1e-53ed-4bd2-8990-85abce0a5d2b\","
                        + "\"headings\": {\"en\": \"" + push.getTitle() + "\"},"
                        + "\"contents\": {\"en\": \"" + push.getMessage() + "\"},"
                        + "\"data\": {\"employeeId\": \"" + push.getEmployeeId() + "\"},"
//                        + "\"large_icon\": \"http://bctemas.beacukai.go.id/wp-content/uploads/2014/01/logo2.png\","
                        + "\"include_player_ids\": [\"" + users.getNotifId().trim() + "\"]}";

                System.out.println("strJsonBody:\n" + strJsonBody);

                byte[] sendBytes = strJsonBody.getBytes("UTF-8");
                con.setFixedLengthStreamingMode(sendBytes.length);

                OutputStream outputStream = con.getOutputStream();
                outputStream.write(sendBytes);

                int httpResponse = con.getResponseCode();
                System.out.println("httpResponse: " + httpResponse);

                if (httpResponse >= HttpURLConnection.HTTP_OK
                        && httpResponse < HttpURLConnection.HTTP_BAD_REQUEST) {
                    Scanner scanner = new Scanner(con.getInputStream(), "UTF-8");
                    jsonRespone = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
                    scanner.close();
                } else {
                    Scanner scanner = new Scanner(con.getErrorStream(), "UTF-8");
                    jsonRespone = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
                    scanner.close();
                }
                output = jsonRespone;

            } catch (Throwable t) {
                t.printStackTrace();
            }
        }

        return output;
    }
}
