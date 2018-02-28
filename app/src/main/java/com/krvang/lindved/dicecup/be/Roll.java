package com.krvang.lindved.dicecup.be;

import java.util.List;

/**
 * Created by Lindved on 28-02-2018.
 */

public class Roll {

    private List<Integer> mNumbers;
    private int mSum;

    public Roll(List<Integer> numbers) {
        mNumbers = numbers;
        mSum = 0;
        for(int number: mNumbers){
            mSum += number;
        }
    }

    public int getSum(){
        return mSum;
    }

    public List<Integer> getNumber(){
        return mNumbers;
    }
}
