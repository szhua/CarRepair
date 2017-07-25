package com.lei.repair.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lei.repair.R;
import com.lei.repair.base.BaseActivity;
import com.lei.repair.bean.TagBean;
import com.lei.repair.widget.SelectPhotoView;
import com.lei.repair.widget.TagsView;
import com.lei.repair.widget.area.AreaViewShowUtil;
import com.orhanobut.logger.Logger;
import com.runer.liabary.flowlayout.TagFlowLayout;
import com.runer.liabary.util.UiUtil;
import com.soundcloud.android.crop.Crop;
import com.squareup.picasso.Picasso;
import com.yanzhenjie.album.Album;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import top.zibin.luban.Luban;

/*修理厂个人资料管理*/
public class MechantPersonalInfoEditActivity extends BaseActivity implements View.OnClickListener,AreaViewShowUtil.OnAreaSelectListener {
    @InjectView(R.id.left_back)
    ImageView leftBack;
    @InjectView(R.id.title)
    TextView title;
    @InjectView(R.id.right_text)
    TextView rightText;
    @InjectView(R.id.right_img)
    ImageView rightImg;
    @InjectView(R.id.select_area)
    TextView selectArea;
    @InjectView(R.id.service_types)
    TagsView serviceTypes;
    @InjectView(R.id.mechant_types)
    TagsView mechantTypes;
    @InjectView(R.id.selet_photos_view)
    SelectPhotoView seletPhotosView;
    private AreaViewShowUtil  areaViewShowUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechant_personal_info_edit);
        ButterKnife.inject(this);

        selectArea.setOnClickListener(this);

        //添加照片
        seletPhotosView.setOnItemSelectPicClickListener(new SelectPhotoView.OnItemSelectPicClickListener() {
            @Override
            public void onAddPic(int leftNum) {
                Album.album(MechantPersonalInfoEditActivity.this)
                        .toolBarColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary)) // Toolbar 颜色，默认蓝色。
                        .statusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary)) // StatusBar 颜色，默认蓝色。
                        .title("图库")
                        .selectCount(leftNum)
                        .columnCount(3)
                        .camera(true)
                        .requestCode(PHOTO_SELECT_CODE)
                        .start();
            }
            @Override
            public void onPictureClick(String path) {

            }
        });

        //设置类型
        List<TagBean >typesTag  =new ArrayList<>() ;
        for (int i = 0; i <6 ; i++) {
            TagBean bean =new TagBean() ;
            bean.setTagName("item"+i);
            typesTag.add(bean);
        }
        mechantTypes.setTagBeanList(typesTag);

        //设置类型
        List<TagBean > servicesType  =new ArrayList<>() ;
        for (int i = 0; i <6 ; i++) {
            TagBean bean =new TagBean() ;
            bean.setTagName("item"+i);
            servicesType.add(bean);
        }
        serviceTypes.setTagBeanList(servicesType);
    }

    public static final int PHOTO_SELECT_CODE = 999;
    //裁剪头像的缓存地址
    public static final String CROPO_CACHE_PAHT = "imgine_cloud_crop";


    @Override
    protected void onStart() {
        super.onStart();
        setTitle("修改资料");
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PHOTO_SELECT_CODE) {

            if (resultCode == RESULT_OK) {
                ArrayList<String> pathList = Album.parseResult(data);
                seletPhotosView.addImgPath(pathList);
                //裁剪
                //Crop.of(Uri.fromFile(new File(pathList.get(0))), Uri.fromFile(new File(getCacheDir(), CROPO_CACHE_PAHT))).start(CompleteUserInfoActivity.this);
            }

            //裁剪以后的操作
        } else if (requestCode == Crop.REQUEST_CROP && resultCode == RESULT_OK) {
            //进行压缩;
            Flowable.just(new File(getCacheDir(), CROPO_CACHE_PAHT))
                    .observeOn(Schedulers.io())
                    .map(new Function<File, File>() {
                        @Override
                        public File apply(@NonNull File file) throws Exception {
                            return Luban.with(MechantPersonalInfoEditActivity.this).load(file).get();
                        }
                    })
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<File>() {
                        @Override
                        public void accept(File file) throws Exception {
//                            uploadHeaderDao.upLoadUserHeader(AppUtil.getUserId(getApplicationContext()),file);
//                            showProgressWithMsg(true,"正在上传头像");
//                            Picasso.with(CompleteUserInfoActivity.this).load(file).into(headerBt);
                        }
                    });
        } else if (resultCode == Crop.RESULT_ERROR) {
            UiUtil.showLongToast(getApplicationContext(), "裁剪失败!");
        }
    }


    @Override
    public void onClick(View v) {
        if(v==selectArea){
            //选择地区
            areaViewShowUtil = new AreaViewShowUtil(this);
            areaViewShowUtil.setOnAreaSelectListener(this);
            areaViewShowUtil.show();
        }
    }

    @Override
    public void onAreaSelect(String area) {
      selectArea.setText(area);
    }
}
