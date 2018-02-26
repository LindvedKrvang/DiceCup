package com.krvang.lindved.dicecup;

import android.content.Context;
import android.support.v4.widget.ImageViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

public class RollActivity extends AppCompatActivity {

    private final int ROW_DIVIDER = 3;

    private Button mRollButton;
    private ImageButton mHistoryButton;
    private LinearLayout mDiceLayout;
    private Spinner mNumbersSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roll);

        initializeSpinner();
        initializeLayout();
    }

    private void initializeLayout(){
        mDiceLayout = findViewById(R.id.diceLayout);
        createDiceBoard(Integer.parseInt(mNumbersSpinner.getSelectedItem().toString()));
    }

    private void initializeSpinner(){
        mNumbersSpinner = findViewById(R.id.spnNumbers);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.number_of_dices, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mNumbersSpinner.setAdapter(adapter);
        mNumbersSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //TODO RKL: Show dice according to selected number.
                int dice = Integer.parseInt(parent.getItemAtPosition(position).toString());
                createDiceBoard(dice);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void createDiceBoard(int amount){
        mDiceLayout.removeAllViews();
        LinearLayout firstRow = new LinearLayout(this);
        firstRow.setGravity(Gravity.CENTER);
        for (int i = 0; i < ROW_DIVIDER; i++){
            if(i < amount){
                createSingleDice(firstRow);
            }
        }
        mDiceLayout.addView(firstRow);
        if(amount > ROW_DIVIDER){
            LinearLayout secondRow = new LinearLayout(this);
            secondRow.setGravity(Gravity.CENTER);
            for(int i = ROW_DIVIDER; i < amount; i++){
                createSingleDice(secondRow);
            }
            mDiceLayout.addView(secondRow);
        }
    }

    private void createSingleDice(LinearLayout layout){
        Dice dice = new Dice(this);
        layout.addView(dice);
    }

    class Dice extends AppCompatImageView {

        public Dice(Context context) {
            super(context);
            setImageResource(R.drawable.dice_one);
        }
    }
}
