package com.lei.repair.ui.fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lei.repair.R;
import com.lei.repair.adapter.MerChantOrderAdapter;
import com.lei.repair.base.BaseLoadMoreFragment;
import com.lei.repair.ui.activity.MerchantCheckMaintenanceDemand;
import com.lei.repair.util.AppUtil;
import com.runer.liabary.recyclerviewUtil.ItemDecorations;

/**
 * Created by szhua on 2017/7/22/022.
 * github:https://github.com/szhua
 * CarRepair
 * MerchantOderFragment
 * 商家订单管理列表界面
 */

public class MerchantOderFragment extends BaseLoadMoreFragment {


    public static  final String TO_CATCH_ORDER ="0";
    public  static  final String HAS_BAOJIA_ORDER ="1";
    public static  final String  GOT_ORDER="2";

    private MerChantOrderAdapter merChantOrderAdapter ;

    private String type ;
    public static  MerchantOderFragment getInstance(String type ){
        MerchantOderFragment merchantOderFragment =new MerchantOderFragment() ;
        merchantOderFragment.type =type ;
        return  merchantOderFragment ;
    }
    @Override
    public BaseQuickAdapter getAdater() {
        merChantOrderAdapter =new MerChantOrderAdapter(AppUtil.getTestData(),type) ;
        merChantOrderAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                transUi(MerchantCheckMaintenanceDemand.class,null);
            }
        });
        return merChantOrderAdapter;
    }
    @Override
    public void loadMore() {

    }

    @Override
    public RecyclerView.ItemDecoration getDecoration(Context context) {
        return ItemDecorations.vertical(context)
                .first(R.drawable.decoration_divider_6dp)
                .type(0, R.drawable.decoration_divider_6dp).create();
    }

    @Override
    public void refresh() {

    }
}
