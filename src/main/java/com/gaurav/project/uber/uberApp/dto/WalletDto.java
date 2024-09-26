package com.gaurav.project.uber.uberApp.dto;

import com.gaurav.project.uber.uberApp.entities.User;
import com.gaurav.project.uber.uberApp.entities.WalletTransactions;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
public class WalletDto {

    private Long id;


    private UserDto user;

    private Double balance;


    private List<WalletTransactionDto> transactions;
}
