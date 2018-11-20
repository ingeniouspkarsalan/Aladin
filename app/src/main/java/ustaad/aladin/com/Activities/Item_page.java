package ustaad.aladin.com.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

import ustaad.aladin.com.R;
import ustaad.aladin.com.classes.Animation;

public class Item_page extends AppCompatActivity implements OnMapReadyCallback{
    String b_id ,
     b_name ,
     b_image ,
     b_mobile,
     b_city,
     b_address,
     b_detail,
     b_lat ,
     b_long ,
     b_email;
double lat,longt;

    private ImageView logo;
    private ImageButton call, email;
    private TextView city,address,detail;
    private GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_page);
        b_id = getIntent().getStringExtra("b_id");
        b_name = getIntent().getStringExtra("b_name");
        b_image = getIntent().getStringExtra("b_image");
        b_mobile = getIntent().getStringExtra("b_mobile");
        b_city = getIntent().getStringExtra("b_city");
        b_address = getIntent().getStringExtra("b_address");
        b_detail = getIntent().getStringExtra("b_detail");
        b_lat = getIntent().getStringExtra("b_lat");
        b_long = getIntent().getStringExtra("b_long");
        b_email = getIntent().getStringExtra("b_email");
        lat = getIntent().getDoubleExtra("lat",0);
        longt = getIntent().getDoubleExtra("long",0);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(b_name);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void init() {
        logo = findViewById(R.id.logo);
        call = findViewById(R.id.call);
        email = findViewById(R.id.email);

        if (b_image.isEmpty()) {
            logo.setImageResource(R.drawable.banner);
        } else {
            Glide.with(this).load(b_image).into(logo);
        }

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri u = Uri.parse("tel:" + b_mobile);
                Intent intent = new Intent(Intent.ACTION_DIAL,u);
                startActivity(intent);
                }
            });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto",b_email, null));
                startActivity(emailIntent);
            }
        });

        city=findViewById(R.id.bus_card_city);
        address=findViewById(R.id.bus_card_address);
        detail=findViewById(R.id.bus_card_detail);

        city.setText(b_city);
        address.setText(b_address);
        detail.setText(b_detail);
        }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animation.fade(Item_page.this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        Animation.fade(Item_page.this);
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng place = new LatLng(lat, longt);
        mMap.addMarker(new MarkerOptions().position(place).title(b_name));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(place));
    }
}
