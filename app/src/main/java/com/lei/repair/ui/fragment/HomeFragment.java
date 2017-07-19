package com.lei.repair.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lei.repair.R;
import com.lei.repair.base.BaseFragment;
import com.lei.repair.ui.activity.MerchantCenterActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by szhua on 2017/7/20/020.
 * github:https://github.com/szhua
 * CarRepair
 * HomeFragment
 */

public class HomeFragment extends BaseFragment {
    @InjectView(R.id.home)
    TextView home;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_layout, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick(R.id.home)
    public void onViewClicked() {
        transUi(MerchantCenterActivity.class,null);
    }
}
