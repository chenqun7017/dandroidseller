package com.kejiang.canyin.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kejiang.canyin.adapter.SalesSetAdapter;
import com.kejiang.canyin.base.BaseActivity;
import com.kejiang.canyin.base.BaseConstant;
import com.kejiang.canyin.fragment.R;
import com.kejiang.canyin.util.NumberUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.kejiang.canyin.fragment.R.id.et_allprice;
import static com.kejiang.canyin.fragment.R.id.et_delprice;

/**
 * Created by chenqun on 2017/7/31.
 */

public class SaleSetActivity extends BaseActivity{
    //头部控件
    @BindView(R.id.rl_back)
    public RelativeLayout rl_back;
    @BindViews({R.id.tv_title, R.id.tv_end})
    public List<TextView> textViewList;

    @OnClick(R.id.rl_back)
    public  void back(){
        finish();
    }

    @BindView(R.id.tv_add_price)
    public TextView tv_add_price;
    @OnClick(R.id.tv_add_price)
    public void price(){
        prices();
    }



    @BindViews({R.id.et_fistprice, et_allprice, et_delprice})
    public List<EditText> editTextList;
  @BindView(R.id.lv_sales)
    public ListView lv_sales;

    @BindView(R.id.check_selectprice)
    public CheckBox check_selectprice;

    //获取用户输入内容
    private  String fistprice;
    private  String allprice;
    private  String delprice;

    private SalesSetAdapter salesSetAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);
        ButterKnife.bind(this);
        initViews();

    }

    @Override
    protected void initViews() {
        if (BaseConstant.price.size()>0){
            salesSetAdapter=new SalesSetAdapter(this,BaseConstant.price);
            lv_sales.setAdapter(salesSetAdapter);
        }


        if (rl_back.getVisibility() != View.VISIBLE) {
            rl_back.setVisibility(View.VISIBLE);
        }
        if (textViewList.get(0).getVisibility() != View.VISIBLE) {
            textViewList.get(0).setVisibility(View.VISIBLE);
            textViewList.get(0).setText("促销活动");
        }
        if (textViewList.get(1).getVisibility() != View.GONE) {
            textViewList.get(1).setVisibility(View.GONE);
        }

        check_selectprice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    showToast("??");
                } else {
                    showToast("!!");
                }
            }
        });
    }

    public void prices(){
        allprice=editTextList.get(1).getText().toString();
        delprice=editTextList.get(2).getText().toString();
        if (!NumberUtil.isNull(allprice)&&!NumberUtil.isNull(delprice)){
            int x=Integer.parseInt(allprice);
            int y=Integer.parseInt(delprice);
            if (x>y){
                if (BaseConstant.getPrice().size()<=9){
                    if (BaseConstant.getPrice().size()==0){
                        BaseConstant.getPrice().add("满"+allprice+"减"+delprice);
                    } else {
                        for(int j=0;j<BaseConstant.getPrice().size();j++){
                            if (BaseConstant.getPrice().get(j).equals("满"+allprice+"减"+delprice)){
                                showToast("促销价格已存在");
                                return;
                            }
                        }
                        BaseConstant.getPrice().add("满"+allprice+"减"+delprice);

                    }
                    salesSetAdapter=new SalesSetAdapter(this,BaseConstant.price);
                    lv_sales.setAdapter(salesSetAdapter);
                    salesSetAdapter.notifyDataSetChanged();
                }else{
                    showToast("只能设置10条");
                }

            }else {
                showToast("价格不合理");
            }
        }else{
            showToast("价格不能为空");
        }
    }


}
