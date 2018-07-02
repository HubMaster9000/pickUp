package org.danielsoares.pickupapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import org.danielsoares.pickupapp.R;

public class Edit_Profile extends AppCompatActivity {

    private Button saveEditButton = findViewById(R.id.save_edit_button);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        saveEditButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Edit_Profile.this, Profile_Page.class);
                // intent.putExtra("key",value);
                Edit_Profile.this.startActivity(intent);
            }
        });
    }
}
