package ustaad.aladin.com.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import ustaad.aladin.com.Adaptors.Adaptor_for_Home_Category;
import ustaad.aladin.com.R;
import ustaad.aladin.com.classes.layout_category_class;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private List<layout_category_class> layoutCategoryClassList;
    private Adaptor_for_Home_Category adaptor_for_home_category;
    private GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Aladin");
        setSupportActionBar(toolbar);

        //calling all widget from layout
        initing();



//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    public void initing(){
        layoutCategoryClassList=new ArrayList<>();
        adding_categories();
        adaptor_for_home_category=new Adaptor_for_Home_Category(this,layoutCategoryClassList);
        gridView=findViewById(R.id.gridview);
        gridView.setAdapter(adaptor_for_home_category);
    }

    private void adding_categories(){
        layoutCategoryClassList.add(new layout_category_class(this.getResources().getDrawable(R.drawable.c1),"Shopping"));
        layoutCategoryClassList.add(new layout_category_class(this.getResources().getDrawable(R.drawable.c2),"Furniture"));
        layoutCategoryClassList.add(new layout_category_class(this.getResources().getDrawable(R.drawable.c3),"Cloth"));
        layoutCategoryClassList.add(new layout_category_class(this.getResources().getDrawable(R.drawable.c4),"Real State"));
        layoutCategoryClassList.add(new layout_category_class(this.getResources().getDrawable(R.drawable.c5),"Computer"));
        layoutCategoryClassList.add(new layout_category_class(this.getResources().getDrawable(R.drawable.c6),"Hardware"));
        layoutCategoryClassList.add(new layout_category_class(this.getResources().getDrawable(R.drawable.c7),"Cargo"));
        layoutCategoryClassList.add(new layout_category_class(this.getResources().getDrawable(R.drawable.c8),"Car"));
        layoutCategoryClassList.add(new layout_category_class(this.getResources().getDrawable(R.drawable.c9),"Plug"));

        layoutCategoryClassList.add(new layout_category_class(this.getResources().getDrawable(R.drawable.c1),"Shopping"));
        layoutCategoryClassList.add(new layout_category_class(this.getResources().getDrawable(R.drawable.c2),"Furniture"));
        layoutCategoryClassList.add(new layout_category_class(this.getResources().getDrawable(R.drawable.c3),"Cloth"));
        layoutCategoryClassList.add(new layout_category_class(this.getResources().getDrawable(R.drawable.c4),"Real State"));
        layoutCategoryClassList.add(new layout_category_class(this.getResources().getDrawable(R.drawable.c5),"Computer"));
        layoutCategoryClassList.add(new layout_category_class(this.getResources().getDrawable(R.drawable.c6),"Hardware"));
        layoutCategoryClassList.add(new layout_category_class(this.getResources().getDrawable(R.drawable.c7),"Cargo"));
        layoutCategoryClassList.add(new layout_category_class(this.getResources().getDrawable(R.drawable.c8),"Car"));
        layoutCategoryClassList.add(new layout_category_class(this.getResources().getDrawable(R.drawable.c9),"Plug"));

        layoutCategoryClassList.add(new layout_category_class(this.getResources().getDrawable(R.drawable.c1),"Shopping"));
        layoutCategoryClassList.add(new layout_category_class(this.getResources().getDrawable(R.drawable.c2),"Furniture"));
        layoutCategoryClassList.add(new layout_category_class(this.getResources().getDrawable(R.drawable.c3),"Cloth"));
        layoutCategoryClassList.add(new layout_category_class(this.getResources().getDrawable(R.drawable.c4),"Real State"));
        layoutCategoryClassList.add(new layout_category_class(this.getResources().getDrawable(R.drawable.c5),"Computer"));
        layoutCategoryClassList.add(new layout_category_class(this.getResources().getDrawable(R.drawable.c6),"Hardware"));
        layoutCategoryClassList.add(new layout_category_class(this.getResources().getDrawable(R.drawable.c7),"Cargo"));
        layoutCategoryClassList.add(new layout_category_class(this.getResources().getDrawable(R.drawable.c8),"Car"));
        layoutCategoryClassList.add(new layout_category_class(this.getResources().getDrawable(R.drawable.c9),"Plug"));
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
}
