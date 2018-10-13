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
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private final static Integer INTENT_CODE = 22;

    final private List<Portal> portalList = new ArrayList<>();
    private RecyclerView.Adapter gridViewArrayAdapter;
    private RecyclerView portalGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            addPortals(readSetttings());
        }
        catch(Exception ex)
        {
            Toast.makeText(this, "Portals could not be loaded.", Toast.LENGTH_SHORT).show();
        }

        this.gridViewArrayAdapter = new PortalGridAdapter(portalList, this);
        this.portalGrid = findViewById(R.id.portalGrid);
        portalGrid.setLayoutManager(new StaggeredGridLayoutManager(3,
                LinearLayoutManager.VERTICAL));
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
        this.gridViewArrayAdapter.notifyDataSetChanged();
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
                writeSettings(convert());
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

    private void addPortals(Map<String, String> portals)
    {
        for(String key : portals.keySet())
        {
            final String title = key;
            final String url = portals.get(key);
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
        }
    }

    private Map<String, String> convert()
    {
        Map<String, String> newPortalMap = new HashMap<>();
        for(Portal portal : this.portalList)
        {
            newPortalMap.put(portal.getTitle(), portal.getUrl());
        }
        return newPortalMap;
    }

    private String subFolder = "/userdata";
    private String file = "portals.ser";

    private void writeSettings(Map<String, String> userSettings)
    {
        File cacheDir = null;
        File appDirectory = null;

        if (android.os.Environment.getExternalStorageState().
                equals(android.os.Environment.MEDIA_MOUNTED)) {
            cacheDir = getApplicationContext().getExternalCacheDir();
            appDirectory = new File(cacheDir + subFolder);

        } else {
            cacheDir = getApplicationContext().getCacheDir();
            String BaseFolder = cacheDir.getAbsolutePath();
            appDirectory = new File(BaseFolder + subFolder);

        }

        if (appDirectory != null && !appDirectory.exists()) {
            appDirectory.mkdirs();
        }

        File fileName = new File(appDirectory, file);

        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream(fileName);
            out = new ObjectOutputStream(fos);
            out.writeObject(userSettings);
        } catch (IOException ex) {
            ex.printStackTrace();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null)
                    fos.flush();
                fos.close();
                if (out != null)
                    out.flush();
                out.close();
            } catch (Exception e) {

            }
        }
    }


    private Map<String, String> readSetttings() throws Exception
    {
        File cacheDir = null;
        File appDirectory = null;
        if (android.os.Environment.getExternalStorageState().
                equals(android.os.Environment.MEDIA_MOUNTED)) {
            cacheDir = getApplicationContext().getExternalCacheDir();
            appDirectory = new File(cacheDir + subFolder);
        } else {
            cacheDir = getApplicationContext().getCacheDir();
            String BaseFolder = cacheDir.getAbsolutePath();
            appDirectory = new File(BaseFolder + subFolder);
        }

        if (appDirectory != null && !appDirectory.exists()){
            throw new Exception("File not found.");
        }

        File fileName = new File(appDirectory, file);

        FileInputStream fis = null;
        ObjectInputStream in = null;
        try {
            fis = new FileInputStream(fileName);
            in = new ObjectInputStream(fis);
            Map<String, String> myHashMap = (Map<String, String> ) in.readObject();
            return myHashMap;
        }finally {
            try {
                if(fis != null) {
                    fis.close();
                }
                if(in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
