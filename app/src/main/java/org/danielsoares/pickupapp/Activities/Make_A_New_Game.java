package org.danielsoares.pickupapp.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import org.danielsoares.pickupapp.R;

public class Make_A_New_Game extends AppCompatActivity {

    private Button selectTimeButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make__a__new__game);

        // Initializes Buttons
    }

    private void initialize() {
        selectTimeButton = (Button) findViewById(R.id.SelectTimeButton);
        selectTimeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Creates Dialogue Fragment
                            }
        });
    }


}
