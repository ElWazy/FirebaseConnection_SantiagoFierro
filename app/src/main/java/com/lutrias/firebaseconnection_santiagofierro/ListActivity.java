package com.lutrias.firebaseconnection_santiagofierro;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lutrias.firebaseconnection_santiagofierro.model.Professional;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private ListView listView;
    private Button btnDelete;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    private List<Professional> professionals;
    private Professional selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        initDatabase();

        loadComponents();
        assignButtons();
        fillListView();
    }

    private void initDatabase() {
        FirebaseApp.initializeApp(this);
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
    }

    private void loadComponents() {
        listView = findViewById(R.id.listView);
        btnDelete = findViewById(R.id.btnDelete);
    }

    private void assignButtons() {
        listView.setOnItemClickListener(this::selectProfessional);
        btnDelete.setOnClickListener(this::delete);
    }

    private void fillListView() {
        professionals = new ArrayList<>();
        databaseReference.child("Professional").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                professionals.clear();

                for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
                    Professional professional = dataSnapshot.getValue(Professional.class);

                    professionals.add(professional);

                    ArrayAdapter adapter = new ArrayAdapter(
                            getBaseContext(),
                            android.R.layout.simple_list_item_1,
                            professionals
                    );
                    listView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void selectProfessional(AdapterView<?> adapterView, View view, int i, long l) {
        selected = (Professional) adapterView.getItemAtPosition(i);
    }

    private void delete(View view) {
        Professional professional = new Professional();

        professional.setId(selected.getId());

        databaseReference
                .child("Professional")
                .child(professional.getId())
                .removeValue();

        Toast.makeText(getBaseContext(), "Has eliminado un profesional", Toast.LENGTH_SHORT).show();
    }
}