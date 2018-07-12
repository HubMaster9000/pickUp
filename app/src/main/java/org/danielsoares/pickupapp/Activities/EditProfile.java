package org.danielsoares.pickupapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import org.danielsoares.pickupapp.R;

public class EditProfile extends AppCompatActivity {

    private Button saveEditButton = findViewById(R.id.save_edit_button);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        saveEditButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditProfile.this, ProfilePage.class);
                // intent.putExtra("key",value);
                EditProfile.this.startActivity(intent);
            }
        });
    }
}
