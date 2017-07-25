package com.lei.repair.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lei.repair.R;
import com.lei.repair.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;

/*修理厂第三部 ,确认报价*/
public class MechantQuotationConfirmationActivity extends BaseActivity {
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
    @InjectView(R.id.bao_yang_matrials)
    RecyclerView baoYangMatrials;
    @InjectView(R.id.photos_list)
    RecyclerView photosList;
    @InjectView(R.id.textView)
    TextView textView;
    @InjectView(R.id.qulty_time_select_bt)
    TextView qultyTimeSelectBt;
    @InjectView(R.id.textView2)
    TextView textView2;
    @InjectView(R.id.commit)
    TextView commit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechant_quotation_confirmation);
        ButterKnife.inject(this);
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transUi(ConstructionScheduleActivity.class,null);
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        setTitle("报价确认");
    }
}
