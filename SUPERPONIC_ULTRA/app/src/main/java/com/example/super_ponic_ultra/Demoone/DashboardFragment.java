//package com.example.super_ponic_ultra.Demoone;
//
//import  android.os.Bundle;
//
//import androidx.fragment.app.Fragment;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.example.super_ponic_ultra.R;
//
//
//public class DashboardFragment extends Fragment {
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_dashboard, container, false);
//    }
//}

package com.example.super_ponic_ultra.Demoone;

import static android.content.ContentValues.TAG;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.super_ponic_ultra.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DashboardFragment extends Fragment {

    Switch led,pump,solA,solB;
    TextView temp,ph,fan,humiditystatus;

    private ProgressBar loadingPB;
    private TextView cityNameTv, temperatureTV, conditionTV, windTV, cloudTV, humidityTV;
    private TextInputEditText CityEdit;
    private ImageView iconIV, searchIv, countryFlag;
    private LocationManager locationManager;
    private int PERMISSION_CODE = 1;
    private String cityName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);

        ///Realtime Db Status id Declaration
        //switch
        led=rootView.findViewById(R.id.led_button);
        pump=rootView.findViewById(R.id.pump1_button);
        solA=rootView.findViewById(R.id.pump2_button);
        solB=rootView.findViewById(R.id.pump3_button);

        //textview
        temp=rootView.findViewById(R.id.temprature_status);
        ph=rootView.findViewById(R.id.phstatus);
        fan=rootView.findViewById(R.id.fanStatus);
        humiditystatus=rootView.findViewById(R.id.humidity_status);


        // Initialize UI elements
        cityNameTv = rootView.findViewById(R.id.idTVCityName);
        temperatureTV = rootView.findViewById(R.id.idTVTemperature);
        conditionTV = rootView.findViewById(R.id.idTVCondition);
        CityEdit = rootView.findViewById(R.id.idETCity);
        iconIV = rootView.findViewById(R.id.idIVIcon);
        searchIv = rootView.findViewById(R.id.idTVSearch);
        windTV = rootView.findViewById(R.id.idTVWindTextMetric);
        cloudTV = rootView.findViewById(R.id.idTVCloudTextMetric);
        humidityTV = rootView.findViewById(R.id.idTVCHumidTextMetric);
        countryFlag = rootView.findViewById(R.id.idIVFlag);



        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        led.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                myRef.child("/Controls/Led").setValue(true);
            } else {
                myRef.child("/Controls/Led").setValue(false);
            }
        });
        pump.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                myRef.child("/Controls/PumpC").setValue(true);
            } else {
                myRef.child("/Controls/PumpC").setValue(false);
            }
        });
        solA.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                myRef.child("/Controls/PumpA").setValue(true);
            } else {
                myRef.child("/Controls/PumpA").setValue(false);
            }
        });
        solB.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                myRef.child("/Controls/PumpB").setValue(true);
            } else {
                myRef.child("/Controls/PumpB").setValue(false);
            }
        });



//// Write Realtime DB
//        // Write a message to the database
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("Sensors"); // Update the reference to point to the "Sensors" node

// Write Realtime DB
        DatabaseReference myRef1 = database.getReference("Sensor");
        myRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Retrieve sensor data
                Double humidity = dataSnapshot.child("Humidity").getValue(Double.class);
                Double phLevel = dataSnapshot.child("PhValue").getValue(Double.class);
                Double temperature = dataSnapshot.child("Temperature").getValue(Double.class);
                Boolean fanStatus = dataSnapshot.child("Fan").getValue(Boolean.class);

                // Update UI elements with sensor data
                if (humidity != null) {
                    humiditystatus.setText(" " + humidity +" %");
                }
                if (phLevel != null) {
                    // Ensure that pH level is positive
                    if (phLevel < 0) {
                        phLevel = Math.abs(phLevel);
                    }
                    // Adjust pH level if it's above 14
                    phLevel = Math.min(14.0, phLevel);
                    ph.setText(" " + phLevel);
                }
                if (temperature != null) {
                    temp.setText(" " + temperature +" C");
                }
                if (fanStatus != null) {
                    if (fanStatus){
                        fan.setText("On");
                    }
                    else{
                        fan.setText("Off");
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });





        locationManager = (LocationManager) requireActivity().getSystemService(requireContext().LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSION_CODE);
        } else {
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if (location != null) {
                cityName = getCityName(location.getLongitude(), location.getLatitude());
                getWeatherInfo(cityName);
            } else {
                Toast.makeText(requireContext(), "Location not available", Toast.LENGTH_SHORT).show();
            }
        }

        setTimeBasedBackground();

        searchIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = CityEdit.getText().toString().trim(); // Trim the input to remove extra spaces
                if (city.isEmpty()) {
                    Toast.makeText(requireContext(), "Please enter city name", Toast.LENGTH_SHORT).show();
                } else {
                    cityNameTv.setText(city);
                    getWeatherInfo(city);
                }
            }
        });

        return rootView;
    }

    private void setTimeBasedBackground() {
        Calendar calendar = Calendar.getInstance();
        Date currentTime = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("HH", Locale.getDefault());
        String currentHourString = sdf.format(currentTime);
        int currentHour = Integer.parseInt(currentHourString);

        int dayImage = R.drawable.day;
        int nightImage = R.drawable.night;
        int backgroundResource;

        if (currentHour >= 18 || currentHour < 6) {
            // Night time (6 PM to 6 AM)
            backgroundResource = nightImage;
        } else {
            // Day time (6 AM to 6 PM)
            backgroundResource = dayImage;
        }

        // You can set the background of the root view or any other view as per your requirement
        // For example:
        // rootView.setBackgroundResource(backgroundResource);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(requireContext(), "Permission Granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(requireContext(), "Please provide the permission", Toast.LENGTH_SHORT).show();
                requireActivity().finish();
            }
        }
    }

    private String getCityName(double longitude, double latitude) {
        String cityName = "Not Found";
        Geocoder gcd = new Geocoder(requireContext(), Locale.getDefault());
        try {
            List<Address> addresses = gcd.getFromLocation(latitude, longitude, 10);
            for (Address adr : addresses) {
                if (adr != null) {
                    String city = adr.getLocality();
                    if (city != null && !city.equals("")) {
                        cityName = city;
                    } else {
                        Toast.makeText(requireContext(), "User City Not Found..", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cityName;
    }

    private void getWeatherInfo(String cityName) {
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&appid=89852f15bebd043e42effdd09d6aef37&units=metric";
        cityNameTv.setText(cityName);
        RequestQueue requestQueue = Volley.newRequestQueue(requireContext());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject mainObject = response.getJSONObject("main");
                    String temperature = mainObject.getString("temp");
                    temperatureTV.setText(temperature + "°C");

                    JSONArray weatherArray = response.getJSONArray("weather");
                    if (weatherArray.length() > 0) {
                        JSONObject weatherObject = weatherArray.getJSONObject(0);
                        String condition = weatherObject.getString("main");
                        String description = weatherObject.getString("description");
                        conditionTV.setText(condition + " (" + description + ")");

                        JSONObject windObject = response.getJSONObject("wind");
                        double windSpeed = windObject.getDouble("speed");
                        String windInfo = windSpeed + " m/s";
                        windTV.setText(windInfo);

                        JSONObject cloudObject = response.getJSONObject("clouds");
                        int cloudPercentage = cloudObject.getInt("all");
                        String cloudInfo = cloudPercentage + "%";
                        cloudTV.setText(cloudInfo);

                        double humidity = mainObject.getDouble("humidity");
                        String humidityInfo =  humidity + "%";
                        humidityTV.setText(humidityInfo);

                        String cityName = response.getString("name");
                        cityNameTv.setText(cityName);

                        JSONObject sysObject = response.getJSONObject("sys");
                        String countryCode = sysObject.getString("country");
                        String countryUrl = "https://flagcdn.com/144x108/" + countryCode.toLowerCase() + ".png";
                        // You can load the country flag image here if needed

                        String iconCode = weatherObject.getString("icon");
                        String iconUrl = "https://openweathermap.org/img/w/" + iconCode + ".png";
                        // You can load the weather icon image here if needed
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(requireContext(), "Error fetching weather data", Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonObjectRequest);
    }

}
