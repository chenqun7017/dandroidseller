package com.kejiang.canyin.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.kejiang.canyin.activity.AddMenuActivity;
import com.kejiang.canyin.fragment.R;

public class MenuRightAdapter extends BaseAdapter {
    Context context;

    int postion;

    int selectPositon = -1;


    private TextView btn_edit;
    private TextView btn_stop;

    private boolean State = false;
    private boolean StateStop = false;


    public MenuRightAdapter(Context context, int postion) {
        this.context = context;
        this.postion = postion;
    }

    @Override
    public int getCount() {
        return 7;
    }

    @Override
    public Object getItem(int arg0) {
        return getItem(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(int arg0, View view, ViewGroup arg2) {
        view = LayoutInflater.from(context).inflate(R.layout.item_fg_rightmenu, null);

        btn_edit = (TextView) view.findViewById(R.id.btn_edit);
        btn_stop = (TextView) view.findViewById(R.id.btn_stop);
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  in=new Intent(context, AddMenuActivity.class);
                context.startActivity(in);
                if (State) {
                    Toast.makeText(context,"123",Toast.LENGTH_SHORT).show();
                    btn_edit.setBackgroundResource(R.drawable.editorial_nor);
                    State = false;
                } else {
                    btn_edit.setBackgroundResource(R.drawable.editorial_pre);
                    State = true;
                }

            }
        });

        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"321",Toast.LENGTH_SHORT).show();
                if (StateStop) {
                    btn_stop.setBackgroundResource(R.drawable.discontinued_nor);
                    StateStop = false;
                } else {
                    btn_stop.setBackgroundResource(R.drawable.discontinued_nor);
                    StateStop = true;
                }

            }
        });

        return view;

    }




    public void setSelectedPosition(int positon) {
        selectPositon = positon;
    }

}
