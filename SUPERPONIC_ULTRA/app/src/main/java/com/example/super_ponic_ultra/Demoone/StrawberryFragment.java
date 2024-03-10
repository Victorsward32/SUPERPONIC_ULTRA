package com.example.super_ponic_ultra.Demoone;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.super_ponic_ultra.R;

public class StrawberryFragment extends Fragment {
    Button button_strawberry;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview= inflater.inflate(R.layout.fragment_strawberry, container, false);
        button_strawberry=rootview.findViewById(R.id.Strawberry_Button);
        button_strawberry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an instance of the NutriGrowFragment
                NutriGrowFragment nutriGrowFragment = new NutriGrowFragment();
                // Get the FragmentManager
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.basel_fragment_container_Strawberry, nutriGrowFragment)
                        .addToBackStack(null)
                        .commit();


            }
        });
        // Inflate the layout for this fragment
        return rootview;
    }
}