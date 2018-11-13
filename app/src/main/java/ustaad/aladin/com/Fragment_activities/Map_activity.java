package ustaad.aladin.com.Fragment_activities;

import android.content.Intent;
import android.support.annotation.NonNull;
        import android.support.annotation.Nullable;
        import android.support.v4.app.Fragment;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
import android.widget.Toast;

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
                                mMap.addMarker(new MarkerOptions().position(new LatLng(bus_list_classes.get(i).getB_lat(),bus_list_classes.get(i).getB_long())).title("pop up"));
                            }
                        }
                    }
//                locations.add(new LatLng(24.821730,67.024680));
//                locations.add(new LatLng(24.823327,67.028414));
//                locations.add(new LatLng(24.823288,67.031568));
//                locations.add(new LatLng(24.824677,67.033982));
//                locations.add(new LatLng(24.823093,67.035559));
//                locations.add(new LatLng(24.822489,67.036632));



                    //LatLngBound will cover all your marker on Google Maps
                    LatLngBounds.Builder builder = new LatLngBounds.Builder();
                    builder.include(locations.get(0)); //Taking Point A (First LatLng)
                    builder.include(locations.get(locations.size() - 1)); //Taking Point B (Second LatLng)
                    LatLngBounds bounds = builder.build();
                    CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, 200);
                    mMap.moveCamera(cu);
                    mMap.animateCamera(CameraUpdateFactory.zoomTo(14), 2000, null);

                    // Setting a custom info window adapter for the google map
                    MarkerInfoWindowAdapter markerInfoWindowAdapter = new MarkerInfoWindowAdapter(getContext(),bus_list_classes);
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
                            startActivity(new Intent(getContext(), Item_page.class));
                        }
                    });

                }catch (Exception e){}


            }
        });
    }
}
