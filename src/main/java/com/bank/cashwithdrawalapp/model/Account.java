package com.bank.cashwithdrawalapp.model;

import jakarta.persistence.*;

@Entity
@Table(name="account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer accountId;

    Double balance;
    @ManyToOne
    @JoinColumn(name =  "account_holder_id")
    private AccountHolder accountHolder;

    public Account(){

    }

    public Account(Integer accountId, Double balance, AccountHolder accountHolder){
        this.accountId = accountId;
        this.balance = balance;
        this.accountHolder = accountHolder;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public AccountHolder getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(AccountHolder accountHolder) {
        this.accountHolder = accountHolder;
    }
}
