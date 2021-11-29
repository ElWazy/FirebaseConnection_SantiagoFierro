package com.lutrias.firebaseconnection_santiagofierro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lutrias.firebaseconnection_santiagofierro.model.Professional;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private TextView txtName, txtSalary, txtProfession;
    private Button btnSave, btnGoList;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDatabase();

        loadComponents();
        assignButtons();
    }

    private void initDatabase() {
        FirebaseApp.initializeApp(this);
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
    }

    private void loadComponents() {
        txtName = findViewById(R.id.txtName);
        txtSalary = findViewById(R.id.txtSalary);
        txtProfession = findViewById(R.id.txtProfession);

        btnSave = findViewById(R.id.btnSave);
        btnGoList = findViewById(R.id.btnGoList);
    }

    private void assignButtons() {
        btnSave.setOnClickListener(this::save);
        btnGoList.setOnClickListener(this::goList);
    }

    private void save(View view) {
        Professional professional = new Professional();

        professional.setId(UUID.randomUUID().toString());
        professional.setName(txtName.getText().toString());
        professional.setSalary(txtSalary.getText().toString());
        professional.setProfession(txtProfession.getText().toString());

        databaseReference
                .child("Professional")
                .child(professional.getId())
                .setValue(professional);

        Toast.makeText(getBaseContext(), "Has añadido un profesional", Toast.LENGTH_SHORT).show();
    }

    private void goList(View view) {
        Intent intent = new Intent(getBaseContext(), ListActivity.class);
        startActivity(intent);
    }
}