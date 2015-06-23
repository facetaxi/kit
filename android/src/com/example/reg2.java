package com.example;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import org.apache.http.client.methods.HttpGet;


public class reg2 extends Activity   implements View.OnClickListener
{
    Button back,reg;
    EditText phone,username;
    SharedPreferences sPref;

    //
    HttpClient1 mt1;
    public ProgressDialog dialog1;
    //

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg2);

        Singleton q = Singleton.getInstance();

        back=(Button) findViewById(R.id.back);
        back.setOnClickListener(this);

        reg=(Button) findViewById(R.id.reg);
        reg.setOnClickListener(this);

        phone   = (EditText)findViewById(R.id.phone);

        if (q.getPhone()!=null)
        {
            phone.setText(q.getPhone());
        }

        phone.requestFocus();

        username   = (EditText)findViewById(R.id.username);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case (R.id.back):
                back();
                break;
            case (R.id.reg):
                reguser();
                break;
        }
    }

    private void back() {
        Intent intent = new Intent();
        intent.setClass(this, auth.class);
        startActivity(intent);
        finish();
    }

    public void finish(){
        Log.v("press back===", "001");
    }


    public void reguser()
    {
        Singleton q = Singleton.getInstance();
        q.setPhone( phone.getText().toString() );
        sPref = getSharedPreferences("info", MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString("phone", q.getPhone()   );
        ed.commit();

        register();
    }

    private void register() {
        Singleton q = Singleton.getInstance();


        q.setPhone( phone.getText().toString() );

        sPref = getSharedPreferences("info", MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString("phone", q.getPhone()   );
        ed.commit();

        q.setUrl("http://taxi.ru/i/io.php?l="+q.getPhone()+"&n="+username.getText().toString()+"&o=12");


        /////////////////////// http поток
        dialog1 = new ProgressDialog(this);
        dialog1.setMessage("Регистрируем ..."); // Получение статуса заказа...
        dialog1.setIndeterminate(true);
        dialog1.setCancelable(true);
        dialog1.show();



        mt1 = new HttpClient1();
        mt1.execute((Void)null);

        /////////////////////// http поток

        //
//        String res="";
  //      HttpGet method = new HttpGet(q.getUrl());
    //    try {
      //      res=  WebHttpGet.executeHttpGet(method).trim();

       // } catch (Exception e) {
         //   Toast.makeText(this, "нет интернет соединения", Toast.LENGTH_SHORT).show();
        //}
        //












    }



    public class HttpClient1 extends AsyncTask<Void, Integer, Long> {

        // запускаем ProgressBar в момент запуска потока
        protected void onPreExecute() {

        }

        // сама работа потока, SendHttpPost() - наш долгоработающий метод
        protected Long doInBackground(Void... params) {

            Singleton q = Singleton.getInstance();
            String res="";
            HttpGet method = new HttpGet(q.getUrl());
            try {
                res=  WebHttpGet.executeHttpGet(method).trim();
            } catch (Exception e) {
                res="нет интернет соединения";
            }

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

        if (res.indexOf("интернет соединения")>0)
        {
            Toast.makeText(this, "нет интернет соединения", Toast.LENGTH_SHORT).show();
        }



        res=res.replaceAll("&quot;"," ");
        res=res.replaceAll("<br>","");
        res=res.replaceAll("  "," ");

        if (res.length()>0)
        {
            Toast.makeText(this, res, Toast.LENGTH_SHORT).show();

            //  if (res.indexOf("бесплатная")>0)
            //{
            //  q.set_focus(2);
            // back();
            // }

        }

        if (res.indexOf("прийдёт бесплатная SMS")>0)
        {
            q.set_focus(2);
            Intent intent = new Intent();
            intent.setClass(this, auth.class);
            startActivity(intent);
            finish();
        }

    }



}
