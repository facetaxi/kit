package com.example;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import org.apache.http.client.methods.HttpGet;
import java.io.InputStream;



public class auth extends Activity   implements View.OnClickListener
{
    Button back,vxod,reg,sendpas;
    EditText phone,pas;
    private static final String TAG = "auth";
    private InputStream r;
    public ProgressDialog dialog1;
    HttpClient1 mt1;
    SharedPreferences sPref;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        Log.v("auth","onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth);

        sendpas=(Button) findViewById(R.id.sendpas);
        sendpas.setOnClickListener(this);

        reg=(Button) findViewById(R.id.reg);
        reg.setOnClickListener(this);


        back=(Button) findViewById(R.id.back);
        back.setOnClickListener(this);

        vxod=(Button) findViewById(R.id.vxod);
        vxod.setOnClickListener(this);

        phone   = (EditText)findViewById(R.id.phone);
        pas   = (EditText)findViewById(R.id.password);


        Singleton q = Singleton.getInstance();

        if (q.get_focus()==2)
        {
            pas.requestFocus();
        }

        sPref = getSharedPreferences("info", MODE_PRIVATE);
        String h_phone = sPref.getString("phone","");
        String h_pas = sPref.getString("pas","");

        if (h_phone!=null)
        {
            phone.setText(h_phone);
            q.setPhone(h_phone);
        }
        if (h_pas!=null)
        {
            pas.setText(h_pas);
            q.setPas(h_pas);
        }
    }





    @Override
    public void onClick(View v) {
        Log.v("auth","onClick");
        switch (v.getId())
        {
            case (R.id.back):
            back();
            break;

            case (R.id.reg):
                reg();
                break;

            case (R.id.sendpas):
                sendpas();
                break;

            case (R.id.vxod):
                 vxod();
                break;
        }
    }

    private void vxod() {
        Log.v("auth","vxod");

        Log.v("vxod","1");

        Singleton q = Singleton.getInstance();

        phone   = (EditText)findViewById(R.id.phone);
        pas   = (EditText)findViewById(R.id.password);

        q.setPhone( phone.getText().toString() );
        q.setPas( pas.getText().toString() );



        sPref = getSharedPreferences("info", MODE_PRIVATE);
        Editor ed = sPref.edit();
        ed.putString("phone", q.getPhone()   );
        ed.putString("pas", q.getPas()    );
        ed.commit();

        q.setUrl("http://taxi.ru/i/io.php?l="+q.getPhone()+"&p="+q.getPas());

        /////////////////////// http поток
        dialog1 = new ProgressDialog(this);
        dialog1.setMessage("Вход...");
        dialog1.setIndeterminate(true);
        dialog1.setCancelable(true);
        dialog1.show();



        mt1 = new HttpClient1();
        mt1.execute((Void)null);

        /////////////////////// http поток


    }

    public class HttpClient1 extends AsyncTask<Void, Integer, Long> {

        // запускаем ProgressBar в момент запуска потока
        protected void onPreExecute() {

        }

        // сама работа потока, SendHttpPost() - наш долгоработающий метод
        protected Long doInBackground(Void... params) {
            Singleton q = Singleton.getInstance();
            //
            String res="";
            HttpGet method = new HttpGet(q.getUrl());
            try {
                res=  WebHttpGet.executeHttpGet(method).trim();
            } catch (Exception e) {
               // Toast.makeText(this, "нет интернет соединения", Toast.LENGTH_SHORT).show();
            }
            //

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


    public void HttpClient1_end(){

        dialog1.dismiss();
        Singleton q = Singleton.getInstance();
        String res=q.get_1();
        int ir;

        try
        {
            ir=Integer.parseInt( res );
        }
        catch (NumberFormatException e)
        {
            e.printStackTrace();
            ir=2;
        }

        if (ir==1)
        {
            //   Toast.makeText(this, "клиент", Toast.LENGTH_SHORT).show();
            back_and_vxod();
        }
        if (ir==3)
        {
            //  Toast.makeText(this, "водитель", Toast.LENGTH_SHORT).show();
            voditel();
        }

        if (ir ==2 )
        {
            Toast.makeText(this, "неверный телефон или пароль", Toast.LENGTH_SHORT).show();
        }
    }

    public void finish(){
        Log.v("press back===", "001"    );
    }

    private void reg() {
        Log.v("auth","reg");
        Singleton q = Singleton.getInstance();
        q.setPhone( phone.getText().toString() );
        sPref = getSharedPreferences("info", MODE_PRIVATE);
        Editor ed = sPref.edit();
        ed.putString("phone", q.getPhone()   );
        ed.commit();

        Intent intent = new Intent();
        intent.setClass(this, reg2.class);
        startActivity(intent);
        finish();
    }

    private void sendpas() {
        Log.v("auth","sendpas");
        Intent intent = new Intent();
        intent.setClass(this, sendpass.class);
        startActivity(intent);
        finish();
    }

    private void voditel() {
        Log.v("auth","voditel");

        Singleton q = Singleton.getInstance();
        q.set_auth_status(true);
        Intent intent = new Intent();
        intent.setClass(this, zakazi.class);
        startActivity(intent);
        finish();

    }

    private void back_and_vxod() {

        Log.v("auth","back_and_vxod");

        Singleton q = Singleton.getInstance();
        q.set_auth_status(true);

       // Log.v("fast===",    Integer.toString    ( q.get_button_press() ) );

        if (q.get_button_press()==0)
        {
            Intent intent = new Intent();
            intent.setClass(this, fast.class);
            startActivity(intent);
            finish();
        }

        if (q.get_button_press()==1)
        {
            Log.v("auth_===","1");
            if (q.get_call_status()==1)
            {
                Intent intent = new Intent();
                intent.setClass(this, MyActivity.class);
                startActivity(intent);
                finish();
            }
            else
            {
                Intent intent = new Intent();
                intent.setClass(this, fast.class);
                startActivity(intent);
                finish();
            }
        }
        if (q.get_button_press()==2)
        {
            Log.v("auth_===","2");
            Intent intent = new Intent();
            intent.setClass(this, fast.class);
            startActivity(intent);
            finish();
        }

        if (q.get_button_press()==4)
        {
            q.set_focus(10);
            Log.v("auth_===","4");
            Intent intent = new Intent();
            intent.setClass(this, call2.class);
            startActivity(intent);
            finish();
        }

        if (q.get_button_press()==3)
        {
            Log.v("auth_===","3");
            q.set_HysType(1);
            Intent intent = new Intent();
            intent.setClass(this, hystory.class);  //история     hystory   zakazi
            startActivity(intent);
            finish();
        }


    }

    private void back() {
        Log.v("auth","back");
        Intent intent = new Intent();
        intent.setClass(this, fast.class);
        startActivity(intent);
        finish();

    }


}
