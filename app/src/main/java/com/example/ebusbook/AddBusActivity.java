package com.example.ebusbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class AddBusActivity extends AppCompatActivity {

    private DatabaseReference busDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bus);

        EditText busid = findViewById(R.id.id);
        EditText from = findViewById(R.id.from);
        EditText to = findViewById(R.id.to);
        EditText dt = findViewById(R.id.date);
        EditText seats = findViewById(R.id.seats);
        Button addbus = findViewById(R.id.addbus);
        Button back1 =findViewById(R.id.button2);


        busDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Bus");


        addbus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = busid.getText().toString();
                String departure = from.getText().toString();
                String arrival = to.getText().toString();
                String date = dt.getText().toString();
                String total_seats = seats.getText().toString();

                if (id.equals("") || departure.equals("") || arrival.equals("") || date.equals("") || total_seats.equals(""))
                {
                    Toast.makeText(AddBusActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    busDatabaseReference.child(id).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()) {
                                Toast.makeText(AddBusActivity.this, "ID already exists", Toast.LENGTH_SHORT).show();
                            } else {
                                HashMap busMap = new HashMap();
                                busMap.put("busId",id);
                                busMap.put("departure",departure);
                                busMap.put("arrival",arrival);
                                busMap.put("date",date);
                                busMap.put("seats",total_seats);

                                busDatabaseReference.child(id).updateChildren(busMap).addOnCompleteListener(new OnCompleteListener() {
                                    @Override
                                    public void onComplete(@NonNull Task task) {
                                        if(task.isSuccessful())
                                        {
                                            Toast.makeText(AddBusActivity.this, "Bus has been added", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(getApplicationContext(), AdminPanelActivity.class);
                                            startActivity(intent);
                                        }
                                        else
                                        {
                                            String msg = task.getException().getMessage();
                                            Toast.makeText(AddBusActivity.this, msg, Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });


        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AdminPanelActivity.class);
                startActivity(intent);
            }
        });

    }
}