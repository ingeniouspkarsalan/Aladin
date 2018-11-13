package ustaad.aladin.com.Fragment_activities;

import android.support.annotation.NonNull;
        import android.support.annotation.Nullable;
        import android.support.v4.app.Fragment;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;

import java.util.ArrayList;

import ustaad.aladin.com.R;
import ustaad.aladin.com.classes.bus_list_class;

public class Map_activity extends Fragment {
    private ArrayList<bus_list_class> bus_list_classes;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rv =  inflater.inflate(
                R.layout.activity_map_activity, container, false);
        bus_list_classes=getArguments().getParcelableArrayList("data_list");
        return rv;
    }
}
