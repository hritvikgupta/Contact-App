package com.example.savecontacts;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toast.*;
import android.net.Uri;

public class MainActivity extends AppCompatActivity {



    ImageView ivMood, ivLocation, ivPhone, ivWebPage;
    Button btnCreate;
    String name = "", number = "", web = "", mood = "", location = "";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivMood = findViewById(R.id.ivMood);
        ivLocation = findViewById(R.id.ivLocation);
        ivPhone = findViewById(R.id.ivPhone);
        ivWebPage = findViewById(R.id.ivWebPage);
        btnCreate = findViewById(R.id.btnCreate);

        ivLocation.setVisibility(View.GONE);
        ivWebPage.setVisibility(View.GONE);
        ivPhone.setVisibility(View.GONE);
        ivMood.setVisibility(View.GONE);


        ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK )
                        {
                            ivLocation.setVisibility(View.VISIBLE);
                            ivWebPage.setVisibility(View.VISIBLE);
                            ivPhone.setVisibility(View.VISIBLE);
                            ivMood.setVisibility(View.VISIBLE);
                            // There are no request codes
                            Intent data = result.getData();
                            //Catching the data from the Activity3 using surname
                            name = data.getStringExtra("Name");
                            number = data.getStringExtra("Number");
                            web = data.getStringExtra("WebPage");
                            location = data.getStringExtra("Location");

                            mood = data.getStringExtra("Mood");
                            if(mood.equals("happy"))
                            {
                               ivMood.setImageResource(R.drawable.happysent);
                            }
                            else if(mood.equals("sad"))
                            {
                                ivMood.setImageResource(R.drawable.sad);
                            }
                            else {
                                ivMood.setImageResource(R.drawable.neutral);
                            }



                        }

                    }
                });


        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, com.example.savecontacts.CreateContact.class);
                someActivityResultLauncher.launch(intent);

            }
        });

        ivPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+number));
                startActivity(intent);


            }
        });

        ivWebPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://"+web));
                startActivity(intent);

            }
        });

        ivLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q="+location));
                startActivity(intent);

            }
        });





    }
}