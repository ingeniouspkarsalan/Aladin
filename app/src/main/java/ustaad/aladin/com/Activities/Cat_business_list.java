package ustaad.aladin.com.Activities;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
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
    private Dialog di;
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
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                show_best_form();
            }
        }, 5000);

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
        try {
            di.dismiss();
        }catch (Exception e){

        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        Animation.fade(Cat_business_list.this);
        finish();
        try {
            di.dismiss();
        }catch (Exception e){

        }
        return super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.cat_form, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clics on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.form) {
            show_best_form();
        }

        return super.onOptionsItemSelected(item);
    }


    private void show_best_form(){
        AlertDialog.Builder alert = new AlertDialog.Builder(Cat_business_list.this);

       final EditText edittext = new EditText(this);
       edittext.setHint("Enter Your Message......");
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                120);
        edittext.setLayoutParams(lp);
        alert.setMessage("Your specification requirement for suggestion best deals.");
        alert.setTitle("Fill this form and get best deals on "+cat_name);

        alert.setView(edittext);

        alert.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                String YouEditTextValue = edittext.getText().toString();
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // what ever you want to do with No option.
            }
        });

        di=alert.create();
        di.show();
    }




}
