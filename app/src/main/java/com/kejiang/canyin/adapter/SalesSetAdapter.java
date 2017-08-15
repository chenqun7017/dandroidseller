package com.kejiang.canyin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kejiang.canyin.base.BaseConstant;
import com.kejiang.canyin.fragment.R;

import java.util.List;

/**
 * Created by chenqun on 2017/8/4.
 *
 */

public class SalesSetAdapter extends BaseAdapter {
    Context context;
    List   price;


    public  SalesSetAdapter(Context context,List price){
      this.context=context;
        this.price=price;
    }

    @Override
    public int getCount() {
        return price.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(context).inflate(R.layout.item_salesset, null);
        TextView tv_number=(TextView) convertView.findViewById(R.id.tv_number);
        tv_number.setText("促销"+ BaseConstant.NUMS[position]);

        TextView tv_price=(TextView) convertView.findViewById(R.id.tv_price);
        tv_price.setText(price.get(position).toString());
        TextView tv_del=(TextView) convertView.findViewById(R.id.tv_del);
        tv_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseConstant.price.remove(position);
                notifyDataSetChanged();
                //notifyDataSetInvalidated();
            }
        });

        return convertView;
    }
}
