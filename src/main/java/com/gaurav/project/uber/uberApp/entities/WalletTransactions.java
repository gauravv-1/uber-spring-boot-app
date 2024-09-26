package com.gaurav.project.uber.uberApp.entities;

import com.gaurav.project.uber.uberApp.entities.enums.TransactionMethod;
import com.gaurav.project.uber.uberApp.entities.enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(
        indexes = {
                @Index(name = "idx_wallet_transactions_wallet",columnList = "wallet_id"),
                @Index(name = "idx_wallet_transactions_ride",columnList = "ride_id")
        }
)
public class WalletTransactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;  

    private TransactionType transactionType;

    private TransactionMethod transactionMethod;

    @ManyToOne
    private Ride ride;

    private String  transactionId;

    @ManyToOne
    private Wallet wallet;

    @CreationTimestamp
    private LocalDateTime timeStamp;

}
