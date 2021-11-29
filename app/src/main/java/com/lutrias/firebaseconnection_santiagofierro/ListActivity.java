package com.lutrias.firebaseconnection_santiagofierro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class ListActivity extends AppCompatActivity {

    private ListView listView;
    private Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        loadComponents();
        assignButtons();
    }

    private void loadComponents() {
        listView = findViewById(R.id.listView);

        btnDelete = findViewById(R.id.btnDelete);
    }

    private void assignButtons() {
        btnDelete.setOnClickListener(this::delete);
    }

    private void delete(View view) {

    }
}