package com.darkroomlabs.hishob;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button AddNewExpenseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AddNewExpenseButton = (Button) findViewById(R.id.button);
        AddNewExpenseButton.setOnClickListener(MainActivity.this);

    }

    @Override
    public void onClick(View v) {
        Intent AddExpenseActivityIntent = new Intent(this, AddExpenseActivity.class);
        startActivity(AddExpenseActivityIntent);
    }
}
