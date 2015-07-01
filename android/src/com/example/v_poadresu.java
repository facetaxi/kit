package com.example;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;


public class v_poadresu extends Activity   implements View.OnClickListener
{
    Button b1,b2;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.v_poadresu);

        b1=(Button) findViewById(R.id.b1);
        b1.setOnClickListener(this);

        b2=(Button) findViewById(R.id.b2);
        b2.setOnClickListener(this);



        poadresu();
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
        }
    }

    public void finish(){
        Log.v("press back===", "001");
    }

    private void poadresu() {
        Singleton q = Singleton.getInstance();
        q.setUrl("http://taxi.ru/i/io.php?l="+q.getPhone()+"&p="+q.getPas()+"&o=64"+"&z="+q.GetZakaz());
        String res="";
        HttpGet method = new HttpGet(q.getUrl());
        try {
          //  res=  WebHttpGet.MyTask();
            res=  WebHttpGet.executeHttpGet(method).trim();
        } catch (Exception e) {
            Toast.makeText(this, "нет интернет соединения", Toast.LENGTH_SHORT).show();
        }
    }

    private void b1() {
        rem();
    }

    private void b2() {
        otmenit("4");
        zakazi();
    }

    private void rem(){
        Intent intent = new Intent();
        intent.setClass(this, v_rem.class);
        startActivity(intent);
        finish();
    }

    private void zakazi() {
        Intent intent = new Intent();
        intent.setClass(this, zakazi.class);
        startActivity(intent);
        finish();
    }

    private void otmenit(String s) {
        Singleton q = Singleton.getInstance();
        q.setUrl("http://taxi.ru/i/io.php?l="+q.getPhone()+"&p="+q.getPas()+"&o=62"+"&z="+q.GetZakaz()+"&vodrem="+s);
        String res="";
        HttpGet method = new HttpGet(q.getUrl());
        try {
            res=  WebHttpGet.executeHttpGet(method).trim();
        } catch (Exception e) {
            Toast.makeText(this, "нет интернет соединения", Toast.LENGTH_SHORT).show();
        }
    }
}
