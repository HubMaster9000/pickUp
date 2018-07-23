package org.danielsoares.pickupapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.danielsoares.pickupapp.R;

import javax.annotation.Nullable;


public class AvailableGames extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private Spinner sportsDropDown;
    private Spinner distanceDropDown;
    private Spinner sizeDropDown;
    private FloatingActionButton newGameButton;
    private DatabaseReference mDatabase;
    private CollectionReference ref = FirebaseFirestore.getInstance().collection("Games");
    private static final String TAG = "AvailableGames";
    private String selectedSport = null;
    private String selectedDistance = null;
    private int selectedSize = 0;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_games);

        initViews();
        initListeners();
        getGames();
    }


    /**
     * Initializes views
     */
    private void initViews() {
        //POPULATES SPORTS SPINNER
        sportsDropDown = findViewById(R.id.sportsSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> sportsAdapter = ArrayAdapter.createFromResource(this,
                R.array.all_sports, android.R.layout.spinner_item);
        // Specify the layout to use when the list of choices appears
        sportsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        sportsDropDown.setAdapter(sportsAdapter);


        //POPULATES DISTANCE SPINNER
        distanceDropDown = findViewById(R.id.distanceSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> distanceAdapter = ArrayAdapter.createFromResource(this,
                R.array.all_distances, android.R.layout.spinner_item);
        // Specify the layout to use when the list of choices appears
        distanceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        distanceDropDown.setAdapter(distanceAdapter);


        //POPULATES SIZE SPINNER
        sizeDropDown = findViewById(R.id.sizeSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> sizeAdapter = ArrayAdapter.createFromResource(this,
                R.array.all_sizes, android.R.layout.spinner_item);
        // Specify the layout to use when the list of choices appears
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        sizeDropDown.setAdapter(sizeAdapter);


        //FIND NEW GAME BUTTON
        newGameButton = findViewById(R.id.newGameButton);

        //FIND LIST VIEW
        listView = findViewById(R.id.listView);
        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, myStringArray);

    }


    /**
     * Initializes listeners
     */
    private void initListeners() {
        // Sports Spinner
        sportsDropDown.setOnItemSelectedListener(this);
        // Distance Spinner
        distanceDropDown.setOnItemSelectedListener(this);
        // Size Spinner
        sizeDropDown.setOnItemSelectedListener(this);
        // New Game
        newGameButton.setOnClickListener(this);

    }


    /**
     * Listens to the click on view
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.newGameButton:
                // Make a new Game
                Intent makeNewGameIntent = new Intent(getApplicationContext(), MakeANewGame.class);
                startActivity(makeNewGameIntent);
                break;

        }
    }

    /**
     * Listens to the selection of spinners
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        switch (parent.getId()) {
            case R.id.sportSpinner:
                // Filter By Sport
                selectedSport = sportsDropDown.getSelectedItem().toString();
                filterGames();
                break;
            case R.id.distanceSpinner:
                // Filter By Distance
                selectedDistance = distanceDropDown.getSelectedItem().toString();
                filterGames();
                break;
            case R.id.sizeSpinner:
                // Filter By Size
                selectedSize = Integer.parseInt(sizeDropDown.getSelectedItem().toString());
                filterGames();
                break;

        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        getGames();
    }

    private void getGames() {
        ref
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    private void filterGames() {
        if (selectedSport != null) {
            if (selectedDistance != null && selectedSize != 0) {
                filterByAll(selectedSport, selectedDistance, selectedSize);
            } else if (selectedDistance != null) {
                filterBySportAndDistance(selectedSport, selectedDistance);
            } else if (selectedSize != 0) {
                filterBySportAndSize(selectedSport, selectedSize);
            } else {
                filterBySport(selectedSport);
            }

        } else if (selectedDistance != null) {
            if (selectedSize != 0) {
                filterByDistanceAndSize(selectedDistance, selectedSize);
            } else {
                filterByDistance(selectedDistance);
            }
        } else {
            filterBySize(selectedSize);
        }

    }

    private void filterByAll(String selectedSport, String selectedDistance, int selectedSize) {
        Query allQuery = ref.whereEqualTo("sport", selectedSport).whereEqualTo("distance", selectedDistance).whereEqualTo("size", selectedSize);
    }

    private void filterBySportAndDistance(String selectedSport, String selectedDistance) {
        Query sportDistanceQuery = ref.whereEqualTo("sport", selectedSport).whereEqualTo("distance", selectedDistance);
    }

    private void filterBySportAndSize(String selectedSport, int selectedSize) {
        Query sportSizeQuery = ref.whereEqualTo("sport", selectedSport).whereEqualTo("size", selectedSize);
    }

    private void filterBySport(String selectedSport) {
        Query sportQuery = ref.whereEqualTo("sport", selectedSport);
    }

    private void filterByDistanceAndSize(String selectedDistance, int selectedSize) {
        Query distanceSizeQuery = ref.whereEqualTo("distance", selectedDistance).whereEqualTo("size", selectedSize);
    }

    private void filterByDistance(String selectedDistance) {
        Query distanceQuery = ref.whereEqualTo("distance", selectedDistance);
    }

    private void filterBySize(int selectedSize) {
        Query sizeQuery = ref.whereEqualTo("size", selectedSize);
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