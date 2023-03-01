package com.spring.bike.bikeshopapp.common;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class ApiResponse {
    private final boolean success;
    private final String message;
    public boolean isSuccess(){return success;}
    public String getMessage(){return message;}
    public String getTimestamp(){return LocalDateTime.now().toString();}
}
