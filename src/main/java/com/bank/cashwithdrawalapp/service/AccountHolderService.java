package com.bank.cashwithdrawalapp.service;

import com.bank.cashwithdrawalapp.model.AccountHolder;

import java.util.List;

public interface AccountHolderService {
    public AccountHolder getAccountHolderByPin(Integer pin);
    public List<AccountHolder> findAll();
}
