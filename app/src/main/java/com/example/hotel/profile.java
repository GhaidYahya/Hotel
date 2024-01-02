package com.example.hotel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;



import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class profile extends AppCompatActivity {

    private TextView profileName, profileEmail, profilePhone;
    private Button changeProfile, resendCode;
    private ImageView profileImage;
    private TextView verifyMsg;
    private CardView cardView;

    private static final String PREF_NAME = "user_pref";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileName = findViewById(R.id.profileName);
        profileEmail = findViewById(R.id.profileEmail);
        profilePhone = findViewById(R.id.profilePhone);
        changeProfile = findViewById(R.id.changeProfile);
        resendCode = findViewById(R.id.resendCode);
        profileImage = findViewById(R.id.profileImage);
        verifyMsg = findViewById(R.id.verifyMsg);
        cardView = findViewById(R.id.cardView);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String userEmail = sharedPreferences.getString("email", "");
        String userPassword = sharedPreferences.getString("password", "");


        Log.d("MainActivity", "User Email: " + userEmail);

        String userName = extractNameFromEmail(userEmail);
        profileName.setText("Name: " + userName);


        profileEmail.setText("Email: " + userEmail);
        profilePhone.setText("Password: " + userPassword);
    }


    private String extractNameFromEmail(String email) {
        int atIndex = email.indexOf('@');
        if (atIndex != -1) {
            return email.substring(0, atIndex);
        } else {
            return "Unknown";
        }
    }
}
