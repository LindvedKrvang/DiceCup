package com.krvang.lindved.dicecup.model;

import com.krvang.lindved.dicecup.be.Roll;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lindved on 28-02-2018.
 */

public class RollModel {

    private static RollModel Instance;

    private List<Roll> mRolls;


    public static RollModel getInstance(){
        if(Instance == null){
            Instance = new RollModel();
        }
        return Instance;
    }

    private RollModel(){
        mRolls = new ArrayList<>();
    }

    public void addRoll(Roll roll){
        mRolls.add(roll);
    }

    public List<Roll> getRolls(){
        return mRolls;
    }

    public Roll getRoll(int position){
        if(position < mRolls.size())
            return mRolls.get(position);
        else
            return null;
    }

    public void clearRolls(){
        mRolls = new ArrayList<>();
    }
}
