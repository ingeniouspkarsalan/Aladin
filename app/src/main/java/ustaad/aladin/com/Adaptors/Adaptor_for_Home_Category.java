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
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Calendar;
import java.util.List;

import ustaad.aladin.com.Activities.Cat_business_list;
import ustaad.aladin.com.R;
import ustaad.aladin.com.classes.layout_category_class;
import ustaad.aladin.com.maps.MapsActivity;

public class Adaptor_for_Home_Category extends RecyclerView.Adapter<Adaptor_for_Home_Category.CatViewHolder>
{
    private Context context;
    private List<layout_category_class> layoutCategoryClassList;


    class CatViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView card_image;
        TextView card_text;

        public CatViewHolder(View itemView)
        {
            super(itemView);
            cardView=itemView.findViewById(R.id.card_view);
            card_image=itemView.findViewById(R.id.cat_card_img);
            card_text=itemView.findViewById(R.id.cat_card_text);
        }
    }

    public Adaptor_for_Home_Category(Context context, List<layout_category_class> layout_category_classList) {
        this.context = context;
        this.layoutCategoryClassList = layout_category_classList;
    }

    @Override
    public void onBindViewHolder(CatViewHolder holder , int position)
    {
        final layout_category_class layout_category_class = layoutCategoryClassList.get(position);
        Glide.with(context).load(layout_category_class.getCategory_image()).into(holder.card_image);
        holder.card_image.setColorFilter(ContextCompat.getColor(context, R.color.hint), android.graphics.PorterDuff.Mode.MULTIPLY);
        holder.card_text.setText(layout_category_class.getCategory_name());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(context,Cat_business_list.class);
                in.putExtra("cat_id",layout_category_class.getCategory_id());
                in.putExtra("cat_name",layout_category_class.getCategory_name());
                context.startActivity(in);
            }
        });


    }

    @Override
    public CatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_for_categories, null);
        return new CatViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return layoutCategoryClassList.size();
    }

    public void searchedList(List<layout_category_class> _list)
    {
        layoutCategoryClassList = _list;
        notifyDataSetChanged();
    }


}