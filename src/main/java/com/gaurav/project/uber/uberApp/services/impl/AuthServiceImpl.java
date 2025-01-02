package com.gaurav.project.uber.uberApp.services.impl;

import com.gaurav.project.uber.uberApp.dto.DriverDto;
import com.gaurav.project.uber.uberApp.dto.SignupDto;
import com.gaurav.project.uber.uberApp.dto.UserDto;
import com.gaurav.project.uber.uberApp.entities.Driver;
import com.gaurav.project.uber.uberApp.entities.User;
import com.gaurav.project.uber.uberApp.entities.enums.Role;
import com.gaurav.project.uber.uberApp.exceptions.ResourceNotFoundException;
import com.gaurav.project.uber.uberApp.exceptions.RuntimeConflictException;
import com.gaurav.project.uber.uberApp.repositories.UserRepository;
import com.gaurav.project.uber.uberApp.security.JWTService;
import com.gaurav.project.uber.uberApp.services.AuthService;
import com.gaurav.project.uber.uberApp.services.DriverService;
import com.gaurav.project.uber.uberApp.services.RiderService;
import com.gaurav.project.uber.uberApp.services.WalletService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RiderService riderService;
    private final WalletService walletService;
    private final DriverService driverService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    @Override
    public String[] login(String email, String password) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email,password)
        );

        User user = (User) authentication.getPrincipal();
        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        return new String[] {accessToken,refreshToken};


    }


    @Transactional
    @Override
    public UserDto signup(SignupDto signupDto) {
        User user =  userRepository.findByEmail(signupDto.getEmail()).orElse(null);
        if(user!=null){
            throw new RuntimeConflictException("User Already Exits with email "+signupDto.getEmail());
        }

        User mappedUser = modelMapper.map(signupDto,User.class);

        mappedUser.setRoles(Set.of(Role.RIDER));
        mappedUser.setPassword(passwordEncoder.encode(mappedUser.getPassword()));
        User savedUser = userRepository.save(mappedUser);

        //create user related entities
       riderService.createNewRider(savedUser);
       walletService.createNewWallet(savedUser);



        return modelMapper.map(savedUser,UserDto.class);
    }

    @Override
    public DriverDto onBoardNewDriver(Long userId, String vehicleId) {
        log.info("In Service Method onBoardNewDriver");
        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User not found with id "+userId));
        if(user.getRoles().contains(Role.DRIVER)) throw new RuntimeException("User with id "+user+" is already a driver");
        Driver createDriver = Driver.builder()
                .user(user)
                .rating(0.0)
                .vehicleId(vehicleId)
                .available(true)
                .build();
        log.info("Driver obj craeted");

        user.getRoles().add(Role.DRIVER);
        userRepository.save(user);
        log.info("Driver obj saved");

        Driver savedDriver = driverService.createNewDriver(createDriver);
        log.info("Driver obj saved");

        return modelMapper.map(savedDriver,DriverDto.class);

    }

    @Override
    public String refreshToken(String refreshToken) {
        Long userId = jwtService.getUserIdFromToken(refreshToken);
        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User not found with user id "+userId));

        return jwtService.generateRefreshToken(user);
    }
}
