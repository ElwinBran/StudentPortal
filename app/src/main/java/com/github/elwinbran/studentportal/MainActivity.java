package com.github.elwinbran.studentportal;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridView portalGrid = findViewById(R.id.portalGrid);
        portalGrid.setNumColumns(3);
        final List<String> portalNames = new ArrayList<>();
        portalNames.add("HVA");
        portalNames.add("Bones");
        portalNames.add("SO");
        portalNames.add("VLO");
        // Create a new ArrayAdapter
        final ArrayAdapter<String> gridViewArrayAdapter = new ArrayAdapter<String>
                (this,android.R.layout.simple_list_item_1, portalNames);
        portalGrid.setAdapter(gridViewArrayAdapter);
        final FloatingActionButton addPortalButton = (FloatingActionButton) findViewById(R.id.addPortalButton);
        addPortalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addingPortal = new Intent(MainActivity.this, AddActivity.class);
                startActivity(addingPortal);
            }
        });
    }
}
