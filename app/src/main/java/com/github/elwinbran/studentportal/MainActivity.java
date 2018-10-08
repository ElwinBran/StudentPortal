package com.github.elwinbran.studentportal;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final static Integer INTENT_CODE = 22;

    final private List<Portal> portalList = new ArrayList<>();
    private RecyclerView.Adapter gridViewArrayAdapter;
    private RecyclerView portalGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        List<String> portalTitleList = null;
        setContentView(R.layout.activity_main);
        this.gridViewArrayAdapter = new PortalGridAdapter(portalList, this);
        this.portalGrid = findViewById(R.id.portalGrid);
        portalGrid.setLayoutManager(new StaggeredGridLayoutManager(3,
                LinearLayoutManager.VERTICAL));
        //portalList.add("HVA");
        //portalList.add("Bones");
        //portalList.add("SO");
        //portalList.add("VLO");
        // Create a new ArrayAdapter
        portalGrid.setAdapter(gridViewArrayAdapter);
        final FloatingActionButton addPortalButton = (FloatingActionButton) findViewById(R.id.addPortalButton);
        addPortalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addingPortal = new Intent(MainActivity.this, AddActivity.class);
                startActivityForResult(addingPortal, INTENT_CODE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        //Check if the result code is the right one
        if (resultCode == Activity.RESULT_OK)
        {
            //Check if the request code is correct
            if (requestCode == INTENT_CODE)
            {
                final String title = data.getStringExtra(Portal.TITLE_IDENTIFIER);
                final String url = data.getStringExtra(Portal.URL_IDENTIFIER);
                portalList.add(new Portal() {
                    @Override
                    public String getTitle() {
                        return title;
                    }

                    @Override
                    public String getUrl() {
                        return url;
                    }
                });
                this.gridViewArrayAdapter.notifyDataSetChanged();
            }
        }
    }

    public void browseUrl(String url)
    {
        Intent browse = new Intent(MainActivity.this, PortalActivity.class);
        browse.putExtra("url", url);
        startActivityForResult(browse, 7);
    }
}
