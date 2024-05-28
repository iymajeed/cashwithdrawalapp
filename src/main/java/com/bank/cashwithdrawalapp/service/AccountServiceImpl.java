package com.bank.cashwithdrawalapp.service;

import com.bank.cashwithdrawalapp.model.Account;
import com.bank.cashwithdrawalapp.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    AccountRepository accountRepository;

    @Override
    public void updateAccount(Account account){
        accountRepository.save(account);
    }

}
