package com.bank.cashwithdrawalapp.repository;

import com.bank.cashwithdrawalapp.model.Banknote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Banknote,Integer> {
}
