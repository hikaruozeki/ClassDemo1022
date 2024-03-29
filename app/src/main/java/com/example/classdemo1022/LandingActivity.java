package com.example.classdemo1022;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LandingActivity extends AppCompatActivity implements View.OnClickListener{

    TextView textViewBirdNameBiggest;
    TextView textViewBissgestImportance;
    Button buttonPush;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        textViewBirdNameBiggest = findViewById(R.id.textViewBirdNameBiggest);
        textViewBissgestImportance = findViewById(R.id.textViewBiggestImportance);
        buttonPush = findViewById(R.id.buttonPush);
        buttonPush.setOnClickListener(this);

    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

       if(item.getItemId() == R.id.itemLanding) {
           Toast.makeText(this, "You are already in Landing page, you fool!", Toast.LENGTH_SHORT).show();

       } else if(item.getItemId() == R.id.itemRegister) {

           Intent profileIntent = new Intent(this, ProfileActivity.class);
           startActivity(profileIntent);

       } else if(item.getItemId() == R.id.itemSearch) {

           Intent settingsIntent = new Intent(this, SettingsActivity.class);
           startActivity(settingsIntent);

       } else if(item.getItemId() == R.id.itemLogout) {

           Intent logoutIntent = new Intent(this, MainActivity.class);
           startActivity(logoutIntent);
       }



        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

    }
}
