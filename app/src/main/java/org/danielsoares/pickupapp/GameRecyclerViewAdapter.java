package org.danielsoares.pickupapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.danielsoares.pickupapp.Models.Game;

import java.util.ArrayList;

public class GameRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "GameRecyclerViewAdapter";

    private ArrayList<Game> mGames = new ArrayList<>();
    private IMakeANewGame mIMakeANewGame;
    private Context mContext;
    private int mSelectedGameIndex;

    public GameRecyclerViewAdapter(Context context, ArrayList<Game> games) {
        mGames = games;
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.layout_game_list_item, parent, false);

        holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof ViewHolder){
            ((ViewHolder)holder).sport.setText(mGames.get(position).getSport());

            int startHour = mGames.get(position).getStartTimeHour();
            int startMinute = mGames.get(position).getStartTimeMinute();
            ((ViewHolder)holder).time.setText(startHour + " : " + startMinute);

            String location = mGames.get(position).getLocation();
            ((ViewHolder)holder).location.setText("@ " + location);
        }
    }

    @Override
    public int getItemCount() {
        return mGames.size();
    }


    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mIMakeANewGame = (IMakeANewGame) mContext;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView sport, location, time;

        public ViewHolder(View itemView) {
            super(itemView);
            sport = itemView.findViewById(R.id.sport);
            location = itemView.findViewById(R.id.location);
            time = itemView.findViewById(R.id.time);

        }


    }
}