package ustaad.aladin.com.maps;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    public MarkerInfoWindowAdapter(Context context,Intent list) {
        this.context = context.getApplicationContext();
        this.bus_list_classes=list.getParcelableArrayListExtra("data_for_marker");
    }

    @Override
    public View getInfoWindow(Marker arg0) {
        return null;
    }

    @Override
    public View getInfoContents(Marker arg0) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewGroup infoWindow = (ViewGroup) inflater.inflate(R.layout.info_popup_marker, null);

        try {
    LatLng latLng = arg0.getPosition();
    TextView name = infoWindow.findViewById(R.id.bus_map_name);
    TextView contact = infoWindow.findViewById(R.id.bus_map_contact);
    TextView city = infoWindow.findViewById(R.id.bus_map_city);
    ImageView image = infoWindow.findViewById(R.id.bus_map_img);
    if (bus_list_classes.size() > 0) {

        String id = arg0.getId();
        id = id.replace("m","");
        int i=Integer.parseInt(id);
        name.setText(bus_list_classes.get(i).getB_name());
        name.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_short_text_black_24dp,0,0,0);
        contact.setText(bus_list_classes.get(i).getB_mobile());
        contact.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_contact_phone_black_24dp,0,0,0);
        city.setText(bus_list_classes.get(i).getB_city());
        city.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_location_city_black_24dp,0,0,0);
        if(bus_list_classes.get(i).getB_image().isEmpty()){
            image.setImageResource(R.drawable.banner);
        }else {
            Glide.with(context).load(bus_list_classes.get(i).getB_image()).into(image);
        }


    }
        }catch (Exception e){
            e.printStackTrace();
        }



        return infoWindow;
    }
}