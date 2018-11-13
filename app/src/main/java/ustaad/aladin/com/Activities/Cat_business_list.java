package ustaad.aladin.com.Activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;
import es.dmoral.toasty.Toasty;
import ustaad.aladin.com.Fragment_activities.Business_lists;
import ustaad.aladin.com.Fragment_activities.Map_activity;
import ustaad.aladin.com.Layout_Fragment_Adapter.fragment_adapter;
import ustaad.aladin.com.R;
import ustaad.aladin.com.Utils.Endpoints;
import ustaad.aladin.com.Utils.Utils;
import ustaad.aladin.com.classes.Animation;
import ustaad.aladin.com.classes.JSONParser;
import ustaad.aladin.com.classes.bus_list_class;

public class Cat_business_list extends AppCompatActivity {
    private TabLayout tabLayout;
    private int[] tabIcons = {
           R.drawable.ic_view_list_black_24dp,
            R.drawable.ic_map_black_24dp
    };
    private String cat_id,cat_name;
    private ViewPager viewPager;
    private ArrayList<bus_list_class> bus_list_classList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_business_list);
        cat_id=getIntent().getStringExtra("cat_id");
        cat_name=getIntent().getStringExtra("cat_name");
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(cat_name);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Init();
    }

    private void Init() {
        tabLayout=(TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        if (Utils.isOnline(Cat_business_list.this)) {
            try {
                requestData();
            } catch (Exception ex) {
                new SweetAlertDialog(Cat_business_list.this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Oops...")
                        .setContentText("Some thing went wrong!")
                        .show();
            }
        } else {
            new SweetAlertDialog(Cat_business_list.this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Oops...")
                    .setContentText("Internet Not Found!")
                    .show();
        }
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
    }

    private void setupViewPager(ViewPager viewPager) {
        fragment_adapter adapter = new fragment_adapter(getSupportFragmentManager());
        adapter.addFragment(new Business_lists(),"List",bus_list_classList);
        adapter.addFragment(new Map_activity(),"Map",bus_list_classList);
        viewPager.setAdapter(adapter);
    }


    //fetching all categories of subcategory
    public void requestData() {
        final String id = Prefs.getString("user_id", "0");
        StringRequest request = new StringRequest(Request.Method.POST, Endpoints.ip_server, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.contains("null")) {
                    Toasty.warning(Cat_business_list.this, "Business are not available.", Toast.LENGTH_LONG).show();
                } else {
                    bus_list_classList = JSONParser.parse_bus_list(response);
                    if (viewPager != null) {
                        setupViewPager(viewPager);
                    }
                    tabLayout.setupWithViewPager(viewPager);
                    setupTabIcons();
                }


            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        new SweetAlertDialog(Cat_business_list.this, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("Oops...")
                                .setContentText("Some thing went wrong!")
                                .show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("req_key", "category_business_list");
                params.put("cat_id", cat_id);
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
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
