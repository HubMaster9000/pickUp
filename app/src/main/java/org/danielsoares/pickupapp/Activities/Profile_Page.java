package org.danielsoares.pickupapp;

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

        initViews();
        // initListeners();
        // initObjects();

        editInfoButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile_Page.this, Edit_Profile.class);
                startActivity(intent);
            }
        });

        editInfoText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile_Page.this, Edit_Profile.class);
                startActivity(intent);
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    /**
     * Iinitializes views
     */
    private void initViews() {
        editInfoButton = findViewById(R.id.edit_info_button);
        editInfoText = findViewById(R.id.edit_info_text);
    }

    /**
     * Initializes listeners
     */
    /* private void initListeners() {
        // Waits for editInfo button to be clicked
        editInfoButton.setOnClickListener(this);
        // Waits for editInfo text to be clicked
        editInfoText.setOnClickListener(this);
    } */

    /**
     * Initialize objects to be used
     */
    /* private void initObjects() {
        editInfoButton = new EditInfoButton(activity);
        editInfoText = new InputValidation(activity);

    } */

}
