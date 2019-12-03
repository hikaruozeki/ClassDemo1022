package com.example.classdemo1022;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.flags.Flag;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener{

    Button buttonSearch;
    Button buttonAdd;
    EditText editTextInputZipCode;
    TextView textViewListOfBirds;
    TextView textViewListOfName;
    TextView textViewImportance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        editTextInputZipCode = findViewById(R.id.editTextInputZipCode);
        buttonSearch = findViewById(R.id.buttonSearch);
        buttonSearch.setOnClickListener(this);

        buttonAdd = findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(this);


        textViewListOfBirds = findViewById(R. id.textViewListOfBirds);
        textViewListOfName = findViewById(R. id. textViewListOfName);
        textViewImportance = findViewById(R.id.textViewImportance);

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
            Intent MainIntent = new Intent(this, ProfileActivity.class);
            startActivity(MainIntent);
        } else if (item.getItemId() == R.id.itemSearch) {
            Toast.makeText(this, "You are already in Search page, you fool!", Toast.LENGTH_SHORT).show();

        } else if (item.getItemId() == R.id.itemLanding) {
            Intent SearchIntent = new Intent(this, LandingActivity.class);
            startActivity(SearchIntent);
        } else if (item.getItemId() == R.id.itemLogout) {
            Intent SearchIntent = new Intent(this, MainActivity.class);
            startActivity(SearchIntent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("birds");

        if (buttonSearch == view) {
            String findbird = editTextInputZipCode.getText().toString();
            myRef.orderByChild("zipcode").equalTo(findbird).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    //String findKey = dataSnapshot.getKey();//
                    Bird foundBird = dataSnapshot.getValue(Bird.class);
                    String findBird = foundBird.bird;
                    String findName = foundBird.you;
                    Integer findImportance = foundBird.importance;
                    textViewListOfBirds.setText(findBird);
                    textViewListOfName.setText(findName);
                    textViewImportance.setText(String.valueOf(findImportance));
                }


                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }

            });

        } else if(buttonAdd == view){
            String addbird = editTextInputZipCode.getText().toString();
            myRef.orderByChild("zipcode").equalTo(addbird).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    String findKey = dataSnapshot.getKey();
                    Bird foundBird = dataSnapshot.getValue(Bird.class);
                    Integer findImportance1 = foundBird.importance + 1;
                    textViewImportance.setText(String.valueOf(findImportance1));

                    myRef.child(findKey).child("importance").setValue(findImportance1);
                }


                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }

            });




        }

    }
}
