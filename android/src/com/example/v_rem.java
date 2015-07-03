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


public class v_rem extends Activity   implements View.OnClickListener
{
    Button b1;
    EditText mEdit;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.v_rem);

        b1=(Button) findViewById(R.id.b1);
        b1.setOnClickListener(this);



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
        zakazok();
        zakazi();
    }


    private void zakazok() {

        mEdit   = (EditText)findViewById(R.id.editText);

        Singleton q = Singleton.getInstance();
        q.setUrl("http://taxi.ru/i/io.php?l="+q.getPhone()+"&p="+q.getPas()+"&o=63"+"&z="+q.GetZakaz()+"&rem="+mEdit.getText().toString());
        String res="";
        HttpGet method = new HttpGet(q.getUrl());
        try {
            res=  WebHttpGet.executeHttpGet(method).trim();
        } catch (Exception e) {
            Toast.makeText(this, "нет интернет соединения", Toast.LENGTH_SHORT).show();
        }
    }



    private void zakazi() {

        Intent intent = new Intent();
        intent.setClass(this, zakazi.class);
        startActivity(intent);
        finish();
    }
}
