package org.danielsoares.pickupapp.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import org.danielsoares.pickupapp.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfilePage extends AppCompatActivity implements View.OnClickListener {

    private Button editInfoButton;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private String email = user.getEmail();
    private String displayName = user.getDisplayName();
    private CircleImageView profilePicImageView;
    private TextView emailTextView;
    private TextView displayNameTextView;
    private Uri profilePic = user.getPhotoUrl();
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        initViews();
        initListeners();
    }

    private void initViews() {
        editInfoButton = findViewById(R.id.edit_info_button);
        emailTextView = findViewById(R.id.email);
        displayNameTextView = findViewById(R.id.displayName);
        emailTextView.setText(email);
        displayNameTextView.setText(displayName);
        backButton = findViewById(R.id.back_button);
        profilePicImageView = (CircleImageView) findViewById(R.id.imageView_account_profile);

        if (profilePic != null) {
            Picasso.get().load(profilePic).into(profilePicImageView);
        }

    }

    private void initListeners() {

        editInfoButton.setOnClickListener(this);
        backButton.setOnClickListener(this);

    }

    /**
     * Listens the click on view
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edit_info_button:
                // Go to Settings
                Intent editProfileIntent = new Intent(getApplicationContext(), EditProfile.class);
                startActivity(editProfileIntent);
                break;
            case R.id.back_button:
                // Go to Available Games
                Intent availableGamesIntent = new Intent(getApplicationContext(), AvailableGames.class);
                startActivity(availableGamesIntent);
                break;

        }
    }

}
