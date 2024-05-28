package com.bank.cashwithdrawalapp.service;

import com.bank.cashwithdrawalapp.model.Banknote;

import java.util.List;

public interface BanknoteService {
    public List<Banknote>  findAll();
    public String updateBanknotes(Double amount);
}
