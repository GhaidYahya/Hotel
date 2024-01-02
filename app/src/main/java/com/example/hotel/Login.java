package com.example.hotel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private CheckBox rememberMeCheckBox;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        rememberMeCheckBox = findViewById(R.id.checkBox);
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        if (sharedPreferences.getBoolean("rememberMe", false)) {
            String storedEmail = sharedPreferences.getString("email", "");
            String storedPassword = sharedPreferences.getString("password", "");
            emailEditText.setText(storedEmail);
            passwordEditText.setText(storedPassword);
            rememberMeCheckBox.setChecked(true);
        }

        Button loginButton = findViewById(R.id.loginbtn);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });
    }

    private void loginUser() {
        String enteredEmail = emailEditText.getText().toString().trim();
        String enteredPassword = passwordEditText.getText().toString().trim();

        String storedEmail = sharedPreferences.getString("email", "");
        String storedPassword = sharedPreferences.getString("password", "");

        if (storedEmail.equals(enteredEmail)) {
            if (storedPassword.equals(enteredPassword)) {
                Intent intent = new Intent(this, home.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Incorrect password", Toast.LENGTH_SHORT).show();
            }
        } else {
            if (isValidEmail(enteredEmail)) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("email", enteredEmail);
                editor.putString("password", enteredPassword);
                editor.apply();

                Intent intent = new Intent(this, home.class);
                startActivity(intent);
                finish();
            } else {

                Toast.makeText(this, "Invalid email format, Please recheck it", Toast.LENGTH_SHORT).show();
            }
        }
        if (rememberMeCheckBox.isChecked()) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("rememberMe", true);
            editor.apply();
        } else {
            sharedPreferences.edit().remove("rememberMe").apply();
        }
    }

    private boolean isValidEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

}
