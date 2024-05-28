package com.bank.cashwithdrawalapp.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="accountholder")
public class AccountHolder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer pin;

    @OneToMany( mappedBy = "accountHolder",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    List<Account> accountList = new ArrayList<>();

    public AccountHolder(){}

    public AccountHolder(Integer id, String name, Integer pin, List<Account> accountList){
        this.id = id;
        this.name = name;
        this.pin = pin;
        this.accountList = accountList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPin() {
        return pin;
    }

    public void setPin(Integer pin) {
        this.pin = pin;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }
}
