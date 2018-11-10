package ustaad.aladin.com.maps;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import ustaad.aladin.com.Activities.Item_page;
import ustaad.aladin.com.R;

public class MarkerInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {
    private Context context;
    public MarkerInfoWindowAdapter(Context context) {
        this.context = context.getApplicationContext();
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
        if(latLng.latitude==24.821730 && latLng.longitude==67.024680){
            name.setText("shopping");
            image.setImageDrawable(context.getResources().getDrawable(R.drawable.c1));
        }else if(latLng.latitude==24.823327 && latLng.longitude==67.028414){
            name.setText("furniture");
            image.setImageDrawable(context.getResources().getDrawable(R.drawable.c2));
        }else if(latLng.latitude==24.823288 && latLng.longitude==67.031568){
            name.setText("cloth");
            image.setImageDrawable(context.getResources().getDrawable(R.drawable.c3));
        }else if(latLng.latitude==24.824677 && latLng.longitude==67.033982){
            name.setText("Real State");
            image.setImageDrawable(context.getResources().getDrawable(R.drawable.c4));
        }else if(latLng.latitude==24.823093 && latLng.longitude==67.035559){
            name.setText("Computer");
            image.setImageDrawable(context.getResources().getDrawable(R.drawable.c5));
        }else if(latLng.latitude==24.822489 && latLng.longitude==67.036632){
            name.setText("Hardware");
            image.setImageDrawable(context.getResources().getDrawable(R.drawable.c6));
        }



        return v;
    }
}