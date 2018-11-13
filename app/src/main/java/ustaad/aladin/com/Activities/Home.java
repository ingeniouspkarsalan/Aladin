package ustaad.aladin.com.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.GridView;
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
import ustaad.aladin.com.Adaptors.Adaptor_for_Home_Category;
import ustaad.aladin.com.R;
import ustaad.aladin.com.Utils.Endpoints;
import ustaad.aladin.com.Utils.Utils;
import ustaad.aladin.com.classes.JSONParser;
import ustaad.aladin.com.classes.layout_category_class;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private List<layout_category_class> layoutCategoryClassList;
    private List<layout_category_class> searchCategoryClassList;
    private Adaptor_for_Home_Category adaptor_for_home_category;
    private RecyclerView recyclerView;
    private EditText et_for_search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Aladin");
        setSupportActionBar(toolbar);

        //calling all widget from layout
        initing();
        //init search
        Search_int();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    public void initing(){
        recyclerView=findViewById(R.id.recylcerView);
        if (Utils.isOnline(Home.this)) {
            try {
                requestData();
            } catch (Exception ex) {
                new SweetAlertDialog(Home.this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Oops...")
                        .setContentText("Some thing went wrong!")
                        .show();
            }
        } else {
            new SweetAlertDialog(Home.this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Oops...")
                    .setContentText("Internet Not Found!")
                    .show();
        }

    }


    //fetching all categories of subcategory
    public void requestData() {
        final String id = Prefs.getString("user_id", "0");
        StringRequest request = new StringRequest(Request.Method.POST, Endpoints.ip_server, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.contains("null")) {
                    Toasty.warning(Home.this, "Categories not available.", Toast.LENGTH_LONG).show();
                } else {
                    layoutCategoryClassList = JSONParser.parse_category(response);
                    adaptor_for_home_category=new Adaptor_for_Home_Category(Home.this,layoutCategoryClassList);
                    recyclerView.setAdapter(adaptor_for_home_category);
                    recyclerView.setLayoutManager(new GridLayoutManager(Home.this,3));

                }


            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        new SweetAlertDialog(Home.this, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("Oops...")
                                .setContentText("Some thing went wrong!")
                                .show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("req_key", "all_category");
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clics on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void Search_int(){

        et_for_search =  findViewById(R.id.et_for_search);
        et_for_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                search(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void search(String word)
    {
        searchCategoryClassList = new ArrayList<>();
        if(searchCategoryClassList != null)
            for (layout_category_class list : layoutCategoryClassList)
            {
                if(list != null)
                    if(list.getCategory_name().toLowerCase().contains(word))
                    {
                        searchCategoryClassList.add(list);
                    }
            }
        adaptor_for_home_category.searchedList(searchCategoryClassList);
    }
}
