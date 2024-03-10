package com.example.super_ponic_ultra.Demoone;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.super_ponic_ultra.R;

public class GrapeFragment extends Fragment {

    Button fertilizersTextView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_grape, container, false);

        fertilizersTextView = rootView.findViewById(R.id.Buttongrape);

        fertilizersTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an instance of the NutriGrowFragment
                NutriGrowFragment nutriGrowFragment = new NutriGrowFragment();
                // Get the FragmentManager
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container_grape, nutriGrowFragment)
                        .addToBackStack(null)
                        .commit();

            }
        });

        // Inflate the layout for this fragment
        return rootView;
    }
}