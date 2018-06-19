package org.danielsoares.pickupapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;

public class SignUp extends AppCompatActivity {

    private Button signUpButton;
    private EditText name;
    private EditText email;
    private EditText password;
    private EditText confirmPassword;
    private TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Initializes Button and input text variables
        signUpButton = findViewById(R.id.signUpButton);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);
        signUpButton = findViewById(R.id.signUpButton);
        login = findViewById(R.id.login);

        // Directs you to login page
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(SignUp.this, Login.class);
                SignUp.this.startActivity(loginIntent);
            }

        });

        }


}
