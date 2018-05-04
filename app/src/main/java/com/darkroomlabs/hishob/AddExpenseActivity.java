package com.darkroomlabs.hishob;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class AddExpenseActivity extends AppCompatActivity {
    static Expense myExpense;
    static String addExpenseErrString;
    static boolean isValidExpense;
    static boolean inputError;
    public AddExpenseActivity() {
        isValidExpense = false;
        myExpense = new Expense();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RadioGroup radioGroup_typeof_Transaction = (RadioGroup) findViewById(R.id.radioGroup_typeOfTransaction);

        //RadioGroup radioGroup_paymentMode = (RadioGroup) findViewById(R.id.radioGroup_PaymentMode);

        Spinner spinnerPaymentModes = (Spinner) findViewById(R.id.spinnerPaymentModes);

        LinkedList<String> listPaymentModes = DataManager.getInstance().getPaymentModes();

        ArrayAdapter<String> adapterPaymentModes = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listPaymentModes);

        adapterPaymentModes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPaymentModes.setAdapter(adapterPaymentModes);

        EditText primaryCategory = findViewById(R.id.editText_primaryCategory);
        EditText subCategory = findViewById(R.id.editText_subCategory);
        EditText amount = findViewById(R.id.editText_amount);

        Button btn_addExpense = findViewById(R.id.btn_addExpense);

        Log.d("dev_abhi","isValidExpense 1 : "+isValidExpense);
        radioGroup_typeof_Transaction.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton selectedRadioButton = (RadioButton) findViewById(checkedId);
                AddExpenseActivity.myExpense.setTypeof_Transaction(selectedRadioButton.getText().toString());
                //Log.d("dev_abhi", "typeof_Transaction: "+selectedRadioButton.getText().toString());
            }
        });
        /*radioGroup_paymentMode.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton selectedRadioButton = (RadioButton) findViewById(checkedId);
                AddExpenseActivity.myExpense.setModeof_Payment(selectedRadioButton.getText().toString());
                //Log.d("dev_abhi", "paymentMode: "+selectedRadioButton.getText().toString());
            }
        });*/

        primaryCategory.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() != 0) {
                    AddExpenseActivity.myExpense.setPrimaryCategory(s.toString());
                    //Log.d("dev_abhi", "primaryCategory: "+AddExpenseActivity.myExpense.getPrimaryCategory());
                }
                else {
                    AddExpenseActivity.myExpense.setPrimaryCategory(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        subCategory.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() != 0) {
                    AddExpenseActivity.myExpense.setSubCategory(s.toString());
                    //Log.d("dev_abhi", "subCategory: "+AddExpenseActivity.myExpense.getSubCategory());
                }
                else {
                    AddExpenseActivity.myExpense.setSubCategory(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        amount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() != 0) {
                    AddExpenseActivity.myExpense.setAmount(s.toString());
                    //Log.d("dev_abhi", "amount: "+AddExpenseActivity.myExpense.getAmount());
                }
                else {
                    AddExpenseActivity.myExpense.setAmount(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btn_addExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddExpenseActivity.myExpense.setModeof_Payment(spinnerPaymentModes.getSelectedItem().toString());
                HashMap<String, Integer> addExpenseErrorMap = Validator.validateAddExpense(AddExpenseActivity.myExpense);
                Log.d("dev_abhi","addExpenseErrorMap: "+addExpenseErrorMap.toString());

                for (Map.Entry<String, Integer> entry : addExpenseErrorMap.entrySet()) {
                    if(entry.getValue()!=0){
                        AddExpenseActivity.addExpenseErrString = entry.getKey() + " is invalid";
                        AddExpenseActivity.inputError = true;
                        break;
                    }
                    else
                        AddExpenseActivity.inputError = false;
                }
                Log.d("dev_abhi","AddExpenseActivity.inputError after: "+AddExpenseActivity.inputError);


                if(AddExpenseActivity.inputError)
                    AddExpenseActivity.isValidExpense = false;
                else
                    AddExpenseActivity.isValidExpense = true;
                Log.d("dev_abhi","isValidExpense after: "+AddExpenseActivity.isValidExpense);
                if (AddExpenseActivity.isValidExpense) {
                    Intent displayExpenseActivityIntent = new Intent(AddExpenseActivity.this, DisplayExpenseActivity.class);

                    Bundle dataBundle = new Bundle();
                    dataBundle.putSerializable("myExpense",AddExpenseActivity.myExpense);

                    displayExpenseActivityIntent.putExtras(dataBundle);


                    AddExpenseActivity.this.startActivity(displayExpenseActivityIntent);
                }
                else {
                    Toast.makeText(AddExpenseActivity.this, AddExpenseActivity.addExpenseErrString, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
