package com.bank.cashwithdrawalapp.repository;

import com.bank.cashwithdrawalapp.model.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountHolderRepository extends JpaRepository<AccountHolder,Integer> {

    //@Query("Select a from AccountHolder a where a.pin=?1")
    public AccountHolder findByPin(int pin);

}

