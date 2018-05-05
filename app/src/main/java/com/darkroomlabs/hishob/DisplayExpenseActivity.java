package com.darkroomlabs.hishob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

import static android.widget.Toast.*;

public class DisplayExpenseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_expense);

        Bundle dataBundle = getIntent().getExtras();

        Button btnEditTransaction = findViewById(R.id.btnEditTransaction);
        Button btnConfirmTransaction = findViewById(R.id.btnConfirmTransaction);

        EditText transactionID = findViewById(R.id.editText0);
        EditText transactionType = findViewById(R.id.editText1);
        EditText paymentMode = findViewById(R.id.editText2);
        EditText primaryCategory = findViewById(R.id.editText3);
        EditText description = findViewById(R.id.editText4);
        EditText amount = findViewById(R.id.editText5);

        transactionID.setEnabled(false);
        transactionType.setEnabled(false);
        paymentMode.setEnabled(false);
        primaryCategory.setEnabled(false);
        description.setEnabled(false);
        amount.setEnabled(false);

        if (dataBundle != null) {
            Expense myExpense;
            myExpense = (Expense) dataBundle.getSerializable("myExpense");

            transactionID.setText(myExpense.getId().toString());
            transactionType.setText(myExpense.getTypeof_Transaction());
            paymentMode.setText(myExpense.getModeof_Payment());
            primaryCategory.setText(myExpense.getPrimaryCategory());
            description.setText(myExpense.getSubCategory());
            amount.setText(myExpense.getAmount());
        }
        else {
            makeText(this, "Unable to get expense details", LENGTH_SHORT).show();
            btnConfirmTransaction.setEnabled(false);
        }

        btnEditTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayExpenseActivity.this.finish();
            }
        });

        btnConfirmTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayExpenseActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                makeText(DisplayExpenseActivity.this,"Transaction Added Successfully", LENGTH_SHORT).show();
                startActivity(intent);
                DisplayExpenseActivity.this.finish();
            }
        });

    }
}
