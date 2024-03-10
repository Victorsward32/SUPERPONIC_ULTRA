package com.example.super_ponic_ultra.Demoone;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.super_ponic_ultra.R;

public class NutriGrowFragment extends Fragment {

    Button t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_nutri_grow, container, false);

        t1 = rootView.findViewById(R.id.item1_buy_button);
        t2 = rootView.findViewById(R.id.item2_buy_button);
        t3 = rootView.findViewById(R.id.item3_buy_button);
        t4 = rootView.findViewById(R.id.item4_buy_button);
        t5 = rootView.findViewById(R.id.item5_buy_button);
        t6 = rootView.findViewById(R.id.item6_buy_button);
        t7 = rootView.findViewById(R.id.item7_buy_button);
        t8 = rootView.findViewById(R.id.item8_buy_button);
        t9 = rootView.findViewById(R.id.item9_buy_button);
        t10 = rootView.findViewById(R.id.item10_buy_button);
        t11 = rootView.findViewById(R.id.item11_buy_button);
        t12 = rootView.findViewById(R.id.item12_buy_button);

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebPage("https://www.amazon.in/Garden-Tomato-Nutrients-Advance-Package/dp/B0BHWRXBXN");
            }
        });

        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebPage("http://www.plantit-growit.com/product/hydro-chilli-pepper-feed-1l/");
            }
        });

        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebPage("https://www.amazon.in/City-Greens-Hydroponic-Hydroponics-Nutrients/dp/B097MJRDR8/ref=asc_df_B097MJRDR8/?tag=googleshopdes-21&linkCode=df0&hvadid=397009291831&hvpos=&hvnetw=g&hvrand=2031038251862210118&hvpone=&hvptwo=&hvqmt=&hvdev=c&hvdvcmdl=&hvlocint=&hvlocphy=9303196&hvtargid=pla-1394025963505&psc=1&ext_vrnc=hi");
            }
        });

        t4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebPage("https://www.amazon.in/Garden-Water-Soluble-Plant-Control/dp/B09NTR9M2C/ref=asc_df_B09NTR9M2C/?tag=googleshopdes-21&linkCode=df0&hvadid=544920781431&hvpos=&hvnetw=g&hvrand=12766024395577270025&hvpone=&hvptwo=&hvqmt=&hvdev=c&hvdvcmdl=&hvlocint=&hvlocphy=9303196&hvtargid=pla-1592761598121&th=1");
            }
        });
        t5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebPage("https://www.wehydroponics.com/store/product/coco-pellets-coco-disc-coco-plug-50mm-growing-medium/");          }
        });
        t6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebPage("https://www.wehydroponics.com/store/product/clay-balls/");
            }
        });
        t7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebPage("https://www.wehydroponics.com/store/product/2-inch-netpot-2/");             }
        });
        t8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebPage("https://www.amazon.in/gp/product/B0CF9PLL5Q/ref=ppx_yo_dt_b_asin_title_o00_s00?ie=UTF8&psc=1");
            }
        });
        t9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebPage("https://www.amazon.in/gp/product/B0CFBCY52N/ref=ppx_yo_dt_b_asin_title_o03_s00?ie=UTF8&th=1");
            }
        });
        t10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebPage("https://www.amazon.in/Navika-Seeds-Grow-Us-Gardening/dp/B0BV6SLMWH/ref=sr_1_11?crid=1FR5KMNDXK0QR&dib=eyJ2IjoiMSJ9.wekHoR1r8SfOPwiGlaVd37cLHRjaoqx_QlLMwZuWy7wBDKkuHQGckrH1NMUQaV69UP25kYJOgXqmqGrbdtMWl4D8Y8VasAMpJ4q0x6gh5IgHxA5t91C6fh75C6qOHWBNLOExlLIRmQB_FQFAz0mePFWE4bTt1DarylVJsBYFx52sR86jvrKacETNzbGglgXj5_HawOvb5qcbkseAQFDlIAGVQGrxDDrBnPurHYG8-rkbpD_Y7k8RBT2t5fb1YqNHqu09Ues3C7SCUHsDNqAG7kBF-QG35WZTCEtItlGCSio.3oN5HiKYUnVNaei6fECLoi37jaUqNXiqYRtiYkPee4s&dib_tag=se&keywords=tomato%2Bseeds&qid=1710081102&sprefix=tamat%2Caps%2C213&sr=8-11&th=1");
            }
        });
        t11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebPage("https://www.amazon.in/Kraft-Seeds-KIV003-Spinach-SEEDS/dp/B00KOKW6H6");
            }
        });
        t12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebPage("https://www.amazon.in/Ultimate-Vegetable-Broccoli-Coriander-Planting/dp/B0C2NBCP6L/?_encoding=UTF8&pd_rd_w=MqwH2&content-id=amzn1.sym.4d5b78c6-4f80-4b93-8d16-deb7aaa19e3f%3Aamzn1.symc.afd86303-4a72-4e34-8f6b-19828329e602&pf_rd_p=4d5b78c6-4f80-4b93-8d16-deb7aaa19e3f&pf_rd_r=8H65FNMJ0FPVED4R947R&pd_rd_wg=f1pFV&pd_rd_r=680f1d0b-e711-4d11-b55a-ccc8acf215b1&ref_=pd_gw_ci_mcx_mr_hp_atf_m");
            }
        });

        return rootView;
    }

    // Method to open a web page using an Intent
    private void openWebPage(String url) {
        Log.d("WebView", "Opening URL: " + url);
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Log.e("WebView", "Error opening web page: " + e.getMessage());
        }
    }
}
