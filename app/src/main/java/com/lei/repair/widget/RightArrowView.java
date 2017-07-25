package com.lei.repair.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lei.repair.R;

/**
 * Created by szhua on 2017/7/23/023.
 * github:https://github.com/szhua
 * CarRepair
 * 通用rightArrowTextView
 */

public class RightArrowView extends RelativeLayout {


    private  int subTitleColor;
    private int  titleColor;
    private TextView title ;
    private ImageView icon ;
    private TextView subtitle ;


    public RightArrowView(Context context) {
        this(context,null,0);
    }
    public RightArrowView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }
    public RightArrowView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.right_arrow_layout,this);

        title = (TextView) findViewById(R.id.arrow_title);
        icon = (ImageView) findViewById(R.id.arrow_image);
        subtitle = (TextView) findViewById(R.id.arrow_sub_title);

        titleColor = ContextCompat.getColor(getContext(),R.color.text_color_normal) ;
        subTitleColor =ContextCompat.getColor(getContext(),R.color.text_color_gray) ;
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RightArrowView, defStyleAttr, 0);

        String titleStr =ta.getString(R.styleable.RightArrowView_title) ;
        String subTitleStr =ta.getString(R.styleable.RightArrowView_subtitle) ;
        titleColor =ta.getColor(R.styleable.RightArrowView_title_color,titleColor);
        subTitleColor =ta.getColor(R.styleable.RightArrowView_sub_title_color,subTitleColor);

        boolean showIcon =ta.getBoolean(R.styleable.RightArrowView_show_left_img,false) ;

        if(showIcon){
            icon.setVisibility(VISIBLE);
        }else{
            icon.setVisibility(GONE);
        }
        if(!TextUtils.isEmpty(titleStr)){
            title.setText(titleStr);
        }
        if(!TextUtils.isEmpty(subTitleStr)){
            subtitle.setText(subTitleStr);
        }
        title.setTextColor(titleColor);
        subtitle.setTextColor(subTitleColor);
    }


    public void setTitle(String titleStr){
        if(!TextUtils.isEmpty(titleStr))
        title.setText(titleStr);
    }

    public void setSubTitle(String subTitleStr){
        if(!TextUtils.isEmpty(subTitleStr)){
            subtitle.setText(subTitleStr);
        }
    }






}
