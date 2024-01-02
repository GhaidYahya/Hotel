package com.example.hotel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class home extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);




        Button profileButton = findViewById(R.id.profile_btn);
        Button recipesButton = findViewById(R.id.reci_btn);
        Button logoutButton = findViewById(R.id.logoutbtn);

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openProfileActivity();
            }
        });

        recipesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRecipesActivity();
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
    }

    private void openProfileActivity() {
        Intent intent = new Intent(this, profile.class);
        startActivity(intent);
    }

    private void openRecipesActivity() {
        Intent intent = new Intent(this, recipes.class);
        startActivity(intent);
    }

    private void logout() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish();
    }
    @Override
    protected void onStop() {
        super.onStop();
    }
}
