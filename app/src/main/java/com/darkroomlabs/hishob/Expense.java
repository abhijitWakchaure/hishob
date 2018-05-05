package com.darkroomlabs.hishob;

import java.io.Serializable;
import java.util.Date;

class Expense implements Serializable {
    private Long id;
    private String typeof_Transaction;
    private String modeof_Payment;
    private String primaryCategory;
    private String subCategory;
    private String amount;

    public Expense(){
        id = new Date().getTime();
        amount = "0.00";
    }

    @Override
    public String toString() {
        return "tt: "+typeof_Transaction+" | pm: "+modeof_Payment+" | pc: "+primaryCategory+" | sc: "+subCategory+" | am: "+amount;
    }


    public String getTypeof_Transaction() {
        return typeof_Transaction;
    }

    public void setTypeof_Transaction(String typeof_Transaction) {
        this.typeof_Transaction = typeof_Transaction;
    }

    public String getModeof_Payment() {
        return modeof_Payment;
    }

    public void setModeof_Payment(String modeof_Payment) {
        this.modeof_Payment = modeof_Payment;
    }

    public String getPrimaryCategory() {
        return primaryCategory;
    }

    public void setPrimaryCategory(String primaryCategory) {
        this.primaryCategory = primaryCategory;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }
}
