//package com.lei.repair.widget;
//
//import android.content.Context;
//import android.support.annotation.Nullable;
//import android.support.v7.widget.GridLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.text.TextUtils;
//import android.util.AttributeSet;
//import android.view.LayoutInflater;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//
//import com.chad.library.adapter.base.BaseQuickAdapter;
//import com.chad.library.adapter.base.BaseViewHolder;
//import com.runer.liabary.util.UiUtil;
//import com.runer.toumai.R;
//import com.squareup.picasso.Picasso;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by szhua on 2017/7/19/019.
// * github:https://github.com/szhua
// * TouMaiNetApp
// * SelectPhotoView
// * 选择图片;
// */
//
//public class SelectPhotoView extends LinearLayout {
//
//
//    private List<String> currentPathList ;
//    private SelectPhotoAdapter selectPhotoAdapter ;
//    public SelectPhotoView(Context context) {
//        this(context,null,0);
//    }
//    public SelectPhotoView(Context context, @Nullable AttributeSet attrs) {
//        this(context, attrs,0);
//    }
//    public SelectPhotoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        LayoutInflater.from(context).inflate(R.layout.select_photo_view,this) ;
//        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
//        selectPhotoAdapter =new SelectPhotoAdapter(currentPathList);
//        GridLayoutManager gridLayoutManager =new GridLayoutManager(getContext(),6);
//        recyclerView.setLayoutManager(gridLayoutManager);
//        recyclerView.setAdapter(selectPhotoAdapter);
//    }
//
//    public static  final int MAX_NUM = 6 ;
//
//    public void addImgPath(List<String> imgPath){
//           if(currentPathList==null){
//               currentPathList =new ArrayList<>() ;
//           }
//           int currentSize =currentPathList.size();
//           int leftSize =MAX_NUM-currentSize;
//
//          if(imgPath==null||imgPath.isEmpty()){
//            UiUtil.showLongToast(getContext(),"请传入路径");
//            return;
//           }
//
//           if(imgPath!=null||!imgPath.isEmpty()){
//               if(leftSize<imgPath.size()){
//                   UiUtil.showLongToast(getContext(),"最多传入"+MAX_NUM+"张图片");
//                   return;
//               }
//               currentPathList.addAll(imgPath) ;
//               selectPhotoAdapter.notifyDataSetChanged();
//           }
//    }
//
//
//
//    private class SelectPhotoAdapter extends BaseQuickAdapter<String,BaseViewHolder>{
//        public SelectPhotoAdapter(@Nullable List<String> data) {
//            super(R.layout.item_select_pic,data);
//        }
//        @Override
//        protected void convert(BaseViewHolder helper, String item) {
//            if(!TextUtils.isEmpty(item))
//            Picasso.with(mContext).load(item).into((ImageView) helper.getView(R.id.header));
//
//        }
//    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//}
