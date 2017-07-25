package com.lei.repair.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lei.repair.R;

import java.util.List;

/**
 * Created by szhua on 2017/7/24/024.
 * github:https://github.com/szhua
 * CarRepair
 * MetrialsFeeAdapter
 */

public class MetrialsFeeAdapter extends BaseQuickAdapter<String ,BaseViewHolder> {
    public MetrialsFeeAdapter(@Nullable List<String> data) {
        super(R.layout.item_matral_fee_layout,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
