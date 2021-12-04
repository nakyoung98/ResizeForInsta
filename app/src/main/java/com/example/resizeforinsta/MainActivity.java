package com.example.resizeforinsta;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import com.example.resizeforinsta.databinding.ActivityImageloadBinding;
import com.example.resizeforinsta.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    private static final String TAG = "Imageload Activity";

    ArrayList<Uri> uriList = new ArrayList<Uri>(); // 이미지 uri담을 array
    private ActivityResultLauncher<Intent> resultLauncher; //intent

    private ActivityImageloadBinding imageloadBinding;
    private ActivityMainBinding mainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());

        // view 설정
        View view = mainBinding.getRoot();
        setContentView(view);

        //앨범 이동 버튼

        mainBinding.getImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                resultLauncher = registerForActivityResult(new ActivityResultContracts.GetContent(),
                        new ActivityResultCallback<Uri>(){
                            @Override
                            public void onActivityResult(Uri result) {

                            }
                        });
            }
        });
    }
}