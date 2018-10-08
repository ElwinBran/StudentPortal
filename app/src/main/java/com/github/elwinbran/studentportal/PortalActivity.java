package com.github.elwinbran.studentportal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.MenuItem;
import android.webkit.URLUtil;
import android.webkit.WebView;

import java.net.MalformedURLException;

public class PortalActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.portal_view);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        String url = getIntent().getStringExtra("url");
        try
        {
            if(!URLUtil.isValidUrl(url) || !Patterns.WEB_URL.matcher(url).matches())
            {
                throw new MalformedURLException();
            }
        }
        catch (Throwable e)
        {
            throw new Error(e);
        }
        WebView portalViewer = findViewById(R.id.portalViewer);
        portalViewer.loadUrl(url);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(myIntent);
        return true;
    }
}
