package ustaad.aladin.com.Activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import ustaad.aladin.com.Fragment_activities.Business_lists;
import ustaad.aladin.com.Fragment_activities.Map_activity;
import ustaad.aladin.com.Layout_Fragment_Adapter.fragment_adapter;
import ustaad.aladin.com.R;
import ustaad.aladin.com.classes.Animation;

public class Cat_business_list extends AppCompatActivity {
    private TabLayout tabLayout;
    private int[] tabIcons = {
           R.drawable.ic_view_list_black_24dp,
            R.drawable.ic_map_black_24dp
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_business_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getIntent().getStringExtra("cat_name"));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Init();
    }

    private void Init() {
        tabLayout=(TabLayout) findViewById(R.id.tabLayout);
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        if (viewPager != null) {
            setupViewPager(viewPager);
        }
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
    }

    private void setupViewPager(ViewPager viewPager) {
        fragment_adapter adapter = new fragment_adapter(getSupportFragmentManager());
        adapter.addFragment(new Business_lists(),"List");
        adapter.addFragment(new Map_activity(),"Map");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animation.fade(Cat_business_list.this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        Animation.fade(Cat_business_list.this);
        finish();
        return super.onSupportNavigateUp();
    }
}
