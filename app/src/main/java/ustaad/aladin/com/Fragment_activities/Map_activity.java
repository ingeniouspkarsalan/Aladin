package ustaad.aladin.com.Fragment_activities;

import android.content.Intent;
import android.support.annotation.NonNull;
        import android.support.annotation.Nullable;
        import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import ustaad.aladin.com.Activities.Item_page;
import ustaad.aladin.com.R;
import ustaad.aladin.com.classes.bus_list_class;
import ustaad.aladin.com.maps.MapsActivity;
import ustaad.aladin.com.maps.MarkerInfoWindowAdapter;

public class Map_activity extends Fragment implements OnMapReadyCallback {
    private ArrayList<bus_list_class> bus_list_classes;
    private GoogleMap mMap;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rv =  inflater.inflate(
                R.layout.activity_map_activity, container, false);
        bus_list_classes=getArguments().getParcelableArrayList("data_list");
        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        return rv;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        // When Map Loads Successfully
        mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                try{

                    //Your code where exception occurs goes here...
                    List<LatLng> locations = new ArrayList<>();
                    if(bus_list_classes.size()>0){
                        for(int i=0;i<bus_list_classes.size();i++){
                            if(bus_list_classes.get(i).getB_lat() !=0 && bus_list_classes.get(i).getB_long() !=0){
                                locations.add(new LatLng(bus_list_classes.get(i).getB_lat(),bus_list_classes.get(i).getB_long()));
                                mMap.addMarker(new MarkerOptions().position(new LatLng(bus_list_classes.get(i).getB_lat(),bus_list_classes.get(i).getB_long())).title(bus_list_classes.get(i).getB_name()));
                            }
                        }
                    }
//


                    //LatLngBound will cover all your marker on Google Maps
                    LatLngBounds.Builder builder = new LatLngBounds.Builder();
                    builder.include(locations.get(0)); //Taking Point A (First LatLng)
                    builder.include(locations.get(locations.size() - 1)); //Taking Point B (Second LatLng)
                    LatLngBounds bounds = builder.build();
                    CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, 200);
                    mMap.moveCamera(cu);
                    mMap.animateCamera(CameraUpdateFactory.zoomTo(14), 2000, null);

                    // Setting a custom info window adapter for the google map
                    final Intent In_list=new Intent();
                    In_list.putParcelableArrayListExtra("data_for_marker",bus_list_classes);
                    MarkerInfoWindowAdapter markerInfoWindowAdapter = new MarkerInfoWindowAdapter(getContext(),In_list);
                    mMap.setInfoWindowAdapter(markerInfoWindowAdapter);


                    mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                        @Override
                        public boolean onMarkerClick(Marker marker) {
                            marker.showInfoWindow();
                            return true;
                        }
                    });

                    mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                        @Override
                        public void onInfoWindowClick(Marker marker) {
                            String id = marker.getId();
                            id = id.replace("m","");
                            int i=Integer.parseInt(id);

                            Intent send=new Intent(getContext(), Item_page.class);
                            send.putExtra("b_id",bus_list_classes.get(i).getB_id());
                            send.putExtra("b_name",bus_list_classes.get(i).getB_name());
                            send.putExtra("b_image",bus_list_classes.get(i).getB_image());
                            send.putExtra("b_mobile",bus_list_classes.get(i).getB_mobile());
                            send.putExtra("b_city",bus_list_classes.get(i).getB_city());
                            send.putExtra("b_address",bus_list_classes.get(i).getB_address());
                            send.putExtra("b_detail",bus_list_classes.get(i).getB_detail());
                            send.putExtra("b_lat",bus_list_classes.get(i).getB_lat());
                            send.putExtra("b_long",bus_list_classes.get(i).getB_long());
                            send.putExtra("b_email",bus_list_classes.get(i).getB_email());
                            send.putExtra("lat",bus_list_classes.get(i).getB_lat());
                            send.putExtra("long",bus_list_classes.get(i).getB_long());
                            getContext().startActivity(send);
                        }
                    });

                }catch (Exception e){}


            }
        });
    }


    //yet not used
    private void show_pop_up(int i){
        LayoutInflater factory = LayoutInflater.from(getContext());
        final View DialogView = factory.inflate(R.layout.info_popup_marker, null);
        final AlertDialog pop_up = new AlertDialog.Builder(getContext()).create();
        pop_up.setView(DialogView);
        TextView name = DialogView.findViewById(R.id.bus_map_name);
        TextView contact = DialogView.findViewById(R.id.bus_map_contact);
        TextView city = DialogView.findViewById(R.id.bus_map_city);
        ImageView image = DialogView.findViewById(R.id.bus_map_img);
        try {
        if (bus_list_classes.size() > 0) {
            name.setText("hi hello");  //bus_list_classes.get(i).getB_name()
            contact.setText(bus_list_classes.get(i).getB_mobile());
            city.setText(bus_list_classes.get(i).getB_city());
            if(bus_list_classes.get(i).getB_image().isEmpty()){
                image.setImageResource(R.drawable.banner);
            }else {
                Glide.with(getContext()).load(bus_list_classes.get(i).getB_image()).into(image);
            }
        }
    }catch (Exception e){
        e.printStackTrace();
    }
        pop_up.show();
    }


}
