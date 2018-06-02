package org.danielsoares.pickupapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Context;
import android.view.View;

public class Login extends AppCompatActivity {

    private Button loginButton;
    private EditText email;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = (Button) findViewById(R.id.loginButton);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);

        final String emailString = email.getText().toString();
        final String passwordString = password.getText().toString();

        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                    // Toasts if email isn't entered
                    if (emailString == null) {

                        Context context = getApplicationContext();
                        CharSequence text = "Please Enter Email First";
                        int duration = Toast.LENGTH_LONG;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        return;
                    }
                    // Toasts if password isn't entered
                    if (passwordString == null) {

                        Context context = getApplicationContext();
                        CharSequence text = "Please Enter Password First";
                        int duration = Toast.LENGTH_LONG;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        return;
                    }
            }
                // Log in. Go to another activity and sends info to server
        });

    }
}
