package org.danielsoares.pickupapp.Classes_Non_Activities;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.content.Intent;

import org.danielsoares.pickupapp.Activities.Login;

public class ProgressBarAnimation extends Animation {

    private Context context;
    private ProgressBar progressBar;
    private TextView textView;
    private float from;
    private float to;

    public ProgressBarAnimation(Context context, ProgressBar progressBar, TextView textView, float from, float to) {
        this.context = context;
        this.progressBar = progressBar;
        this.textView = textView;
        this.from = from;
        this.to = to;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        float value = from - (from - to) * interpolatedTime;
        progressBar.setProgress((int)value);
        textView.setText((int)value+" %");

        if(value == to) {
            context.startActivity(new Intent(context, Login.class));
        }
    }

}