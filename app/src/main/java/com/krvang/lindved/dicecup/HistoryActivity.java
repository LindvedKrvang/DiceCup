package com.krvang.lindved.dicecup;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.krvang.lindved.dicecup.be.Roll;
import com.krvang.lindved.dicecup.model.RollModel;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private RollModel mRollModel;
    private ListView mList;
    private ListViewAdapter mAdapter;

    public static Intent getIntent(Context packageContext){
        Intent intent = new Intent(packageContext, HistoryActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        mRollModel = RollModel.getInstance();
        mList = findViewById(R.id.lstRolls);
        mAdapter = new ListViewAdapter();
        mList.setAdapter(mAdapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.history_menu_actionbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_clear:{
                mRollModel.clearRolls();
                mAdapter.notifyDataSetChanged();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private class ListViewAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return mRollModel.getRolls().size();
        }

        @Override
        public Object getItem(int position) {
            return mRollModel.getRoll(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            ViewHolder holder;

            if(view == null){
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.roll_list_item, null);
                holder = new ViewHolder();
                holder.mSum = view.findViewById(R.id.txtNumber);
                holder.mDices = view.findViewById(R.id.llDices);
                view.setTag(holder);
            }else
                holder = (ViewHolder) view.getTag();

            Roll roll = mRollModel.getRoll(position);
            holder.mSum.setText(roll.getSum() + "");

            holder.mDices.removeAllViews();
            for(int number: roll.getNumber()){
                DiceImage image = new DiceImage(parent.getContext());
                image.setImage(number);
                image.setPadding(15, 15, 15, 15);
                holder.mDices.addView(image);
            }

            return view;
        }
    }

    private class ViewHolder{
        TextView mSum;
        LinearLayout mDices;
    }

    private class DiceImage extends AppCompatImageView{

        public DiceImage(Context context) {
            super(context);
        }

        public void setImage(int value){
            switch (value){
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
