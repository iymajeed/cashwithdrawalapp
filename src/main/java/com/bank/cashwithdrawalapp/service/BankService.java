package com.bank.cashwithdrawalapp.service;

import com.bank.cashwithdrawalapp.exception.AccountHolderNotFoundException;
import com.bank.cashwithdrawalapp.exception.AmountException;
import com.bank.cashwithdrawalapp.exception.BanknoteNotFountException;

public interface BankService {
    public String loginForWithDrawal(String pin) throws AccountHolderNotFoundException, BanknoteNotFountException;
    public String cashWithDrawal(String pin, String accountId, String amount) throws AmountException;
    //public Boolean isValidAccountHolder(AccountHolder accountHolder,String pin);
    //public Boolean isBanknoteAvailable(List<Banknote> banknoteList);
    //public String returnWelcomeMessageAndDetilsOfBanknote(AccountHolder accountHolder, List<Banknote> banknoteList);
    //public Boolean isAmountIsMultipleOfTens(Double amount);
    //public Boolean isWithdrawalAmountIsLessThanBalance(Account account, Double amount);
}
