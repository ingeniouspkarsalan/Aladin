package ustaad.aladin.com.Fragment_activities;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ustaad.aladin.com.Adaptors.Adapter_business;
import ustaad.aladin.com.R;
import ustaad.aladin.com.classes.bus_list_class;

public class Business_lists extends Fragment {
    private ArrayList<bus_list_class> bus_list_classes;
    private RecyclerView recyclerView;
    private Adapter_business adapter_business;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rv =  inflater.inflate(
                R.layout.activity_business_lists, container, false);
        bus_list_classes=getArguments().getParcelableArrayList("data_list");
        if(bus_list_classes.size()>0) {
            recyclerView = rv.findViewById(R.id.recyclerview);
            adapter_business = new Adapter_business(getContext(), bus_list_classes);
            LinearLayoutManager llm = new LinearLayoutManager(getContext());
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(llm);
            recyclerView.setAdapter(adapter_business);
        }
        return rv;
    }




}