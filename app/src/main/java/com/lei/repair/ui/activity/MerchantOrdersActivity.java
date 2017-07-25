package com.lei.repair.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.lei.repair.R;
import com.lei.repair.base.BaseActivity;
import com.lei.repair.base.BaseFragment;
import com.lei.repair.base.BaseFragmentPagerAdapter;
import com.lei.repair.ui.fragment.MerchantOderFragment;
import com.runer.liabary.tab.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/*维修厂订单中心界面*/
public class MerchantOrdersActivity extends BaseActivity {

    @InjectView(R.id.tab_layout)
    SlidingTabLayout tabLayout;
    @InjectView(R.id.view_pager)
    ViewPager viewPager;

    private OrderPagerAdapter orderPagerAdapter ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_orders);
        ButterKnife.inject(this);
        List<BaseFragment> fragments =new ArrayList<>() ;
        fragments.add(MerchantOderFragment.getInstance(MerchantOderFragment.TO_CATCH_ORDER)) ;
        fragments.add(MerchantOderFragment.getInstance(MerchantOderFragment.HAS_BAOJIA_ORDER));
        fragments.add(MerchantOderFragment.getInstance(MerchantOderFragment.GOT_ORDER)) ;
        orderPagerAdapter =new OrderPagerAdapter(getSupportFragmentManager(),fragments) ;
        viewPager.setAdapter(orderPagerAdapter);
        tabLayout.setViewPager(viewPager);
    }





    private class OrderPagerAdapter extends BaseFragmentPagerAdapter{

        private List<BaseFragment> baseFragmentList ;
        private String [] titles =new String[]{"待抢单","已报价","已接单"};


        public OrderPagerAdapter(FragmentManager fm,List<BaseFragment> baseFragments) {
            super(fm);
            this.baseFragmentList =baseFragments ;
        }

        @Override
        public Fragment getItem(int position) {
            return baseFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return 3;
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }


















    @Override
    protected void onStart() {
        super.onStart();
        setTitle("我的保险订单");
    }
}
