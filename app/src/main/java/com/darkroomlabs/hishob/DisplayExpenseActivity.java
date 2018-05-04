package com.darkroomlabs.hishob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayExpenseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_expense);

        Bundle dataBundle = getIntent().getExtras();

        TextView myTextView = findViewById(R.id.textView);
        Expense myExpense = null;
        if (dataBundle != null) {
            myExpense = (Expense) dataBundle.getSerializable("myExpense");
            myTextView.setText("typeof_Transaction: "+myExpense.getTypeof_Transaction()+
                    "\nmodeof_Payment: "+myExpense.getModeof_Payment()+
                    "\nprimaryCategory: "+myExpense.getPrimaryCategory()+
                    "\nsubCategory: "+myExpense.getSubCategory()+
                    "\namount: "+myExpense.getAmount());
        }
        else
            myTextView.setText("Unable to get expense details");


    }
}
