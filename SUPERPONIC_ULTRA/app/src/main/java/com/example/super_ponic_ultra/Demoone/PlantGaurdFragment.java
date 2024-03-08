package com.example.super_ponic_ultra.Demoone;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.super_ponic_ultra.Demoone.Gemeniai.RequestNetwork;
import com.example.super_ponic_ultra.Demoone.Gemeniai.RequestNetworkController;
import com.example.super_ponic_ultra.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlantGaurdFragment extends Fragment {

    private ArrayList<HashMap<String, Object>> list_map = new ArrayList<>();

    private RecyclerView recyclerview1;
    private LinearLayout linear1;
    private EditText edittext1;
    private Button button_submit;

    private RequestNetwork Net;
    private RequestNetwork.RequestListener _Net_request_listener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plant_gaurd, container, false);
        recyclerview1 = view.findViewById(R.id.recyclerview1);
        linear1 = view.findViewById(R.id.linear1);
        edittext1 = view.findViewById(R.id.edittext1);
        button_submit = view.findViewById(R.id.button_submit);
        Net = new RequestNetwork(getActivity());

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setStackFromEnd(true);
        recyclerview1.setLayoutManager(layoutManager);

        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                if (edittext1.getText().toString().length() < 2) {
                    Toast.makeText(getContext(), "Please Enter your text", Toast.LENGTH_SHORT).show();
                } else {
                    String Input_data = edittext1.getText().toString();
                    HashMap<String, Object> header = new HashMap<>();
                    HashMap<String, Object> params = new HashMap<>();

                    header.put("Content-Type", "application/json");

                    ArrayList<HashMap<String, Object>> contentsList = new ArrayList<>();
                    HashMap<String, Object> partsMap = new HashMap<>();
                    ArrayList<HashMap<String, Object>> partsList = new ArrayList<>();
                    partsMap.put("text", Input_data);
                    partsList.add(partsMap);
                    HashMap<String, Object> contentMap = new HashMap<>();
                    contentMap.put("parts", partsList);
                    contentsList.add(contentMap);
                    params.put("contents", contentsList);

                    Gson gson = new Gson();
                    String jsonParams = gson.toJson(params);

                    String geminiApiUrl = "https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent?key=AIzaSyDSm1HFptFzHAVR-Vzw2N-n-NeWbCaULFQ";

                    Net.setHeaders(header);
                    Net.setParams(params, RequestNetworkController.REQUEST_BODY);
                    Net.startRequestNetwork(RequestNetworkController.POST, geminiApiUrl, "", _Net_request_listener);

                    HashMap<String, Object> map1 = new HashMap<>();
                    map1.put("User", "You");
                    map1.put("Text", edittext1.getText().toString());
                    list_map.add(map1);
                    recyclerview1.setAdapter(new Recyclerview1Adapter(list_map));
                    edittext1.setText("");
                }
            }
        });

        _Net_request_listener = new RequestNetwork.RequestListener() {
            @Override
            public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
                final String _tag = _param1;
                final String _response = _param2;
                final HashMap<String, Object> _responseHeaders = _param3;
                Map<String, Object> geminiMap = new Gson().fromJson(_response, Map.class);
                List<Map<String, Object>> candidates = (List<Map<String, Object>>) geminiMap.get("candidates");
                Map<String, Object> content = (Map<String, Object>) candidates.get(0).get("content");
                List<Map<String, Object>> parts = (List<Map<String, Object>>) content.get("parts");
                String geminiText = parts.get(0).get("text").toString();

                HashMap<String, Object> map1 = new HashMap<>();
                map1.put("User", "PlantBot");
                map1.put("Text", geminiText);
                list_map.add(map1);
                recyclerview1.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onErrorResponse(String _param1, String _param2) {
                final String _tag = _param1;
                final String _message = _param2;

            }
        };

        return view;
    }

    public class Recyclerview1Adapter extends RecyclerView.Adapter<Recyclerview1Adapter.ViewHolder> {

        ArrayList<HashMap<String, Object>> _data;

        public Recyclerview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
            _data = _arr;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View _v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cus, parent, false);
            return new ViewHolder(_v);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
            View _view = holder.itemView;

            final LinearLayout linear_gpt = _view.findViewById(R.id.linear_gpt);
            final LinearLayout linearuser = _view.findViewById(R.id.linearuser);
            final androidx.cardview.widget.CardView cardview1 = _view.findViewById(R.id.cardview1);
            final LinearLayout linear2 = _view.findViewById(R.id.linear2);
            final ImageView image_user = _view.findViewById(R.id.image_user);
            final TextView text_username = _view.findViewById(R.id.text_username);
            final TextView text_response = _view.findViewById(R.id.text_response);
            final LinearLayout linear4 = _view.findViewById(R.id.linear4);
            final androidx.cardview.widget.CardView cardview2 = _view.findViewById(R.id.cardview2);
            final TextView textview1 = _view.findViewById(R.id.textview1);
            final TextView textview2 = _view.findViewById(R.id.textview2);
            final ImageView imageview1 = _view.findViewById(R.id.imageview1);

            if (_data.get((int) position).get("User").toString().equals("You")) {
                linearuser.setBackground(new GradientDrawable() {
                    public GradientDrawable getIns(int a, int b) {
                        this.setCornerRadius(a);
                        this.setColor(b);
                        return this;
                    }
                }.getIns((int) 10, 0xFF69F0AE));
                textview1.setText(list_map.get((int) position).get("User").toString());
                textview2.setText(list_map.get((int) position).get("Text").toString());
                linear_gpt.setVisibility(View.GONE);
                linearuser.setVisibility(View.VISIBLE);
            } else {
                linear_gpt.setBackground(new GradientDrawable() {
                    public GradientDrawable getIns(int a, int b) {
                        this.setCornerRadius(a);
                        this.setColor(b);
                        return this;
                    }
                }.getIns((int)10, 0xFF40C4FF)); // This sets the background color and corner radius for the Gemini response layout
                text_username.setText(list_map.get((int) position).get("User").toString());
                text_response.setText(list_map.get((int) position).get("Text").toString());
                linearuser.setVisibility(View.GONE);
                linear_gpt.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public int getItemCount() {
            return _data.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public ViewHolder(@NonNull View v) {
                super(v);
            }
        }
    }
}

