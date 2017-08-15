package com.kejiang.canyin.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.kejiang.canyin.activity.OrderDetailsActivity;
import com.kejiang.canyin.adapter.OrderFinishAdapter;
import com.kejiang.canyin.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chenqun on 2017/7/24.
 *
 */

public class OrderUnfinishedFragment extends BaseFragment {
    @BindView(R.id.lv_finish)
    public ListView listView;
    OrderFinishAdapter  orderFinishAdapter;
    private ImageButton btn_seemore;
    View rootView;
    @Override
    public View initView(LayoutInflater inflater) {
        rootView = inflater.inflate(R.layout.tab_finish_fragment, null);
        ButterKnife.bind( this , rootView ) ;
        orderFinishAdapter = new OrderFinishAdapter(getActivity());
        listView.setAdapter(orderFinishAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showCustomToast("测试");
                startActivity(OrderDetailsActivity.class);
            }
        });

        return rootView;
    }

}
