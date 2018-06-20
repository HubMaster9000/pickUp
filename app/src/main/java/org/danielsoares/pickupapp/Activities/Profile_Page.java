package org.danielsoares.pickupapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.danielsoares.pickupapp.Activities.Edit_Profile;

public class Profile_Page extends AppCompatActivity {

    private final AppCompatActivity activity = Profile_Page.this;

    private ImageView editInfoButton;
    private TextView editInfoText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        editInfoButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile_Page.this, Edit_Profile.class);
                intent.putExtra("key",value);
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
    }

}
