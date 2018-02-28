package com.krvang.lindved.dicecup;

import android.content.Context;
import android.content.Intent;
import android.os.Debug;
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

import com.krvang.lindved.dicecup.be.Roll;
import com.krvang.lindved.dicecup.model.RollModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RollActivity extends AppCompatActivity {

    private final int ROW_DIVIDER = 3;

    private Button mRollButton;
    private ImageButton mHistoryButton;
    private LinearLayout mDiceLayout;
    private Spinner mNumbersSpinner;

    private List<Dice> mDices;
    private RollModel mRollModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roll);

        mRollModel = RollModel.getInstance();

        initializeSpinner();
        initializeLayout();
    }

    private void initializeLayout(){
        mDiceLayout = findViewById(R.id.diceLayout);
        createDiceBoard(Integer.parseInt(mNumbersSpinner.getSelectedItem().toString()));
    }

    private void initializeButtons(){
        mRollButton = findViewById(R.id.btnRoll);
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

    public void goToHistoryActivity(View view){
        //TODO RKL: This method isn't implemented for indented purpose.
//        for(Roll roll: mRollModel.getRolls()){
//            for(int number: roll.getNumber()){
//                Log.v("TEST", number + "");
//            }
//            Log.v("TEST", "-----------------------------------------");
//        }
        Intent intent = HistoryActivity.getIntent(this);
        startActivity(intent);
    }

    private void createDiceBoard(int amount){
        mDices = new ArrayList<>();
        mDiceLayout.removeAllViews();
        LinearLayout firstRow = new LinearLayout(this);
        firstRow.setGravity(Gravity.CENTER);
        for (int i = 0; i < ROW_DIVIDER; i++){
            if(i < amount){
                createSingleDice(firstRow, mDices);
            }
        }
        mDiceLayout.addView(firstRow);
        if(amount > ROW_DIVIDER){
            LinearLayout secondRow = new LinearLayout(this);
            secondRow.setGravity(Gravity.CENTER);
            for(int i = ROW_DIVIDER; i < amount; i++){
                createSingleDice(secondRow, mDices);
            }
            mDiceLayout.addView(secondRow);
        }
    }

    private void createSingleDice(LinearLayout layout, List<Dice> list){
        Dice dice = new Dice(this);
        dice.setPadding(5, 5, 5, 5);
        list.add(dice);
        layout.addView(dice);
    }

    public void rollDices(View view){
        List<Integer> rolls = new ArrayList<>();
        for(Dice dice: mDices){
            dice.rollDice();
            rolls.add(dice.getVale());
        }
        Roll roll = new Roll(rolls);
        mRollModel.addRoll(roll);
    }



    private class Dice extends AppCompatImageView {

        private final int MAX = 6;

        private int mValue;

        public Dice(Context context) {
            super(context);
            rollDice();
        }

        public int getVale(){
            return mValue;
        }

        public void rollDice(){
            Random rand = new Random();
            mValue = rand.nextInt(MAX) + 1;
            setImage();
        }

        private void setImage(){
            switch (mValue){
                case 1:{
                    setImageResource(R.drawable.dice_one);
                    break;
                }
                case 2:{
                    setImageResource(R.drawable.dice_two);
                    break;
                }
                case 3:{
                    setImageResource(R.drawable.dice_three);
                    break;
                }
                case 4:{
                    setImageResource(R.drawable.dice_four);
                    break;
                }
                case 5:{
                    setImageResource(R.drawable.dice_five);
                    break;
                }
                case 6:{
                    setImageResource(R.drawable.dice_six);
                    break;
                }
            }
        }
    }
}
