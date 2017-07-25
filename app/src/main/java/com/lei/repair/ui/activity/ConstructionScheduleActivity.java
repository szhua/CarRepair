package com.lei.repair.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lei.repair.R;
import com.lei.repair.base.BaseActivity;

/*
  修理厂的第四部
  施工进度Activity*/
public class ConstructionScheduleActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_construction_schedule);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setTitle("施工进度");
    }
}
