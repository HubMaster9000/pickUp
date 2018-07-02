package org.danielsoares.pickupapp.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.danielsoares.pickupapp.R;

public class Profile_Page extends AppCompatActivity {

    private ImageView editInfoButton = findViewById(R.id.edit_info_button);
    private TextView editInfoText = findViewById(R.id.edit_info_text);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        // Intent intent = getIntent();
        // String value = intent.getStringExtra("key"); //if it's a string you stored.

        editInfoButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile_Page.this, Edit_Profile.class);
                // intent.putExtra("key",value);
                Profile_Page.this.startActivity(intent);
            }
        });

        editInfoText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile_Page.this, Edit_Profile.class);
                Profile_Page.this.startActivity(intent);
            }
        });

        //Access Firebase user
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();

            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            String uid = user.getUid();
        }
    }

}
