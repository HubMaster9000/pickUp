package org.danielsoares.pickupapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView clickThisForNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clickThisForNow = findViewById(R.id.clickThisForNow);

        // Directs you to login page
        clickThisForNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forNowIntent = new Intent(MainActivity.this, Login.class);
                MainActivity.this.startActivity(forNowIntent);
            }

        });
    }
}
