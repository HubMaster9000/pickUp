package org.danielsoares.pickupapp.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import org.danielsoares.pickupapp.GameRecyclerViewAdapter;
import org.danielsoares.pickupapp.IMakeANewGame;
import org.danielsoares.pickupapp.Models.Game;
import org.danielsoares.pickupapp.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AvailableGames extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener, IMakeANewGame, SwipeRefreshLayout.OnRefreshListener {

    private Spinner sportsDropDown;
    //private Spinner distanceDropDown;
    private Spinner sizeDropDown;
    private FloatingActionButton newGameButton;
    private DatabaseReference mDatabase;
    private CollectionReference ref = FirebaseFirestore.getInstance().collection("Games");
    private static final String TAG = "AvailableGames";
    private String selectedSport = null;
    private String selectedDistance = null;
    private int selectedSize = 0;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private CircleImageView profilePicImageView;
    private String userName;
    private Uri profilePic = user.getPhotoUrl();

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    //views
    private View mParentLayout;
    private ArrayList<Game> mGames = new ArrayList<>();
    private GameRecyclerViewAdapter mGameRecyclerViewAdapter;
    private DocumentSnapshot mLastQueriedDocument;


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
                R.array.all_sports, R.layout.spinner_item);
        // Specify the layout to use when the list of choices appears
        sportsAdapter.setDropDownViewResource(R.layout.spinner_item);
        // Apply the adapter to the spinner
        sportsDropDown.setAdapter(sportsAdapter);

        // TODO: Figure out how to find distance
        /**
        //POPULATES DISTANCE SPINNER
        distanceDropDown = findViewById(R.id.distanceSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> distanceAdapter = ArrayAdapter.createFromResource(this,
                R.array.all_distances, R.layout.spinner_item);
        // Specify the layout to use when the list of choices appears
        distanceAdapter.setDropDownViewResource(R.layout.spinner_item);
        // Apply the adapter to the spinner
        distanceDropDown.setAdapter(distanceAdapter);
         */


        //POPULATES SIZE SPINNER
        sizeDropDown = findViewById(R.id.sizeSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> sizeAdapter = ArrayAdapter.createFromResource(this,
                R.array.all_sizes, R.layout.spinner_item);
        // Specify the layout to use when the list of choices appears
        sizeAdapter.setDropDownViewResource(R.layout.spinner_item);
        // Apply the adapter to the spinner
        sizeDropDown.setAdapter(sizeAdapter);


        //FIND NEW GAME BUTTON
        newGameButton = findViewById(R.id.newGameButton);


        //FIND PROFILE PIC ICON
        profilePicImageView = (CircleImageView) findViewById(R.id.account_Image);

        userName = user.getDisplayName();
        if (userName.length() < 1) {
            Intent editProfile = new Intent(getApplicationContext(), EditProfile.class);
            startActivity(editProfile);
        }

        if (profilePic != null) {
            Picasso.get().load(profilePic).into(profilePicImageView);
        }

        //FIND VIEWS
        mParentLayout = findViewById(android.R.id.content);
        mRecyclerView = findViewById(R.id.recycler_view);
        mSwipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        initRecyclerView();
    }


    /**
     * Initializes listeners
     */
    private void initListeners() {
        // Sports Spinner
        sportsDropDown.setOnItemSelectedListener(this);
        // Distance Spinner
        // TODO: Figure out how to find distance
        //distanceDropDown.setOnItemSelectedListener(this);
        // Size Spinner
        sizeDropDown.setOnItemSelectedListener(this);
        // New Game
        newGameButton.setOnClickListener(this);
        // Profile Pic
        profilePicImageView.setOnClickListener(this);

    }

    @Override
    public void onRefresh() {
        getGames();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    //POPULATE RECYCLER VIEW WITH GAMES
    private void getGames() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        //Initial condition, no spinners selected
        if (selectedSport == "All Sports" && selectedSize == 100) {
            CollectionReference gamesCollectionRef = db.collection("games");

            Query allGameQuery = null;
            if (mLastQueriedDocument != null) {
                //repopulates on refresh
                allGameQuery = gamesCollectionRef
                        .orderBy("startTimeHour", Query.Direction.ASCENDING)
                        //.orderBy("startTimeMinute", Query.Direction.ASCENDING)
                        .startAfter(mLastQueriedDocument);
            } else {
                //initial population
                allGameQuery = gamesCollectionRef
                        //.orderBy("startTimeHour", Query.Direction.ASCENDING)
                        .orderBy("startTimeMinute", Query.Direction.ASCENDING);
            }
            allGameQuery.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Game game = document.toObject(Game.class);
                            mGames.add(game);
                        }

                        if (task.getResult().size() != 0) {
                            mLastQueriedDocument = task.getResult().getDocuments().get(task.getResult().size() - 1);
                        }
                        mGameRecyclerViewAdapter.notifyDataSetChanged();

                    } else {
                        Toast.makeText(AvailableGames.this, "Query Failed.1",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        //Filters games based off of sport
        else if (selectedSport != "All Sports" && selectedSize == 100) {
            CollectionReference gamesCollectionRef = db.collection("games");

            Query filteredBySportQuery = null;
            if (mLastQueriedDocument != null) {
                //repopulates on refresh
                filteredBySportQuery = gamesCollectionRef
                        .whereEqualTo("sport", selectedSport)
                        .orderBy("startTimeHour", Query.Direction.ASCENDING)
                        .orderBy("startTimeMinute", Query.Direction.ASCENDING)
                        .startAfter(mLastQueriedDocument);
            } else {
                //initial population
                filteredBySportQuery = gamesCollectionRef
                        .whereEqualTo("sport", selectedSport)
                        .orderBy("startTimeHour", Query.Direction.ASCENDING)
                        .orderBy("startTimeMinute", Query.Direction.ASCENDING);
            }
             filteredBySportQuery.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                 @Override
                 public void onComplete(@NonNull Task<QuerySnapshot> task) {
                     if (task.isSuccessful()) {
                         for (QueryDocumentSnapshot document : task.getResult()) {
                             Game game = document.toObject(Game.class);
                             mGames.add(game);
                         }

                         if (task.getResult().size() != 0) {
                             mLastQueriedDocument = task.getResult().getDocuments().get(task.getResult().size() - 1);
                         }
                         mGameRecyclerViewAdapter.notifyDataSetChanged();

                     } else {
                         Toast.makeText(AvailableGames.this, "Query Failed.2",
                                 Toast.LENGTH_SHORT).show();
                     }
                 }
             });
        }

        //Filters games based off of size
        else if (selectedSize != 100 && selectedSport == "All Sports") {
            CollectionReference gamesCollectionRef = db.collection("games");

            Query filteredBySizeQuery = null;
            if (mLastQueriedDocument != null) {
                //repopulates on refresh
                filteredBySizeQuery = gamesCollectionRef
                        .whereEqualTo("maxSize", selectedSize)
                        .orderBy("startTimeHour", Query.Direction.ASCENDING)
                        .orderBy("startTimeMinute", Query.Direction.ASCENDING)
                        .startAfter(mLastQueriedDocument);
            } else {
                //initial population
                filteredBySizeQuery = gamesCollectionRef
                        .whereEqualTo("maxSize", selectedSize)
                        .orderBy("startTimeHour", Query.Direction.ASCENDING)
                        .orderBy("startTimeMinute", Query.Direction.ASCENDING);
            }
            filteredBySizeQuery.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Game game = document.toObject(Game.class);
                            mGames.add(game);
                        }

                        if (task.getResult().size() != 0) {
                            mLastQueriedDocument = task.getResult().getDocuments().get(task.getResult().size() - 1);
                        }
                        mGameRecyclerViewAdapter.notifyDataSetChanged();

                    } else {
                        Toast.makeText(AvailableGames.this, "Query Failed.3",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        //Filters games based off of sport and size
        else if (selectedSize != 100 && selectedSport != "All Sports") {
            CollectionReference gamesCollectionRef = db.collection("games");

            Query filteredByBothQuery = null;
            if (mLastQueriedDocument != null) {
                //repopulates on refresh
                filteredByBothQuery = gamesCollectionRef
                        .whereEqualTo("sport", selectedSport)
                        .whereEqualTo("maxSize", selectedSize)
                        .orderBy("startTimeHour", Query.Direction.ASCENDING)
                        .orderBy("startTimeMinute", Query.Direction.ASCENDING)
                        .startAfter(mLastQueriedDocument);
            } else {
                //initial population
                filteredByBothQuery = gamesCollectionRef
                        .whereEqualTo("sport", selectedSport)
                        .whereEqualTo("maxSize", selectedSize)
                        .orderBy("startTimeHour", Query.Direction.ASCENDING)
                        .orderBy("startTimeMinute", Query.Direction.ASCENDING);
            }
            filteredByBothQuery.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Game game = document.toObject(Game.class);
                            mGames.add(game);
                        }

                        if (task.getResult().size() != 0) {
                            mLastQueriedDocument = task.getResult().getDocuments().get(task.getResult().size() - 1);
                        }
                        mGameRecyclerViewAdapter.notifyDataSetChanged();

                    } else {
                        Toast.makeText(AvailableGames.this, "Query Failed.4",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    //Displays games
    private void initRecyclerView(){
        if(mGameRecyclerViewAdapter == null){
            mGameRecyclerViewAdapter = new GameRecyclerViewAdapter(this, mGames);
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mGameRecyclerViewAdapter);
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
                makeNewGameIntent.putExtra("user", user);
                startActivity(makeNewGameIntent);
                break;
            case R.id.account_Image:
                // Go to Settings
                Intent goToProfileIntent = new Intent(getApplicationContext(), ProfilePage.class);
                goToProfileIntent.putExtra("user", user);
                startActivity(goToProfileIntent);
                break;

        }
    }

    /**
     * Listens to the selection of spinners
     */
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch(adapterView.getId()){
            case R.id.sportsSpinner:
                // Filter By Sport
                selectedSport =  sportsDropDown.getSelectedItem().toString();
                mGames.clear();
                mGameRecyclerViewAdapter.notifyDataSetChanged();
                getGames();
                break;
            // TODO: Figure out how to find distance
            /**
             case R.id.distanceSpinner:
             // Filter By Distance
             selectedDistance = distanceDropDown.getSelectedItem().toString();
             filterGames();
             break;
             */
            case R.id.sizeSpinner:
                // Filter By Size
                String selected = sizeDropDown.getSelectedItem().toString();
                selectedSize = Integer.parseInt(selected);
                mGames.clear();
                mGameRecyclerViewAdapter.notifyDataSetChanged();
                getGames();
                break;
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
    }

    @Override
    public void createNewGame(String sport, String host, int startTimeHour, int startTimeMinute, int endTimeHour, int endTimeMinute, int maxSize,  String location) {
    }


}