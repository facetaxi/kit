package com.example;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;


public class v_k_2 extends Activity   implements View.OnClickListener
{
    Button b1;private WebView browser;
    


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.v_k_2);

        b1=(Button) findViewById(R.id.b1);
        b1.setOnClickListener(this);

        Singleton q = Singleton.getInstance();
        browser=(WebView)findViewById(R.id.webView);
        browser.loadUrl("http://taxi.ru/i/io.php?l="+q.getPhone()+"&p="+q.getPas()+"&o=72");
        browser.getSettings().setJavaScriptEnabled(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case (R.id.b1):
                b1();
                break;

        }
    }

    public void finish(){
        Log.v("press back===", "001");
    }

    private void b1() {
        Intent intent = new Intent();
        intent.setClass(this, zakazi.class);
        startActivity(intent);
        super.onDestroy();
        finish();
    }




}
