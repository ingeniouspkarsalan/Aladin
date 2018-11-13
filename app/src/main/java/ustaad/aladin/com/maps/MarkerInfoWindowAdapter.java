package ustaad.aladin.com.maps;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;

import ustaad.aladin.com.Activities.Item_page;
import ustaad.aladin.com.R;
import ustaad.aladin.com.classes.bus_list_class;

public class MarkerInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {
    private Context context;
    private ArrayList<bus_list_class> bus_list_classes;
    public MarkerInfoWindowAdapter(Context context,ArrayList<bus_list_class> bus_list_classes) {
        this.context = context.getApplicationContext();
        this.bus_list_classes=bus_list_classes;
    }

    @Override
    public View getInfoWindow(Marker arg0) {
        return null;
    }

    @Override
    public View getInfoContents(Marker arg0) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v =  inflater.inflate(R.layout.info_popup_marker, null);

        LatLng latLng = arg0.getPosition();
        TextView name =  v.findViewById(R.id.name);
        ImageView image= v.findViewById(R.id.image);
        if(bus_list_classes.size()>0){
            for(int i=0;i<bus_list_classes.size();i++){
                if(latLng.latitude==bus_list_classes.get(i).getB_lat() && latLng.longitude==bus_list_classes.get(i).getB_lat()){
                    name.setText(bus_list_classes.get(i).getB_name());
                    Glide.with(context).load(bus_list_classes.get(i).getB_image()).into(image);
                }
            }

        }




        return v;
    }
}