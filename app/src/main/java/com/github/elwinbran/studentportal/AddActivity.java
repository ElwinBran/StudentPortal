package com.github.elwinbran.studentportal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        setContentView(R.layout.activity_add);
        Button add = findViewById(R.id.finishPortalAddButton);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent previousScreen = new Intent(getApplicationContext(), MainActivity.class);
                StringBuilder titleBuilder = new StringBuilder(((EditText)findViewById(R.id.titleInput)).getText());
                String title = titleBuilder.toString();
                previousScreen.putExtra(Portal.TITLE_IDENTIFIER, title);
                StringBuilder urlBuilder = new StringBuilder(((EditText)findViewById(R.id.urlInput)).getText());
                String url = urlBuilder.toString();
                previousScreen.putExtra(Portal.URL_IDENTIFIER, url);
                setResult(Activity.RESULT_OK, previousScreen);
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(myIntent);
        return true;
    }
}
