package com.lei.repair.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.lei.repair.R;
import com.lei.repair.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


/*修理厂个人商家中心界面*/
public class MerchantCenterActivity extends BaseActivity {
    @InjectView(R.id.left_back)
    ImageView leftBack;
    @InjectView(R.id.title)
    TextView title;
    @InjectView(R.id.right_img)
    ImageView rightImg;
    @InjectView(R.id.factory_info)
    RelativeLayout factoryInfo;
    @InjectView(R.id.youhui_act)
    RelativeLayout youhuiAct;
    @InjectView(R.id.un_read_msg_num)
    TextView unReadMsgNum;
    @InjectView(R.id.msg_notice)
    RelativeLayout msgNotice;
    @InjectView(R.id.orders)
    RelativeLayout orders;
    @InjectView(R.id.my_orders)
    RelativeLayout myOrders;
    @InjectView(R.id.my_recharge)
    RelativeLayout myRecharge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_center);
        ButterKnife.inject(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        setRightImageClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transUi(MechantPersonalInfoEditActivity.class,null);
            }
        });
    }

    @OnClick({R.id.factory_info, R.id.youhui_act, R.id.un_read_msg_num, R.id.msg_notice, R.id.orders, R.id.my_orders, R.id.my_recharge})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.factory_info:
                transUi(MerchantInfoActivity.class,null);
                break;
            case R.id.youhui_act:
                transUi(MechantPersonalInfoEditActivity.class,null);
                break;
            case R.id.un_read_msg_num:
                break;
            case R.id.msg_notice:
                break;
            case R.id.orders:
                transUi(MerchantOrdersActivity.class,null);
                break;
            case R.id.my_orders:
                break;
            case R.id.my_recharge:
                break;
        }
    }
}
