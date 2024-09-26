package com.gaurav.project.uber.uberApp.services;

import com.gaurav.project.uber.uberApp.dto.DriverDto;
import com.gaurav.project.uber.uberApp.dto.SignupDto;
import com.gaurav.project.uber.uberApp.dto.UserDto;


public interface AuthService {
    String[] login(String email,String password);
    UserDto signup(SignupDto signupDto);
    DriverDto onBoardNewDriver(Long userId, String vehicleId);

    String refreshToken(String refreshToken);
}
