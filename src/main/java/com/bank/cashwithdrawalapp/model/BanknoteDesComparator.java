package com.bank.cashwithdrawalapp.model;

import java.util.Comparator;

public class BanknoteDesComparator implements Comparator<Banknote> {
    public int compare(Banknote bNote1, Banknote bNote2){
        return bNote2.getNoteValue() - bNote1.getNoteValue();
    }
}
