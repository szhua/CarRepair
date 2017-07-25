package com.lei.repair.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lei.repair.R;
import com.lei.repair.adapter.FlowImagaAdapter;
import com.lei.repair.base.BaseActivity;
import com.lei.repair.widget.RightArrowView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/*维修厂第一步 查看维修需求*/
public class MerchantCheckMaintenanceDemand extends BaseActivity {
    @InjectView(R.id.left_back)
    ImageView leftBack;
    @InjectView(R.id.title)
    TextView title;
    @InjectView(R.id.right_text)
    TextView rightText;
    @InjectView(R.id.right_img)
    ImageView rightImg;
    @InjectView(R.id.driving_license_photos)
    RecyclerView drivingLicensePhotos;
    @InjectView(R.id.car_photos)
    RecyclerView carPhotos;
    @InjectView(R.id.loss_detail_photos)
    RecyclerView lossDetailPhotos;
    @InjectView(R.id.losssingle_photos)
    RecyclerView losssinglePhotos;
    @InjectView(R.id.tijiao_baojia_bt)
    RightArrowView tijiaoBaojiaBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_check_maintenance_demand);
        ButterKnife.inject(this);
        List<String> imgs = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            imgs.add("item" + i);
        }
        drivingLicensePhotos.setLayoutManager(new GridLayoutManager(this, 5));
        drivingLicensePhotos.setAdapter(new FlowImagaAdapter(imgs));

        carPhotos.setLayoutManager(new GridLayoutManager(this, 5));
        carPhotos.setAdapter(new FlowImagaAdapter(imgs));

        lossDetailPhotos.setLayoutManager(new GridLayoutManager(this, 5));
        lossDetailPhotos.setAdapter(new FlowImagaAdapter(imgs));

        losssinglePhotos.setLayoutManager(new GridLayoutManager(this, 5));
        losssinglePhotos.setAdapter(new FlowImagaAdapter(imgs));

        tijiaoBaojiaBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transUi(MerchantQuotation.class,null);
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        setTitle("查看维修需求");
    }
}
