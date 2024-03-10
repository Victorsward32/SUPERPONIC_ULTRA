package com.example.super_ponic_ultra.Demoone;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.super_ponic_ultra.R;



public class TomatoFragment extends Fragment {
    Button button_Tomato;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview=inflater.inflate(R.layout.fragment_tomato, container, false);
        button_Tomato=rootview.findViewById(R.id.Button_Tomatto);
        button_Tomato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NutriGrowFragment nutriGrowFragment =new NutriGrowFragment();

                getParentFragmentManager().beginTransaction()
                        .replace(R.id.basel_fragment_container_tomatto,nutriGrowFragment)
                        .addToBackStack(String.valueOf(null))
                        .commit();
            }
        });
        // Inflate the layout for this fragment
        return rootview;
    }
}