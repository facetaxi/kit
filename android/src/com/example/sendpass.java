package com.example;


import android.app.Activity;
import android.app.ProgressDialog;
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


public class sendpass extends Activity   implements View.OnClickListener
{
    Button back,sendpas;
    EditText uiphone;
    SharedPreferences sPref;

    //
    HttpClient1 mt1;
    public ProgressDialog dialog1;
    //

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sendpass);

        back=(Button) findViewById(R.id.back);
        back.setOnClickListener(this);

        sendpas=(Button) findViewById(R.id.sendpas);
        sendpas.setOnClickListener(this);

        uiphone   = (EditText)findViewById(R.id.phone);

       Singleton q = Singleton.getInstance();
        if (q.getPhone()!=null)
        {
            uiphone.setText(q.getPhone());
        }

    }

    public void finish(){
        Log.v("press back===", "001");
    }


    private void send_pas() {
        Singleton q = Singleton.getInstance();



        q.setPhone( uiphone.getText().toString() );

        sPref = getSharedPreferences("info", MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString("phone", q.getPhone()   );
        ed.commit();

        q.setUrl("http://taxi.ru/i/io.php?l="+q.getPhone()+"&o=11");


        /////////////////////// http поток
        dialog1 = new ProgressDialog(this);
        dialog1.setMessage("Отправляем пароль ..."); // Получение статуса заказа...
        dialog1.setIndeterminate(true);
        dialog1.setCancelable(true);
        dialog1.show();



        mt1 = new HttpClient1();
        mt1.execute((Void)null);

        /////////////////////// http поток






    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case (R.id.back):
                back();
                break;
            case (R.id.sendpas):
                send_pas();
                break;
        }
    }

    private void back() {

        Intent intent = new Intent();
        intent.setClass(this, auth.class);
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

        if (res.length()>0)
        {
            Toast.makeText(this, res, Toast.LENGTH_SHORT).show();

            if (res.indexOf("бесплатная")>0)
            {
                q.set_focus(2);
                back();
            }

        }

    }

}
