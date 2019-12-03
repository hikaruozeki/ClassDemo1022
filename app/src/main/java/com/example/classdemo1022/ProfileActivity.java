package com.example.classdemo1022;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{
    Button buttonRegister;
    EditText editTextBirdName, editTextYourName;
    EditText editTextNewZipCode;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        editTextBirdName = findViewById(R.id.editTextBirdName);
        editTextYourName = findViewById(R.id.editTextYourName);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String email = user.getEmail();
            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();
            editTextYourName.setText(email);
        }
        editTextNewZipCode = findViewById(R.id.editTextNewZipCode);


        buttonRegister = findViewById(R.id.buttonRegister);
        buttonRegister.setOnClickListener(this);


    }




    @Override
    public void onClick(View v) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("birds");

        if (buttonRegister == v) {
            String BirdName = editTextBirdName.getText().toString();
            String YourName = editTextYourName.getText().toString();
            String NewZipCode = editTextNewZipCode.getText().toString();
            Integer Importance = 0;

            Bird myBird = new Bird(BirdName, YourName, NewZipCode,Importance);

            myRef.push().setValue(myBird);

            Intent mainIntent = new Intent(ProfileActivity.this, SettingsActivity.class);
            startActivity(mainIntent);
        }

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflator = getMenuInflater();
        inflator.inflate(R.menu.mainmenu, menu);

        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itemRegister) {
            Toast.makeText(this, "You are already in Register page, you fool!", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.itemSearch) {
            Intent SearchIntent = new Intent(this, SettingsActivity.class);
            startActivity(SearchIntent);
        } else if (item.getItemId() == R.id.itemLanding) {
            Intent SearchIntent = new Intent(this, LandingActivity.class);
            startActivity(SearchIntent);
        } else if (item.getItemId() == R.id.itemLogout) {
            Intent SearchIntent = new Intent(this, MainActivity.class);
            startActivity(SearchIntent);
        }
            return super.onOptionsItemSelected(item);
        }
    }









