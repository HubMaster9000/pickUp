package org.danielsoares.pickupapp.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.squareup.picasso.Picasso;

import org.danielsoares.pickupapp.R;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfile extends AppCompatActivity implements View.OnClickListener {

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    private Button confirmEditButton;
    private FloatingActionButton editProfilePic;
    private static final String TAG = "EditProfile";
    private CircleImageView profilePicImageView;
    private Button cancelEditButton;
    private static final int RESULT_LOAD_IMAGE = 1;
    private Uri selectedImage;
    private String email = user.getEmail();
    private String displayName = user.getDisplayName();
    private Uri profilePic = user.getPhotoUrl();
    private EditText displayNameEditText;
    private TextView emailTextView;
    private String displayNameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        initViews();
        initListeners();
    }

    private void initViews() {
        confirmEditButton = findViewById(R.id.confirm_edit_button);
        editProfilePic = findViewById(R.id.floatingActionButton);
        cancelEditButton = findViewById(R.id.cancel_edit_button);

        displayNameEditText = findViewById(R.id.displayName);
        emailTextView = findViewById(R.id.email);
        displayNameEditText.setText(displayName);
        emailTextView.setText(email);
        profilePicImageView = (CircleImageView) findViewById(R.id.imageView_account_profile);
        if (profilePic != null) {
            Picasso.get().load(profilePic).into(profilePicImageView);
        }


    }

    private void initListeners() {
        confirmEditButton.setOnClickListener(this);
        editProfilePic.setOnClickListener(this);
        cancelEditButton.setOnClickListener(this);
    }

    /**
     * Listens the click on view
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.confirm_edit_button:
                displayNameText = displayNameEditText.getText().toString();
                updateProfile();
                break;
            case R.id.cancel_edit_button:
                Intent cancelEdit = new Intent(getApplicationContext(), ProfilePage.class);
                startActivity(cancelEdit);
                finish();
                break;
            case R.id.floatingActionButton:
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
            selectedImage = data.getData();
            profilePicImageView.setImageURI(selectedImage);
            Picasso.get().load(profilePic).into(profilePicImageView);
        }
    }

    private void updateProfile() {
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(displayNameText)
                .setPhotoUri(selectedImage)
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "User profile updated.");
                        }
                    }
                });

        Intent saveEdit = new Intent(getApplicationContext(), ProfilePage.class);
        startActivity(saveEdit);
        finish();
    }
}
