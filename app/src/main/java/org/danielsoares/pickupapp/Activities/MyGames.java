package org.danielsoares.pickupapp.Activities;

        import android.os.Bundle;
        import android.support.design.widget.FloatingActionButton;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.Spinner;

        import com.google.firebase.auth.AuthResult;
        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.auth.FirebaseUser;
        import com.google.firebase.database.DatabaseReference;

        import org.danielsoares.pickupapp.R;


public class MyGames extends AppCompatActivity implements View.OnClickListener {

    private Spinner sportsDropDown;
    private Spinner distanceDropDown;
    private Spinner sizeDropDown;
    private FloatingActionButton newGameButton;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_games);

        initViews();
        initListeners();
    }


    /**
     * Initializes views
     */
    private void initViews() {

       // sportsDropDown = findViewById(R.id.sportSpinner);
       // distanceDropDown = findViewById(R.id.distanceSpinner);
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
       // distanceDropDown.setOnClickListener(this);
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
          //  case R.id.sportSpinner:
                //Something with the spinner
         //       break;
            // Distance Spinner
        //    case R.id.distanceSpinner:
                //Something with the spinner
         //       break;
            // Size Spinner
        //    case R.id.sizeSpinner:
                //Something with the spinner
         //       break;
            // New Game
            case R.id.newGameButton:
                // Make a new Game
                break;

        }
    }

}