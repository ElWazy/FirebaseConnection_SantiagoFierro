package com.lutrias.firebaseconnection_santiagofierro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView txtName, txtSalary, txtProfession;
    private Button btnSave, btnGoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadComponents();
        assignButtons();
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

    }

    private void goList(View view) {

    }
}