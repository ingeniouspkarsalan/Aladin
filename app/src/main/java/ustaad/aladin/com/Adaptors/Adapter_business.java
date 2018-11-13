package ustaad.aladin.com.Adaptors;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import ustaad.aladin.com.R;
import ustaad.aladin.com.classes.bus_list_class;

public class Adapter_business extends RecyclerView.Adapter<Adapter_business.businessViewHolder> {
    private Context context;
    private ArrayList<bus_list_class> bus_list_classList;


    class businessViewHolder extends RecyclerView.ViewHolder {
    ImageView bus_card_image;
    TextView bus_card_name,bus_card_contact,bus_card_city;

        public businessViewHolder(View itemView) {
            super(itemView);
        bus_card_image=itemView.findViewById(R.id.bus_card_img);
        bus_card_name=itemView.findViewById(R.id.bus_card_name);
        bus_card_contact=itemView.findViewById(R.id.bus_card_contact);
        bus_card_city=itemView.findViewById(R.id.bus_card_city);
        }
    }

    public Adapter_business(Context context, ArrayList<bus_list_class> bus_list_classList) {
        this.context = context;
        this.bus_list_classList = bus_list_classList;
    }

    @Override
    public void onBindViewHolder(businessViewHolder holder, int position) {
    final bus_list_class bus_list_class=bus_list_classList.get(position);
    Glide.with(context).load(bus_list_class.getB_image()).into(holder.bus_card_image);
    holder.bus_card_name.setText(" "+bus_list_class.getB_name());
    holder.bus_card_contact.setText(" "+bus_list_class.getB_mobile());
    holder.bus_card_city.setText(" "+bus_list_class.getB_city());
    }

    @Override
    public businessViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_bus_list, null);
        return new businessViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return bus_list_classList.size();
    }

}


