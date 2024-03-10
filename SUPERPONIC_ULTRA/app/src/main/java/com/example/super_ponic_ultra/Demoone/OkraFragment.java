package com.example.super_ponic_ultra.Demoone;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.super_ponic_ultra.R;

public class OkraFragment extends Fragment {
    Button button_okra;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_okra, container, false);
        button_okra=rootView.findViewById(R.id.Okra_Button);

        button_okra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an instance of the NutriGrowFragment
                NutriGrowFragment nutriGrowFragment = new NutriGrowFragment();
                // Get the FragmentManager
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.basel_fragment_container_Okra, nutriGrowFragment)
                        .addToBackStack(null)
                        .commit();

            }
        });
        // Inflate the layout for this fragment
        return rootView;
    }
}