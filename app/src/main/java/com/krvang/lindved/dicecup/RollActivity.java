package com.krvang.lindved.dicecup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;

public class RollActivity extends AppCompatActivity {

    private Button mRollButton;
    private ImageButton mHistoryButton;
    private LinearLayout mDiceLayout;
    private Spinner mNumbersSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roll);

        initializeLayout();
        initializeSpinner();
    }

    private void initializeLayout(){
        mDiceLayout = findViewById(R.id.diceLayout);
    }

    private void initializeSpinner(){
        mNumbersSpinner = findViewById(R.id.spnNumbers);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.number_of_dices, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mNumbersSpinner.setAdapter(adapter);
    }
}
