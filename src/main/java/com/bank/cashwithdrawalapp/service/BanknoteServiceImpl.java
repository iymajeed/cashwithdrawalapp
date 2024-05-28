package com.bank.cashwithdrawalapp.service;

import com.bank.cashwithdrawalapp.model.Banknote;
import com.bank.cashwithdrawalapp.model.BanknoteDesComparator;
import com.bank.cashwithdrawalapp.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BanknoteServiceImpl implements BanknoteService {

    @Autowired
    NoteRepository noteRepository;

    @Override
    public List<Banknote> findAll(){
        return noteRepository.findAll();
    }

    @Override
    public String updateBanknotes(Double amount){
        StringBuilder message = new StringBuilder();
        Boolean dispensed = false;
        Double remainingAmount = amount;
        List<Banknote> banknoteList = noteRepository.findAll();
        banknoteList.sort(new BanknoteDesComparator());
        for(Banknote banknote : banknoteList){
            if(remainingAmount>=10.00){
                Integer note = banknote.getNoteValue();
                int noteForWithdrawal = Double.valueOf(remainingAmount/note).intValue();
                if(noteForWithdrawal>0 && banknote.getQuantity()>noteForWithdrawal){
                    message.append(noteForWithdrawal).append(" note of $").append(note).append("<br>");
                    remainingAmount = remainingAmount-(note*noteForWithdrawal);
                    banknote.setQuantity(banknote.getQuantity()-noteForWithdrawal);
                }
            }
        }

        if(remainingAmount>=10.00){
            message = new StringBuilder();
            //banknoteList = noteRepository.findAll();
        }else{
            for(Banknote banknote : banknoteList){
                noteRepository.save(banknote);
            }
        }

        return message.toString();
    }

}
