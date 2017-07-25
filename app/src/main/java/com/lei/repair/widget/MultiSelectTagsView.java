package com.lei.repair.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lei.repair.R;
import com.lei.repair.bean.TagBean;
import com.runer.liabary.flowlayout.FlowLayout;
import com.runer.liabary.flowlayout.TagAdapter;
import com.runer.liabary.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by szhua on 2017/7/19/019.
 * github:https://github.com/szhua
 * TouMaiNetApp
 * TagsView
 */

public class MultiSelectTagsView extends LinearLayout{

    private List<TagBean> tagBeanList ;
    private TagFlowLayout tagFlowLayout ;
    private TagsAdapter  tagsAdapter;

    public MultiSelectTagsView(Context context) {
        this(context,null,0);
    }
    public MultiSelectTagsView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    private int currentPose ;
    public MultiSelectTagsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.tags_layout,this);
        tagFlowLayout = (TagFlowLayout) findViewById(R.id.tag_flow_layout);
        tagFlowLayout.setMaxSelectCount(1);


    }

    public void setTagBeanList(List<TagBean> tagBeanList) {
        this.tagBeanList = tagBeanList;
        tagsAdapter =new TagsAdapter(tagBeanList) ;
        tagFlowLayout.setAdapter(tagsAdapter);
    }


    private class TagsAdapter extends TagAdapter<TagBean> {

        public TagsAdapter(List<TagBean> datas) {
            super(datas);
        }
        @Override
        public View getView(FlowLayout parent, int position, final TagBean tagBean) {
            View view =View.inflate(getContext(),R.layout.item_tags_layout,null);
            View delete =view.findViewById(R.id.delete) ;
            TextView tagName = (TextView) view.findViewById(R.id.tagname);
            tagName.setText(tagBean.getTagName());
            delete.setVisibility(View.GONE);
            if(tagBean.isSelected()){
                view.setSelected(true);
                view.setBackgroundResource(R.drawable.tag_bg_shape);
                tagName.setTextColor(ContextCompat.getColor(getContext(),R.color.white));
            }else{
                view.setSelected(false);
                view.setBackgroundResource(R.drawable.tag_bg_shape_un);
                tagName.setTextColor(ContextCompat.getColor(getContext(),R.color.text_color_gray));
            }
            tagName.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                      tagBean.setSelected(!tagBean.isSelected());
                      notifyDataChanged();
                }
            });
            return view;
        }
    }

    public  List<TagBean> getDatas(){
        List<TagBean >datas =new ArrayList<>() ;
        if(tagBeanList!=null){
            for (TagBean tagBean : tagBeanList) {
                if(tagBean.isSelected()){
                    datas.add(tagBean) ;
                }
            }
        }
        return  datas ;
    }


}
