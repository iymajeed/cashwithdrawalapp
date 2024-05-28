package com.bank.cashwithdrawalapp.service;

import com.bank.cashwithdrawalapp.model.AccountHolder;
import com.bank.cashwithdrawalapp.repository.AccountHolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountHolderServiceImpl implements AccountHolderService{

    @Autowired
    AccountHolderRepository accountHolderRepository;

    public AccountHolderServiceImpl(){

    }

    @Override
    public AccountHolder getAccountHolderByPin(Integer pin){
        return accountHolderRepository.findByPin(pin.intValue());
    }

    @Override
    public List<AccountHolder> findAll(){
        return accountHolderRepository.findAll();
    }

}
