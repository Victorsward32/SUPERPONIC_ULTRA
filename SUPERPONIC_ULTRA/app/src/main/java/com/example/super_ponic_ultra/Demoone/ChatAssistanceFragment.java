package com.example.super_ponic_ultra.Demoone;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.super_ponic_ultra.R;
import com.google.android.material.textfield.TextInputEditText;

public class ChatAssistanceFragment extends Fragment {

    private static final int SELECT_IMAGE_REQUEST = 1;

    private Bitmap image;
    private ImageView imageView;
    private TextInputEditText queryEditText;
    private TextView responseTextView;
    private Button sendQueryButton, selectImageButton;
    private ProgressBar progressBar;

    // ActivityResultLauncher for selecting images
    private final ActivityResultLauncher<Intent> selectImageLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    Uri imageUri = result.getData().getData();
                    try {
                        Bitmap originalImage = MediaStore.Images.Media.getBitmap(requireContext().getContentResolver(), imageUri);
                        int originalWidth = originalImage.getWidth();
                        int originalHeight = originalImage.getHeight();
                        int targetWidth = (int) (originalWidth * 0.5);
                        int targetHeight = (int) (originalHeight * 0.5);
                        image = Bitmap.createScaledBitmap(originalImage, targetWidth, targetHeight, false);
                        imageView.setImageBitmap(originalImage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
    );

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat_assistance, container, false); // Changed layout here

        queryEditText = view.findViewById(R.id.queryEditText);
        responseTextView = view.findViewById(R.id.modelResponseTextView);
        sendQueryButton = view.findViewById(R.id.sendPromptButton);
        selectImageButton = view.findViewById(R.id.selectImageButton);
        progressBar = view.findViewById(R.id.sendPromptProgressBar);
        imageView = view.findViewById(R.id.imageView);

        sendQueryButton.setOnClickListener(v -> {
            // Instantiate GeminiPro
            GeminiPro model = new GeminiPro(); // Changed context here

            String query = queryEditText.getText().toString();
            progressBar.setVisibility(View.VISIBLE);

            responseTextView.setText("");
            queryEditText.setText("");

            // Call getResponse method of GeminiPro
            model.getResponse(query, image, new ResponseCallback() {
                @Override
                public void onResponse(String response) {
                    responseTextView.setText(response);
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onError(Throwable throwable) {
                    // Use requireContext() instead of MainActivity.this
                    Toast.makeText(requireContext(), "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            });
        });

        selectImageButton.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            selectImageLauncher.launch(intent);
        });

        return view;
    }
}
