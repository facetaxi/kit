package com.example;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.AsyncTask;
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


public class v_detail   extends Activity   implements View.OnClickListener
{
    Button poadresu,otmena,karta;

    //
    HttpClient1 mt1;
    HttpClient2 mt2;

    public ProgressDialog dialog1;
    public ProgressDialog dialog2;


    //

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.v_detail);

        Singleton q = Singleton.getInstance();
     //  Log.v("activez2==", "http://taxi.ru/i/io.php?l="+q.getPhone()+"&p="+q.getPas()+"&o=61"+"&z="+q.GetZakaz());

        poadresu=(Button) findViewById(R.id.poadresu);
        poadresu.setOnClickListener(this);

        otmena=(Button) findViewById(R.id.otmena);
        otmena.setOnClickListener(this);

        karta=(Button) findViewById(R.id.karta);
        karta.setOnClickListener(this);

        //проверяем нужно ли перейти на другое окно

                    q.setUrl("http://taxi.ru/i/io.php?l="+q.getPhone()+"&p="+q.getPas()+"&o=79" );

        /////////////////////// http поток 1
        dialog1 = new ProgressDialog(this);
        dialog1.setMessage("Загружаем ..."); // Получение статуса заказа...
        dialog1.setIndeterminate(true);
        dialog1.setCancelable(true);
        dialog1.show();
        mt1 = new HttpClient1();
        mt1.execute((Void)null);
        /////////////////////// http поток 1



    }

    private void LoadDetail() {

        Singleton q = Singleton.getInstance();
        q.setUrl("http://taxi.ru/i/io.php?l="+q.getPhone()+"&p="+q.getPas()+"&o=61"+"&z="+q.GetZakaz());



        /////////////////////// http поток 2
        dialog2 = new ProgressDialog(this);
        dialog2.setMessage("Загружаем заказ ..."); // Получение статуса заказа...
        dialog2.setIndeterminate(true);
        dialog2.setCancelable(true);
        dialog2.show();
        mt2 = new HttpClient2();
        mt2.execute((Void)null);
        /////////////////////// http поток 2

    }

    public void finish(){
        Log.v("press back===", "001"    );
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case (R.id.poadresu):
                gopoadresu();
                break;
            case (R.id.otmena):
                gootmena();
                break;
            case (R.id.karta):
                karta();
                break;
        }
    }

    private void karta() {
        Intent intent = new Intent();
        intent.setClass(this, karta.class);
        startActivity(intent);
        finish();
    }

    private void gootmena() {
        Intent intent = new Intent();
        intent.setClass(this, v_otmena.class);
        startActivity(intent);
        finish();
    }

    private void gopoadresu() {
        Intent intent = new Intent();
        intent.setClass(this, v_poadresu.class);
        startActivity(intent);
        finish();
    }


    public class HttpClient1 extends AsyncTask<Void, Integer, Long> {

        // запускаем ProgressBar в момент запуска потока
        protected void onPreExecute() {

        }

        // сама работа потока, SendHttpPost() - наш долгоработающий метод
        protected Long doInBackground(Void... params) {

            Singleton q = Singleton.getInstance();
            //on_process 1
            String res="";
            HttpGet method = new HttpGet(q.getUrl());
            try {
                res=  WebHttpGet.executeHttpGet(method).trim();
            } catch (Exception e) {
               res= "нет интернет соединения" ;
            }
            //on_process 1



            q.set_1(res);

            return null;
        }

        // как только получили ответ от сервера, выключаем ProgressBar
        protected void onPostExecute(Long unused) {
            Singleton q = Singleton.getInstance();
            Log.v("HttpClient1====","onPostExecute");
            HttpClient1_end();
            super.onPostExecute(unused);
        }

    }

    public void HttpClient1_end()
    {
        dialog1.dismiss();
        Singleton q = Singleton.getInstance();
        String res=q.get_1();
//on_end 1
        //проверяем нужно ли перейти на другое окно
        if (res.equals("0")) {         LoadDetail();       }
        if (res.equals("2")) {         gopoadresu();       }
        //   if (res.equals("2")) {         LoadDetail();       }
//on_end 1

    }


    public class HttpClient2 extends AsyncTask<Void, Integer, Long> {

        // запускаем ProgressBar в момент запуска потока
        protected void onPreExecute() {

        }

        // сама работа потока, SendHttpPost() - наш долгоработающий метод
        protected Long doInBackground(Void... params) {

            Singleton q = Singleton.getInstance();
//on_process 2
            String res="";
            HttpGet method = new HttpGet(q.getUrl());
            try {
                res=  WebHttpGet.executeHttpGet(method).trim();
            } catch (Exception e) {
                res= "нет интернет соединения";
            }

            //on_process 2



            q.set_2(res);

            return null;
        }

        // как только получили ответ от сервера, выключаем ProgressBar
        protected void onPostExecute(Long unused) {
            Singleton q = Singleton.getInstance();
            Log.v("HttpClient2====","onPostExecute");
            HttpClient2_end();
            super.onPostExecute(unused);
        }

    }

    public void HttpClient2_end()
    {
        dialog2.dismiss();
        Singleton q = Singleton.getInstance();
        String res=q.get_2();
        //on_end 2
        TextView answer = ( TextView )findViewById( R.id.textView );
        answer.setText( res )  ;
        //on_end 2

    }
}
