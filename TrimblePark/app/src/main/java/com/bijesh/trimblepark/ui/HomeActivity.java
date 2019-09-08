package com.bijesh.trimblepark.ui;

import android.os.Bundle;


import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.bijesh.trimblepark.R;
import com.bijesh.trimblepark.adapters.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class HomeActivity extends BaseActivity {


    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        viewPager = findViewById(R.id.viewpager);
        addTabs(viewPager);

        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void addTabs(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new ViewMyVehicle(), "View My Vehicle");
//        adapter.addFrag(new BookingFragment(), "Book my slot");
        adapter.addFrag(new CheckoutFragment(), "Check out");
        adapter.addFrag(new ViewAll(), "View All");
        viewPager.setAdapter(adapter);
    }
}
