package com.example.super_ponic_ultra.Demoone;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.super_ponic_ultra.R;


public class PepperBellFragment extends Fragment {
    Button button_Capsicum;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_pepper_bell, container, false);
        button_Capsicum=rootView.findViewById(R.id.Button_capsicum);
        button_Capsicum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an instance of the NutriGrowFragment
                NutriGrowFragment nutriGrowFragment = new NutriGrowFragment();
                // Get the FragmentManager
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.basel_fragment_container_Capsicum, nutriGrowFragment)
                        .addToBackStack(null)
                        .commit();


            }
        });
        return rootView;
    }
}