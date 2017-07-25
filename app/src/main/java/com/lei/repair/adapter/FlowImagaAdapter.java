package com.lei.repair.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lei.repair.R;
import com.runer.liabary.flowlayout.FlowLayout;
import com.runer.liabary.flowlayout.TagAdapter;

import java.util.List;

/**
 * Created by szhua on 2017/7/23/023.
 * github:https://github.com/szhua
 * CarRepair
 * FlowImagaAdapter
 图片Adapter
 */

public class FlowImagaAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public FlowImagaAdapter(@Nullable List<String> data) {
        super(R.layout.item_image_layout,data);
    }
    @Override
    protected void convert(BaseViewHolder helper, String item) {
    }
}
