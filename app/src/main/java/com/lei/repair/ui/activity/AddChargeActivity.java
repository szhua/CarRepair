package com.lei.repair.ui.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.lib.WheelView;
import com.lei.repair.R;
import com.lei.repair.base.BaseActivity;

import java.util.Arrays;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class AddChargeActivity extends BaseActivity {

    @InjectView(R.id.left_back)
    ImageView leftBack;
    @InjectView(R.id.title)
    TextView title;
    @InjectView(R.id.right_text)
    TextView rightText;
    @InjectView(R.id.right_img)
    ImageView rightImg;
    @InjectView(R.id.type)
    TextView type;
    @InjectView(R.id.types_select)
    TextView typesSelect;
    @InjectView(R.id.submit_bt)
    TextView submitBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_charge);
        ButterKnife.inject(this);
        type.setText(getStringExtras("type", ""));
    }

    @Override
    protected void onStart() {
        super.onStart();
        setTitle(getStringExtras("type", ""));
    }

    @OnClick({R.id.types_select, R.id.submit_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.types_select:
                showSelect();
                break;
            case R.id.submit_bt:
                break;
        }
    }


    public void showSelect(){
        final String [] types =new String[]{"原厂","非原厂"};
        OptionsPickerView options =new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                       typesSelect.setText(types[options1]);
            }
        })
                .setTitleText("选择材料产地")
                .build() ;
       options.setPicker(Arrays.asList(types));

        options.show();
    }
}
