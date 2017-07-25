package com.lei.repair.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lei.repair.R;
import com.lei.repair.ui.fragment.MerchantOderFragment;

import java.util.List;

/**
 * Created by szhua on 2017/7/22/022.
 * github:https://github.com/szhua
 * CarRepair
 * MerChantOrderAdapter
 */

public class MerChantOrderAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    private String orderType ;
    public MerChantOrderAdapter(@Nullable List<String> data ,String type) {
        super(R.layout.item_merchant_order_layout,data);
        this.orderType =type ;
    }
    @Override
    protected void convert(BaseViewHolder helper, String item) {
        //判断不同的状态并且设置上
        if(MerchantOderFragment.TO_CATCH_ORDER.equals(orderType)){
            helper.setVisible(R.id.baojia_container,false) ;
            helper.setVisible(R.id.state_tv,false) ;
            helper.setText(R.id.order_bt,"抢单");
        }else if(MerchantOderFragment.HAS_BAOJIA_ORDER.equals(orderType)){
            helper.setVisible(R.id.baojia_container,true) ;
            helper.setVisible(R.id.state_tv,false) ;
            helper.setText(R.id.order_bt,"查看详情");
        }else{
            helper.setVisible(R.id.baojia_container,true) ;
            helper.setVisible(R.id.state_tv,true) ;
            helper.setText(R.id.order_bt,"查看详情");
        }
    }
}
