package com.gaurav.project.uber.uberApp.dto;

import com.gaurav.project.uber.uberApp.entities.Ride;
import com.gaurav.project.uber.uberApp.entities.Wallet;
import com.gaurav.project.uber.uberApp.entities.enums.TransactionMethod;
import com.gaurav.project.uber.uberApp.entities.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@Data
@Builder
public class WalletTransactionDto {
    private Long id;

    private Double amount;

    private TransactionType transactionType;

    private TransactionMethod transactionMethod;


    private RideDto ride;

    private String  transactionId;


    private WalletDto wallet;

    private LocalDateTime timeStamp;
}
