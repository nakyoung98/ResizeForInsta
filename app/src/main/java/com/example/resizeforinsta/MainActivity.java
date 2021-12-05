package com.example.resizeforinsta;

import static androidx.activity.result.ActivityResultCallerKt.registerForActivityResult;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;


import com.example.resizeforinsta.databinding.ActivityImageloadBinding;
import com.example.resizeforinsta.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    private static final String TAG = "Imageload Activity";

    ArrayList<Uri> uriList = new ArrayList<Uri>(); // 이미지 uri담을 array

    private ActivityImageloadBinding imageloadBinding;
    private ActivityMainBinding mainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());

        // view 설정
        View view = mainBinding.getRoot();
        setContentView(view);

        //버튼: 갤러리로부터 사진 받아옴
        mainBinding.getImageBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //intent to pick image from gallery
                Intent intent = new Intent(Intent.ACTION_PICK);

                //set type
                intent.setType("image/*");
                galleryActivityResultLauncher.launch(intent);
;
            }
        });
    }

    private ActivityResultLauncher<Intent> galleryActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>(){
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == Activity.RESULT_OK){
                        //image picked
                        //get uri of image
                        Intent data = result.getData();
                        Uri imageUri = data.getData();

                        mainBinding.showImage.setImageURI(imageUri);
                    }
                    else{
                        //canclled
                        Toast.makeText(MainActivity.this, "**Cancelled**", Toast.LENGTH_SHORT).show();
                    }
                }
            });

}