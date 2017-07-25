package com.lei.repair.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.lei.repair.R;
import com.lei.repair.adapter.MetrialsFeeAdapter;
import com.lei.repair.base.BaseActivity;
import com.lei.repair.util.AppUtil;
import com.lei.repair.widget.MultiSelectTagsView;
import com.lei.repair.widget.SelectPhotoView;
import com.lei.repair.widget.TagsView;
import com.runer.liabary.util.RunerLinearManager;
import com.runer.liabary.util.UiUtil;
import com.soundcloud.android.crop.Crop;
import com.yanzhenjie.album.Album;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import top.zibin.luban.Luban;

/*维修厂报价   维修厂的第二步*/
public class MerchantQuotation extends BaseActivity {
    @InjectView(R.id.left_back)
    ImageView leftBack;
    @InjectView(R.id.title)
    TextView title;
    @InjectView(R.id.right_text)
    TextView rightText;
    @InjectView(R.id.right_img)
    ImageView rightImg;
    @InjectView(R.id.matrial_fee)
    RecyclerView matrialFee;
    @InjectView(R.id.add_matrial)
    TextView addMatrial;
    @InjectView(R.id.bao_yang_matrials)
    RecyclerView baoYangMatrials;
    @InjectView(R.id.select_photos_view)
    SelectPhotoView selectPhotosView;
    @InjectView(R.id.travel_tags)
    TagsView travelTags;
    @InjectView(R.id.services_tags)
    MultiSelectTagsView servicesTags;
    @InjectView(R.id.goods_tags)
    TagsView goodsTags;
    @InjectView(R.id.add_baoyang_bt)
    TextView addBaoyangBt;
    @InjectView(R.id.qulty_time_select_bt)
    TextView qultyTimeSelectBt;
    @InjectView(R.id.submit)
    TextView submit;
    private MetrialsFeeAdapter metrialsFeeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_quotation);
        ButterKnife.inject(this);

        metrialsFeeAdapter = new MetrialsFeeAdapter(AppUtil.getTestData(3));
        matrialFee.setLayoutManager(new RunerLinearManager(this));
        matrialFee.setAdapter(metrialsFeeAdapter);


        selectPhotosView.setOnItemSelectPicClickListener(new SelectPhotoView.OnItemSelectPicClickListener() {
            @Override
            public void onAddPic(int leftNum) {
                Album.album(MerchantQuotation.this)
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

        travelTags.setTagBeanList(AppUtil.getTagTestDatas(6));

        servicesTags.setTagBeanList(AppUtil.getTagTestDatas(8));

        goodsTags.setTagBeanList(AppUtil.getTagTestDatas(8));


    }

    public static final int PHOTO_SELECT_CODE = 999;
    //裁剪头像的缓存地址
    public static final String CROPO_CACHE_PAHT = "imgine_cloud_crop";

    @OnClick({R.id.add_matrial, R.id.add_baoyang_bt, R.id.qulty_time_select_bt,R.id.submit})
    public void onViewClicked(View view) {
        Bundle bundle = null;
        switch (view.getId()) {
            case R.id.add_matrial:
                bundle = new Bundle();
                bundle.putString("type", "维修配件/材料费");
                transUi(AddChargeActivity.class, bundle);

                break;
            case R.id.add_baoyang_bt:
                bundle = new Bundle();
                bundle.putString("type", "保养配件/材料费");
                transUi(AddChargeActivity.class, bundle);
                break;
            //显示选择时间
            case R.id.qulty_time_select_bt:
                final String[] types = new String[]{"1月", "3月", "6月", "12月"};
                OptionsPickerView options = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        qultyTimeSelectBt.setText(types[options1]);
                    }
                })
                        .setTitleText("选择质保时间")
                        .build();
                options.setPicker(Arrays.asList(types));
                options.show();
                break;
            case R.id.submit:
                transUi(MechantQuotationConfirmationActivity.class,null);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHOTO_SELECT_CODE) {

            if (resultCode == RESULT_OK) {
                ArrayList<String> pathList = Album.parseResult(data);
                selectPhotosView.addImgPath(pathList);
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
                            return Luban.with(MerchantQuotation.this).load(file).get();
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
    protected void onStart() {
        super.onStart();
        setTitle("我的维修报价");
    }
}
