package com.bank.cashwithdrawalapp.service;

import com.bank.cashwithdrawalapp.exception.AccountHolderNotFoundException;
import com.bank.cashwithdrawalapp.exception.AmountException;
import com.bank.cashwithdrawalapp.exception.BanknoteNotFountException;
import com.bank.cashwithdrawalapp.model.Account;
import com.bank.cashwithdrawalapp.model.AccountHolder;
import com.bank.cashwithdrawalapp.model.Banknote;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankServiceImpl implements BankService{

    @Autowired
    AccountHolderService accountHolderService;
    @Autowired
    BanknoteService banknoteService;
    @Autowired
    AccountService accountService;

    @Override
    public String loginForWithDrawal(String pin)
            throws AccountHolderNotFoundException, BanknoteNotFountException {
        String message="";

        AccountHolder accountHolder = accountHolderService.getAccountHolderByPin(Integer.valueOf(pin));
        if(accountHolder==null || !isValidAccountHolder(accountHolder,pin)) {
            throw new AccountHolderNotFoundException("Account holder with pin : " + pin + " not found.");
        }

        List<Banknote> banknoteList = banknoteService.findAll();
        if(banknoteList==null || !isBanknoteAvailable(banknoteList)){
            throw new BanknoteNotFountException("Banknote not available for withdrawal.");
        }

        message =  returnWelcomeMessageAndDetailsOfBanknote(accountHolder,banknoteList)
                + "<br> For withdrawal call /bank/cash-with-drawal/{pin}?accountId=1&amount=100 ";
        return message;
    }

    @Override
    @Transactional
    public String cashWithDrawal(String pin, String accountId, String amount)
            throws AmountException{
        StringBuilder message = new StringBuilder();
        Double withDrawalAmount = Double.valueOf(amount);
        if(isAmountIsMultipleOfTens(withDrawalAmount)){
            AccountHolder accountHolder = accountHolderService.getAccountHolderByPin(Integer.valueOf(pin));
            Account account;
            if(accountHolder.getAccountList().size() == 1){
                account = accountHolder.getAccountList().get(0);
            }else{
                account = accountHolder.getAccountList()
                        .stream()
                        .filter(ac -> ac.getAccountId().equals(Integer.valueOf(accountId)))
                        .findFirst().get();
            }
            if(isWithdrawalAmountIsLessThanBalance(account, withDrawalAmount)){
                message.append(cashWithDrawal(account,withDrawalAmount));
            }else{
                throw new AmountException("Insufficient account balance.");
            }
        }else{
            throw new AmountException("Given withdrawal amount is not valid, it should be multiple of 10.");
        }
        return message.toString();

    }

    public String cashWithDrawal(Account account, Double amount) throws AmountException{
        StringBuilder dispensedNoteMessage = new StringBuilder(banknoteService.updateBanknotes(amount));
        if(dispensedNoteMessage.isEmpty()){
            throw new AmountException("Short of cash.");
        }else{
            account.setBalance(account.getBalance()-amount);
            accountService.updateAccount(account);
            dispensedNoteMessage.append("<br>Cash Withdrawal Successfully, Your Balance is ").append(account.getBalance());
        }
        return dispensedNoteMessage.toString();
    }

    public Boolean isValidAccountHolder(AccountHolder accountHolder, String pin){
        return accountHolder.getPin().equals(Integer.valueOf(pin));
    }

    public Boolean isBanknoteAvailable(List<Banknote> banknoteList){
        return !(banknoteList.stream()
                .filter(banknote -> banknote.getQuantity()>0)
                .toList()
                .isEmpty());

    }

    public String returnWelcomeMessageAndDetailsOfBanknote(AccountHolder accountHolder,
                                                          List<Banknote> banknoteList){
        StringBuilder banknoteDetails = new StringBuilder();
        banknoteDetails.append("Banknote Details:<br>");
        for(Banknote banknote : banknoteList){
            //this $ can be internationalized using local settings and resources
            banknoteDetails.append("$").append(banknote.getNoteValue())
                    .append(":").append(banknote.getQuantity())
                    .append("<br>");
        }
        return "Welcome " + accountHolder.getName() + "<br><br>"+banknoteDetails;
    }

    public Boolean isAmountIsMultipleOfTens(Double amount){
        return amount%10==0.00;
    }

    public Boolean isWithdrawalAmountIsLessThanBalance(Account account, Double amount){
        return account.getBalance()>amount;
    }

}
