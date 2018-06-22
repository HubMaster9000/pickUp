package org.danielsoares.pickupapp.Activities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.text.format.DateFormat;
import org.danielsoares.pickupapp.R;
import java.util.Calendar;

public class Time_Pick extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{

    Button pickTimeButton;
    TextView timeResult;

    int day, month, year, minute, hour;
    int dayFinal, monthFinal, yearFinal, minuteFinal, hourFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timepickdialogue);

        pickTimeButton = (Button) findViewById(R.id.pick_time_button);
        timeResult = (TextView) findViewById(R.id.time_result);

        pickTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                year =  c.get(Calendar.YEAR);
                month =  c.get(Calendar.MONTH);
                day =  c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(Time_Pick.this, Time_Pick.this, year, month, day);
                datePickerDialog.show();
            }
        });
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        yearFinal = i;
        monthFinal = i1 + 1;
        dayFinal = i2;

        Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog  = new TimePickerDialog(Time_Pick.this, Time_Pick.this, hour, minute, DateFormat.is24HourFormat(this));
        timePickerDialog.show();
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        hourFinal = i;
        minuteFinal = i1;

        timeResult.setText("Year: " +  yearFinal + "\n" +
                            "Month: " + monthFinal + "\n" +
                            "Day: " + dayFinal + "\n" +
                            "Hour: " + hourFinal + "\n" +
                            "Minute: " + minuteFinal);
    }
}
