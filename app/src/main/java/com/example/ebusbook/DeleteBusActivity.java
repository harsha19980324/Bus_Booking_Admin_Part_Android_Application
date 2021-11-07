package com.example.ebusbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DeleteBusActivity extends AppCompatActivity {

    EditText busid;

    private DatabaseReference busDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_bus);

        busid = findViewById(R.id.busid);
        Button deletebus = findViewById(R.id.deletebus);
        Button back = findViewById(R.id.button03);

        busDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Bus");

        deletebus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = busid.getText().toString();
                if (id.equals(""))
                {
                    Toast.makeText(DeleteBusActivity.this, "Please Enter Bus ID", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    busDatabaseReference.child(id).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()) {


                                DeleteBus();
                            } else {
                                Toast.makeText(DeleteBusActivity.this, "ID doesn't exists", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AdminPanelActivity.class);
                startActivity(intent);
            }
        });

    }

    private void DeleteBus() {
        String id = busid.getText().toString();
        busDatabaseReference.child(id).removeValue();
    }
}