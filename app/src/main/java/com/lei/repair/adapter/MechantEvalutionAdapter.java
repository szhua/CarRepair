package com.lei.repair.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lei.repair.R;

import java.util.List;

/**
 * Created by szhua on 2017/7/22/022.
 * github:https://github.com/szhua
 * CarRepair
 * MechantEvalutionAdapter
 * 维修厂评价adapter
 */

public class MechantEvalutionAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public MechantEvalutionAdapter(@Nullable List<String> data) {
        super(R.layout.item_eva_layout,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
