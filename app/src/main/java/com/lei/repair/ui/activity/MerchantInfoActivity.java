package com.lei.repair.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lei.repair.R;
import com.lei.repair.adapter.MechantEvalutionAdapter;
import com.lei.repair.base.BaseActivity;
import com.lei.repair.util.AppUtil;
import com.runer.liabary.recyclerviewUtil.ItemDecorations;
import com.runer.liabary.recyclerviewUtil.VerticalItemDecoration;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MerchantInfoActivity extends BaseActivity {

    @InjectView(R.id.evaluation_list)
    RecyclerView evaluationList;

    private MechantEvalutionAdapter evalutionAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_info);
        ButterKnife.inject(this);

        evalutionAdapter =new MechantEvalutionAdapter(AppUtil.getTestData()) ;
        evaluationList.addItemDecoration(ItemDecorations.vertical(this)
                .type(0, R.drawable.item_decoration_shape).create());
        evaluationList.setLayoutManager(new LinearLayoutManager(this));
        evaluationList.setAdapter(evalutionAdapter);
    }
}
