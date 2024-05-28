package com.bank.cashwithdrawalapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Banknote {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Integer id;
        Integer noteValue;
        Integer quantity;

        public Banknote(){}

        public Integer getId() {
                return id;
        }

        public void setId(Integer id) {
                this.id = id;
        }

        public Integer getNoteValue() {
                return noteValue;
        }

        public void setNoteValue(Integer noteValue) {
                this.noteValue = noteValue;
        }

        public Integer getQuantity() {
                return quantity;
        }

        public void setQuantity(Integer quantity) {
                this.quantity = quantity;
        }
}
