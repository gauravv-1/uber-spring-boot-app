package com.gaurav.project.uber.uberApp.services.impl;

import com.gaurav.project.uber.uberApp.dto.WalletTransactionDto;
import com.gaurav.project.uber.uberApp.entities.WalletTransactions;
import com.gaurav.project.uber.uberApp.repositories.WalletTransactionRepository;
import com.gaurav.project.uber.uberApp.services.WalletTransactionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletTransactionServiceImpl implements WalletTransactionService {

    private final WalletTransactionRepository walletTransactionRepository;
    private final ModelMapper modelMapper;
    @Override
    public void createNewWalletTransaction(WalletTransactions walletTransaction) {
        walletTransactionRepository.save(walletTransaction);

    }
}
