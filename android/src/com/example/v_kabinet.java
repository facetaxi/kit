package com.example;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;


public class v_kabinet extends Activity   implements View.OnClickListener
{
    Button b1,b2,b3,b4,zakazi;
    WebView browser;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.v_kabinet);

        b1=(Button) findViewById(R.id.b1);
        b1.setOnClickListener(this);

        b2=(Button) findViewById(R.id.b2);
        b2.setOnClickListener(this);

        b3=(Button) findViewById(R.id.b3);
        b3.setOnClickListener(this);

        b4=(Button) findViewById(R.id.b4);
        b4.setOnClickListener(this);

        zakazi=(Button) findViewById(R.id.zakazi);
        zakazi.setOnClickListener(this);




        Singleton q = Singleton.getInstance();
        q.setUrl("http://taxi.ru/i/io.php?l="+q.getPhone()+"&p="+q.getPas()+"&o=70");
        String res="";
        HttpGet method = new HttpGet(q.getUrl());
        try {
            res=  WebHttpGet.executeHttpGet(method).trim();
        } catch (Exception e) {
            Toast.makeText(this, "нет интернет соединения", Toast.LENGTH_SHORT).show();
        }



        TextView answer = ( TextView )findViewById( R.id.textView );
        answer.setText( res )  ;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case (R.id.b1):
                b1();
                break;
            case (R.id.b2):
                b2();
                break;
            case (R.id.b3):
                b3();
                break;
            case (R.id.b4):
                b4();
                break;
            case (R.id.zakazi):
                zakazi();
                break;
        }
    }


    public void finish(){
        Log.v("press back===", "v_kabinet"    );
    }


    private void b1() {
        Intent intent = new Intent();
        intent.setClass(this, v_k_1.class);
        startActivity(intent);
        super.onDestroy();
        finish();
    }
    private void b2() {
        Intent intent = new Intent();
        intent.setClass(this, v_k_2.class);
        startActivity(intent);
        super.onDestroy();
        finish();
    }
    private void b3() {
        Intent intent = new Intent();
        intent.setClass(this, v_k_3.class);
        startActivity(intent);
        super.onDestroy();
        finish();
    }
    private void b4() {
        Intent intent = new Intent();
        intent.setClass(this, v_k_4.class);
        startActivity(intent);
        super.onDestroy();
        finish();
    }

    private void zakazi() {
        Intent intent = new Intent();
        intent.setClass(this, zakazi.class);
        startActivity(intent);

        super.onDestroy();
        finish();
    }

}
