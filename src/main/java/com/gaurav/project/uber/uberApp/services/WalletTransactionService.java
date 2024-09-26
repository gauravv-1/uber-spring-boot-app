package com.gaurav.project.uber.uberApp.services;

import com.gaurav.project.uber.uberApp.dto.WalletTransactionDto;
import com.gaurav.project.uber.uberApp.entities.WalletTransactions;

public interface WalletTransactionService {

    void createNewWalletTransaction(WalletTransactions walletTransaction);
}
