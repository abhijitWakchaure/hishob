package com.darkroomlabs.hishob;

import android.util.Log;
import android.widget.Toast;

import java.util.Arrays;
import java.util.HashMap;

final class Validator {
    public static String[] transactionTypes = {"Debit","Credit","Transfer"};
    public static String[] modeofPayment = {"Cash","Splitwise","HDFC Credit","HDFC Debit","BOM Debit"};

    public static HashMap<String,Integer> validateAddExpense(Expense myExpense){

        Log.d("dev_abhi","Validating: "+myExpense);
        HashMap<String,Integer> addExpenseErrorMap = new HashMap<>();

        //Errors
        addExpenseErrorMap.put("Transaction Type",0);
        addExpenseErrorMap.put("Payment Mode",0);
        addExpenseErrorMap.put("Primary Category",0);
        addExpenseErrorMap.put("Description",0);
        addExpenseErrorMap.put("Amount",0);


        if (!Arrays.asList(transactionTypes).contains(myExpense.getTypeof_Transaction()))
            addExpenseErrorMap.put("Transaction Type",1);
        if (!Arrays.asList(modeofPayment).contains(myExpense.getModeof_Payment()))
            addExpenseErrorMap.put("Payment Mode",1);
        if (myExpense.getPrimaryCategory() == null)
            addExpenseErrorMap.put("Primary Category",1);
        if (myExpense.getSubCategory() == null)
            addExpenseErrorMap.put("Description",1);
        if (myExpense.getAmount() == null || myExpense.getAmount().length() == 0)
            addExpenseErrorMap.put("Amount",1);
        else {
            Double amount = Double.parseDouble(myExpense.getAmount());
            if (amount <= 0.00)
                addExpenseErrorMap.put("Amount", 1);
        }
        return addExpenseErrorMap;
    }
}
