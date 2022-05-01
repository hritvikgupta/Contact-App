package com.example.savecontacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CreateContact extends AppCompatActivity implements View.OnClickListener {

    EditText etName, etNumber, etWebPage, etLocation;
    ImageView ivSad, ivHappy, ivNeutral;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);

        etName = findViewById(R.id.etName);
        etNumber = findViewById(R.id.etNumber);
        etWebPage = findViewById(R.id.etWebPage);
        etLocation= findViewById(R.id.etLocation);

        ivHappy = findViewById(R.id.ivHappy);
        ivSad= findViewById(R.id.ivSad);
        ivNeutral = findViewById(R.id.ivNeutral);

        //Rather than creating each on click listener for every button that done the same taks
        //we simply put implement View.onClickListener on the main create contact class above
        //after that we create a  onclick function below that will does the task for all
        // we create a setOnClickListener(this) for each button to say the button that whenever you are
        //called by createcontact class respond using onclick class

        ivHappy.setOnClickListener(this);
        ivSad.setOnClickListener(this);
        ivNeutral.setOnClickListener(this);


    }
    @Override
    public void onClick(View view){

        if(etName.getText().toString().isEmpty() || etNumber.getText().toString().isEmpty() || etWebPage.getText().toString().isEmpty() || etLocation.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Please Enter all Fields First", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent = new Intent();
            intent.putExtra("Name", etName.getText().toString().trim());
            intent.putExtra("Number", etNumber.getText().toString().trim());
            intent.putExtra("WebPage", etWebPage.getText().toString().trim());
            intent.putExtra("Location", etLocation.getText().toString().trim());

            //Now to display the mood that user clicked on main activity we need to know which mood is passed
            //for that we have to know what user has clicked on the application page
            //for that we use view that is passed in this function, this view has access to everything on
            //the page that user has clicked

            if(view.getId() == R.id.ivHappy)
                    intent.putExtra("Mood", "Happy");
            else if(view.getId() == R.id.ivNeutral)
                    intent.putExtra("Mood", "Neutral");
            else if(view.getId() == R.id.ivSad)
                    intent.putExtra("Mood", "Sad");


            setResult(RESULT_OK,intent);
            CreateContact.this.finish();

            }


        }




    }
