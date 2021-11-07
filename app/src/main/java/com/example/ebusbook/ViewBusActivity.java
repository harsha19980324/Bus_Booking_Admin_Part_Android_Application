package com.example.ebusbook;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ViewBusActivity extends AppCompatActivity {

    private DatabaseReference busDatabaseReference;

    private RecyclerView busList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bus);

        busDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Bus");

        busList = findViewById(R.id.bus_list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        busList.setLayoutManager(linearLayoutManager);

        LoadBus();
    }

    private void LoadBus() {

        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<BusModel>()
                .setQuery(busDatabaseReference, BusModel.class)
                .build();

        FirebaseRecyclerAdapter<BusModel, BusViewHolder> adapter = new FirebaseRecyclerAdapter<BusModel, BusViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull BusViewHolder busViewHolder, int i, @NonNull BusModel busModel) {

                busViewHolder.id.setText("ID : "+busModel.getBusId());
                busViewHolder.departure.setText("Departure : "+busModel.getDeparture());
                busViewHolder.arrival.setText("Arrival : "+busModel.getArrival());
                busViewHolder.date.setText("Date : "+busModel.getDate());
                busViewHolder.seats.setText("Total Seats : "+busModel.getSeats());

            }

            @NonNull
            @Override
            public BusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_bus, parent, false);
                BusViewHolder busViewHolder = new BusViewHolder(view);
                return busViewHolder;
            }
        };

        busList.setAdapter(adapter);
        adapter.startListening();
    }

    public static class BusViewHolder extends RecyclerView.ViewHolder{

        TextView id, departure, arrival, date, seats;

        public BusViewHolder(@NonNull View itemView) {
            super(itemView);

            id =itemView.findViewById(R.id.row_id);
            departure =itemView.findViewById(R.id.row_departure);
            arrival =itemView.findViewById(R.id.row_arrival);
            date =itemView.findViewById(R.id.row_date);
            seats =itemView.findViewById(R.id.row_seats);
        }
    }
}