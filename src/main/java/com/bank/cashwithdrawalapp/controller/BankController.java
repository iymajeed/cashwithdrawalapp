package com.bank.cashwithdrawalapp.controller;

import com.bank.cashwithdrawalapp.exception.AccountHolderNotFoundException;
import com.bank.cashwithdrawalapp.exception.AmountException;
import com.bank.cashwithdrawalapp.exception.BanknoteNotFountException;
import com.bank.cashwithdrawalapp.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("bank")
public class BankController {

    @Autowired
    BankService bankService;

    /*
    public BankController(AccountHolderService accountHolderService,
                          BankService bankService,
                          BanknoteService banknoteService){
        this.accountHolderService = accountHolderService;
        this.bankService = bankService;
        this.banknoteService = banknoteService;
    }*/

    @GetMapping("/with-drawal/{pin}")
    public String loginForWithDrawal(@PathVariable String pin)
            throws AccountHolderNotFoundException, BanknoteNotFountException {

        return bankService.loginForWithDrawal(pin);

    }

    @GetMapping("/cash-with-drawal/{pin}")
    public String cashWithDrawal(@PathVariable String pin,
                                 @RequestParam String accountId,
                                 @RequestParam String amount)
            throws AmountException
    {
        return bankService.cashWithDrawal(pin,accountId,amount);
    }
}
