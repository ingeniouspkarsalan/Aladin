package ustaad.aladin.com.Adaptors;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ustaad.aladin.com.R;
import ustaad.aladin.com.classes.layout_category_class;

public class Adaptor_for_Home_Category extends BaseAdapter {
    private List<layout_category_class> layoutCategoryClassList;
    private Context context;
    public Adaptor_for_Home_Category(Context c,List<layout_category_class> layoutCategoryClassList){
        this.layoutCategoryClassList=layoutCategoryClassList;
        this.context=c;
    }

    @Override
    public int getCount() {
        return layoutCategoryClassList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        layout_category_class cc=layoutCategoryClassList.get(position);
        LayoutInflater inflater = LayoutInflater.from(context);
        View row;
        row = inflater.inflate(R.layout.layout_for_categories, parent, false);
        TextView tt=row.findViewById(R.id.cat_card_text);
        tt.setText(cc.getCategory_text());
        ImageView imageView=row.findViewById(R.id.cat_card_img);
        imageView.setBackground(cc.getImage_id());
        return row;
    }
}
