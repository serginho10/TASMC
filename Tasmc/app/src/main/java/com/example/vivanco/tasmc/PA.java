package com.example.vivanco.tasmc;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class PA extends Fragment {


    public PA() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_p, container, false);
        ImageView main = (ImageView) rootView.findViewById(R.id.main);
        main.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        main.setImageResource(R.drawable.pa);
        return rootView;
    }


}
