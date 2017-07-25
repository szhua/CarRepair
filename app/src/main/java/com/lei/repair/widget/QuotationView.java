package com.lei.repair.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.lei.repair.R;

/**
 * Created by szhua on 2017/7/23/023.
 * github:https://github.com/szhua
 * CarRepair
 * QuotationView
 * 填写报价View
 */

public class QuotationView extends LinearLayout {
    public QuotationView(Context context) {
        super(context);
    }

    public QuotationView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public QuotationView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.quotation_input_layout,this);
    }
}
