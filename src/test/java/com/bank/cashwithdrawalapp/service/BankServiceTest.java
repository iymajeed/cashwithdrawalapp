package com.bank.cashwithdrawalapp.service;

import com.bank.cashwithdrawalapp.model.Account;
import com.bank.cashwithdrawalapp.model.AccountHolder;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
public class BankServiceTest {

    @InjectMocks
    BankServiceImpl bankService;

    //@InjectMocks
    //AccountHolderService accountHolderService;

    //@Mock
    //AccountHolderRepository accountHolderRepository;

    AccountHolder accountHolder;
    Account account;

    @Before("")
    public void setUp(){
        account = createAccount();
        accountHolder = createAccountHolder();
        updateAccount(account, accountHolder);
    }

    // AccountHolder accountHolder1 = Mockito.when(accountHolderRepository.findByPin(1122)).thenReturn(accountHolder)

    @Test
    public void TestIsValidAccountHolder(){
        List<Account> accountList = new ArrayList<>();
        accountList.add(new Account(1,1000.0,null));
        AccountHolder accountHolder1 = new AccountHolder(1,"John",1122,accountList);
        assert(bankService.isValidAccountHolder(accountHolder1,"1122"));
    }

    public AccountHolder createAccountHolder(){
        return new AccountHolder(1,"John", 1122, createAccuntList());
    }

    public List<Account> createAccuntList(){
        List<Account> accountList = new ArrayList<>();
        accountList.add(createAccount());
        return accountList;
    }

    public Account createAccount(){
        return new Account(1,1000.0,null);
    }

    public void updateAccount(Account account, AccountHolder accountHolder){
        account.setAccountHolder(accountHolder);
    }

}
