package org.danielsoares.pickupapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.danielsoares.pickupapp.R;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private TextView TextViewLoginLink;

    private FirebaseAuth mAuth;
    private EditText mEmailField;
    private EditText mPasswordField;
    private static final String TAG = "SignUp";
    private boolean Created = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();

        initViews();
        initListeners();

    }

    /**
     * Initializes views
     */
    private void initViews() {

        TextViewLoginLink = findViewById(R.id.TextViewLoginLink);
        mEmailField = findViewById(R.id.field_email);
        mPasswordField = findViewById(R.id.field_password);

    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        TextViewLoginLink.setOnClickListener(this);
        // Buttons
        findViewById(R.id.email_create_account_button).setOnClickListener(this);
    }


    /**
     * This implemented method is to listen the click on view
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // Sign Up
            case R.id.email_create_account_button:
                createAccount(mEmailField.getText().toString(), mPasswordField.getText().toString());
                break;
            // Directs you to login page
            case R.id.TextViewLoginLink:
                // Navigate to LoginActivity
                Intent goToLoginIntent = new Intent(getApplicationContext(), Login.class);
                startActivity(goToLoginIntent);
                break;
        }
    }


    /**
     * This method creates a new user with their email and password and sends an Email Verification
     */
    private void createAccount(String email, String password) {
        Log.d(TAG, "createAccount:" + email);
        if (!validateForm()) {
            return;
        }

        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // User creation success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            sendEmail();
                        } else {
                            // If creation fails, display a message to the user.
                            Toast.makeText(SignUp.this,
                                    "User Creation failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        // [END create_user_with_email]

    }


    // Send verification email
    private void sendEmail() {
        // [START send_email_verification]
        final FirebaseUser user = mAuth.getCurrentUser();
        user.sendEmailVerification()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // [START_EXCLUDE]

                        if (task.isSuccessful()) {
                            Toast.makeText(SignUp.this,
                                    "Verification email sent to " + user.getEmail(),
                                    Toast.LENGTH_SHORT).show();
                            // Sign up success, go to Available Games
                            Intent goToGamesIntent = new Intent(getApplicationContext(), AvailableGames.class);
                            startActivity(goToGamesIntent);
                            finish();
                        } else {
                            Log.e(TAG, "sendEmailVerification", task.getException());
                            Toast.makeText(SignUp.this,
                                    "Failed to send verification email.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        // [END_EXCLUDE]
                    }
                });
        // [END send_email_verification]
    }


    /**
     * Validate form
     */
    private boolean validateForm() {
        boolean valid = true;

        String email = mEmailField.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mEmailField.setError("Required.");
            valid = false;
        } else {
            mEmailField.setError(null);
        }

        String password = mPasswordField.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mPasswordField.setError("Required.");
            valid = false;
        } else if (password.length() < 6){
            mPasswordField.setError("Password must be at least 6 characters.");
            valid = false;
        }
        else {
            mPasswordField.setError(null);
        }

        return valid;
    }

}