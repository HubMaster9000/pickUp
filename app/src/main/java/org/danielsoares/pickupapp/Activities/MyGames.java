package org.danielsoares.pickupapp.Activities;

        import android.os.Bundle;
        import android.support.annotation.NonNull;
        import android.support.design.widget.FloatingActionButton;
        import android.support.v7.app.AppCompatActivity;
        import android.util.Log;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.ListView;
        import android.widget.Spinner;

        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.Task;
        import com.google.firebase.auth.AuthResult;
        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.auth.FirebaseUser;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.firestore.CollectionReference;
        import com.google.firebase.firestore.FirebaseFirestore;
        import com.google.firebase.firestore.Query;
        import com.google.firebase.firestore.QueryDocumentSnapshot;
        import com.google.firebase.firestore.QuerySnapshot;

        import org.danielsoares.pickupapp.R;


public class MyGames extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner sportsDropDown;
    private FloatingActionButton newGameButton;
    private DatabaseReference mDatabase;
    private String selectedSport = null;
    private CollectionReference ref = FirebaseFirestore.getInstance().collection("MyGames");
    private static final String TAG = "MyGames";
    private ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_games);

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

        //FIND LIST VIEW
        listView = findViewById(R.id.listView);
        /*ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, myStringArray);
        listView.setAdapter(listViewAdapter);*/

    }


    /**
     * Initializes listeners
     */
    private void initListeners() {
        // Sports Spinner
        sportsDropDown.setOnItemSelectedListener(this);
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
        Query sportQuery = ref.whereEqualTo("sport", selectedSport);
    }

}