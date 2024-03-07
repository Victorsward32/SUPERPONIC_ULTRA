package com.example.super_ponic_ultra.Demoone;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.cardview.widget.CardView;
import com.example.super_ponic_ultra.R;

public class PlantariumFragment extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plantarium, container, false);

        CardView basel = view.findViewById(R.id.baselid);
        CardView okra = view.findViewById(R.id.okraid);
        CardView grape = view.findViewById(R.id.grapeid);
        CardView spniach = view.findViewById(R.id.spniachid);
        CardView pepperBell = view.findViewById(R.id.pepper_bellid);
        CardView eggplant = view.findViewById(R.id.eggplant);
        CardView strawberry = view.findViewById(R.id.strawberryid);
        CardView tomato = view.findViewById(R.id.tomatoid);

        basel.setOnClickListener(this);
        okra.setOnClickListener(this);
        grape.setOnClickListener(this);
        spniach.setOnClickListener(this);
        pepperBell.setOnClickListener(this);
        eggplant.setOnClickListener(this);
        strawberry.setOnClickListener(this);
        tomato.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment fragment = null;

        if (v.getId() == R.id.baselid) {
            fragment = new BaselFragment();
        } else if (v.getId() == R.id.okraid) {
            fragment = new OkraFragment();
        } else if (v.getId() == R.id.grapeid) {
            fragment = new GrapeFragment();
        } else if (v.getId() == R.id.spniachid) {
            fragment = new SpinachFragment();
        } else if (v.getId() == R.id.pepper_bellid) {
            fragment = new PepperBellFragment();
        } else if (v.getId() == R.id.eggplant) {
            fragment = new EggPlantFragment();
        } else if (v.getId() == R.id.strawberryid) {
            fragment = new StrawberryFragment();
        } else if (v.getId() == R.id.tomatoid) {
            fragment = new TomatoFragment();
        }

        if (fragment != null) {
            transaction.replace(R.id.framelayout, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}
