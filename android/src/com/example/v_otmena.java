package com.example;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
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


public class v_otmena extends Activity   implements View.OnClickListener
{
    Button e1,e2,e3;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.v_otmena);

        e1=(Button) findViewById(R.id.e1);
        e1.setOnClickListener(this);

        e2=(Button) findViewById(R.id.e2);
        e2.setOnClickListener(this);

        e3=(Button) findViewById(R.id.e3);
        e3.setOnClickListener(this);

    }




    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case (R.id.e1):
                e1();
                break;
            case (R.id.e2):
                e2();
                break;
            case (R.id.e3):
                e3();
                break;
        }
    }

    private void e1() {
        otmenit("1");
        zakazi();
    }

    private void e2() {
        otmenit("2");
        zakazi();
    }

    private void e3() {
        otmenit("3");
        zakazi();
    }

    public void finish(){
        Log.v("press back===", "001");
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

    private void zakazi (){
        Intent intent = new Intent();
        intent.setClass(this, zakazi.class);
        startActivity(intent);
        finish();
    }



}
