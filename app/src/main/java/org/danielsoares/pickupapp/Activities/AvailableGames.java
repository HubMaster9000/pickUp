package org.danielsoares.pickupapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseReference;

import org.danielsoares.pickupapp.R;


public class AvailableGames extends AppCompatActivity implements View.OnClickListener {

    private Spinner sportsDropDown;
    private Spinner distanceDropDown;
    private Spinner sizeDropDown;
    private FloatingActionButton newGameButton;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_games);

        initViews();
        initListeners();
    }


    /**
     * Initializes views
     */
    private void initViews() {

      //  sportsDropDown = findViewById(R.id.sportsSpinner);
      //  distanceDropDown = findViewById(R.id.distanceSpinner);
      //  sizeDropDown = findViewById(R.id.sizeSpinner);
        newGameButton = findViewById(R.id.newGameButton);

    }


    /**
     * Initializes listeners
     */
    private void initListeners() {
        // Sports Spinner
       // sportsDropDown.setOnClickListener(this);
        // Distance Spinner
      //  distanceDropDown.setOnClickListener(this);
        // Size Spinner
      //  sizeDropDown.setOnClickListener(this);
        // New Game
        newGameButton.setOnClickListener(this);

    }


    /**
     * Listens the click on view
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // Sport Spinner
          //  case R.id.sportsSpinner:
                //Something with the spinner
          //      break;
            // Distance Spinner
          //  case R.id.distanceSpinner:
                //Something with the spinner
         //       break;
            // Size Spinner
         //   case R.id.sizeSpinner:
                //Something with the spinner
          //      break;
            // New Game
            case R.id.newGameButton:
                // Make a new Game
                break;

        }
    }

    public static class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

        private GoogleMap mMap;
        private Button submitButton;
        private Marker marker;
        private LatLng location;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_maps);

            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);

            initial();
        }

        private void initial() {
            submitButton = findViewById(R.id.submitButton);
            submitButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (marker == null) {
                        Toast.makeText(MapsActivity.this,
                                "Select a location first", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Intent sendLocation = new Intent(getApplicationContext(), MakeANewGame.class);
                        sendLocation.putExtra("Location", location);
                        startActivity(sendLocation);
                    }
                }
            });
        }

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera. In this case,
         * we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to install
         * it inside the SupportMapFragment. This method will only be triggered once the user has
         * installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;

            // Adds marker at touched point
            mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng point) {
                    marker = mMap.addMarker(new MarkerOptions()
                            .position(point)
                            .title("New Game"));
                    marker.setVisible(true);
                    location = point;
                }
            });
        }


    }
}