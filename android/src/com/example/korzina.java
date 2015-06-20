package com.example;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import org.apache.http.client.methods.HttpGet;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class korzina extends ListActivity implements View.OnClickListener {
    private ListView list1;

    Button back,empty, delete,beru;

    ArrayList<String> v=new ArrayList<String>();//то что отображаем
    ArrayList<String> idz=new ArrayList<String>();// номера заказов
    private ArrayAdapter<String> adapter;

    private Timer updateTimer;
    protected Handler taskHandler = new Handler();
    protected Boolean isComplete = false;
    TimerTask task;
    Timer timer;private int j;
    private boolean beepplay;private boolean startform;
    private LocationManager myManager;


    //private String[] w2 = new String[] { };// с сервера



    //

    HttpClient2 mt2;
    HttpClient3 mt3;

    HttpClient5 mt5;
    HttpClient6 mt6;
    public ProgressDialog dialog1;
    public ProgressDialog dialog2;
    public ProgressDialog dialog3;

    public ProgressDialog dialog5;
    public ProgressDialog dialog6;

    public boolean addz_selected;
    //
    @Override
    public void onCreate(Bundle icicle)
    {
        super.onCreate(icicle);
        setContentView(R.layout.korzina);


        back=(Button) findViewById(R.id.back);
        back.setOnClickListener(  this);

        empty=(Button) findViewById(R.id.empty);
        empty.setOnClickListener(  this);

        delete=(Button) findViewById(R.id.delete);
        delete.setOnClickListener(  this);

        beru=(Button) findViewById(R.id.beru);
        beru.setOnClickListener(  this);

        addz_selected=true;

        beru=(Button) findViewById(R.id.beru);
        beru.setOnClickListener( this);

        AddZ();


    }

    public void finish(){
        Log.v("press back===", "001"    );
    }


    public void back(){
        Intent intent = new Intent();
    intent.setClass(this, zakazi.class);
    startActivity(intent);
    finish();
}

    public void onStatusChanged(String provider, int status, Bundle extras) {}

    private void v_start_zokono_clear() { //!!! ПРИ СТАРТЕ ВОДИТЕЛЯ с нуля СТИРАТЬ ВСЕ ЗАПИСИ В VODITEL_ZOKNO ПОД ВОДИТЕЛЕМ
        Singleton q = Singleton.getInstance();
        q.setUrl("http://taxi.ru/i/io.php?l="+q.getPhone()+"&p="+q.getPas()+"&o=40");

        /////////////////////// http поток 2
        dialog2 = new ProgressDialog(this);
        dialog2.setMessage("Инициализация 002..."); // Получение статуса заказа...
        dialog2.setIndeterminate(true);
        dialog2.setCancelable(true);
        dialog2.show();
        mt2 = new HttpClient2();
        mt2.execute((Void)null);
        /////////////////////// http поток 2
    }

    private void AddZ() {
        addz_selected=false;
        beepplay=false;
        Singleton q = Singleton.getInstance();
        //q.setUrl("http://www.onlinetaxi.pro/i/io.php?l="+q.getPhone()+"&p="+q.getPas()+"&o=4"+"&m="+q.getMaxIdZakaz());
        q.setUrl("http://www.onlinetaxi.pro/i/io.php?l="+q.getPhone()+"&p="+q.getPas()+"&o=203"+"&m="+"0");

        /////////////////////// http поток 3
        dialog3 = new ProgressDialog(this);
        dialog3.setMessage("Обновляем заказы..."); // Получение статуса заказа...
        dialog3.setIndeterminate(true);
        dialog3.setCancelable(true);
        dialog3.show();
        mt3 = new HttpClient3();
        mt3.execute((Void)null);
        /////////////////////// http поток 3

    }

    private void DelZ() {
        Singleton q = Singleton.getInstance();
        q.setUrl("http://www.onlinetaxi.pro/i/io.php?l="+q.getPhone()+"&p="+q.getPas()+"&o=6"+"&m="+q.getMinIdZakaz());



        /////////////////////// http поток 5
        dialog5 = new ProgressDialog(this);
        dialog5.setMessage("Инициализация 005..."); // Получение статуса заказа...
        dialog5.setIndeterminate(true);
        dialog5.setCancelable(true);
        dialog5.show();
        mt5 = new HttpClient5();
        mt5.execute((Void)null);
        /////////////////////// http поток 5

    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String item = (String) getListAdapter().getItem(position);
        Singleton q = Singleton.getInstance();
        q.SetZakaz( idz.get(position) );
        //    Toast.makeText(this, MessageFormat.format("{0} выбран", idz.get(position) ), Toast.LENGTH_LONG).show();
        Toast.makeText(this, item + " выбран", Toast.LENGTH_LONG).show();

    }

    public void onClick(View v) {
        switch (v.getId())
        {

            case (R.id.back):
                back();
                break;

            case (R.id.empty):
                empty();
                break;

            case (R.id.delete):
                delete();
                break;

            case (R.id.beru):
                beru();
                break;

        }
    }

    private void empty() {
    }

    private void delete() {
    }

    private  void  beru()
    {
        Singleton q = Singleton.getInstance();
        if (   Integer.parseInt(   q.GetZakaz())>0      )
        {
            //   startform=true;
            Log.v("activez==3",q.GetZakaz());

            //////////////////////////////////////////////////////////////////////////////////////////////////////////////проверяем что этот заказ еще активен = то есть свободен
            q.setUrl("http://www.onlinetaxi.pro/i/io.php?l=" + q.getPhone() + "&p=" + q.getPas() + "&o=7&z="+q.GetZakaz()  );//status

            /////////////////////// http поток 6
            dialog6 = new ProgressDialog(this);
            dialog6.setMessage("Инициализация 006..."); // Получение статуса заказа...
            dialog6.setIndeterminate(true);
            dialog6.setCancelable(true);
            dialog6.show();
            mt6 = new HttpClient6();
            mt6.execute((Void)null);
            /////////////////////// http поток 6

        }
        else
        {
            Toast.makeText(this, "выберите заказ", Toast.LENGTH_LONG).show();
        }
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
                res="нет интернет соединения";
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

        //on_end 2
    }



    public class HttpClient3 extends AsyncTask<Void, Integer, Long> {

        // запускаем ProgressBar в момент запуска потока
        protected void onPreExecute() {

        }

        // сама работа потока, SendHttpPost() - наш долгоработающий метод
        protected Long doInBackground(Void... params) {

            Singleton q = Singleton.getInstance();

//on_process 3
            String res="";
            HttpGet method = new HttpGet(q.getUrl());
            try {
                res=  WebHttpGet.executeHttpGet(method).trim();
            } catch (Exception e) {
                res="нет интернет соединения";
            }
            //on_process 3

            q.set_3(res);

            return null;
        }

        // как только получили ответ от сервера, выключаем ProgressBar
        protected void onPostExecute(Long unused) {
            Singleton q = Singleton.getInstance();
            Log.v("HttpClient3====","onPostExecute");
            HttpClient3_end();
            super.onPostExecute(unused);
        }

    }

    public void HttpClient3_end()
    {
        dialog3.dismiss();
        addz_selected=true;
        Singleton q = Singleton.getInstance();
        String res=q.get_3();
        //on_end 3
        v.clear();
        idz.clear();
        if (res.length()>3)
        {

            String[] w = new String[] { };
            String[] w2 = new String[] { };
            w = res.split("</br>");
            w2 = w;

            if (w.length>0)
            {

                for(int i =0; i < w.length ; i++)
                {
                    int l1=w[i].indexOf(";");
                    int l2=w2[i].length();
                    String nz =  w[i].substring(0,l1);

                    idz.add( nz  );
                    v.add(  w2[i].substring(l1+1,l2)  );

                    if ( Integer.parseInt(nz)> Integer.parseInt( q.getMaxIdZakaz())  )
                    {
                        q.setMaxIdZakaz(nz);
                        beepplay=true;
                    }
                }

                //      q.setMaxIdZakaz(idz.get(0));
                //    q.setMinIdZakaz(idz.get(idz.size() - 1));

                //    if (  Integer.parseInt( q.getMaxIdZakaz()) <Integer.parseInt(q.getMinIdZakaz()) )
                //  {
                //    String t= q.getMaxIdZakaz();
                //    q.setMaxIdZakaz( q.getMinIdZakaz()  );
                //    q.setMinIdZakaz(t );
                // }

                //  Log.v("НОМЕР MAX ЗАКАЗА", q.getMaxIdZakaz());
                //  Log.v("НОМЕР MIN ЗАКАЗА", q.getMinIdZakaz());
                //  Log.v("idz.size()-1=", String.valueOf((idz.size()-1)));

            }

        }
        adapter.notifyDataSetChanged();

        if (startform)
        {
            if (beepplay)
            {
               // android.media.ToneGenerator tg=new ToneGenerator(AudioManager.STREAM_ALARM, 100);
               // tg.startTone(ToneGenerator.TONE_CDMA_ABBR_ALERT);
                //Toast.makeText(this, "новый заказ", Toast.LENGTH_SHORT).show();
            }
        }

        //on_end 3
    }



    public class HttpClient5 extends AsyncTask<Void, Integer, Long> {

        // запускаем ProgressBar в момент запуска потока
        protected void onPreExecute() {

        }

        // сама работа потока, SendHttpPost() - наш долгоработающий метод
        protected Long doInBackground(Void... params) {

            Singleton q = Singleton.getInstance();
//on_process 5
            String res="";
            HttpGet method = new HttpGet(q.getUrl());
            try {
                res=  WebHttpGet.executeHttpGet(method).trim();
            } catch (Exception e) {
                res= "нет интернет соединения";
            }
            //on_process 5



            q.set_5(res);

            return null;
        }

        // как только получили ответ от сервера, выключаем ProgressBar
        protected void onPostExecute(Long unused) {
            Singleton q = Singleton.getInstance();
            Log.v("HttpClient5====","onPostExecute");
            HttpClient5_end();
            super.onPostExecute(unused);
        }

    }

    public void HttpClient5_end()
    {
        dialog5.dismiss();
        Singleton q = Singleton.getInstance();
        String res=q.get_5();
        //on_end 5
        if (res.length()>3)
        {

            String[] w = new String[] { };
            w = res.split(";");

            if (w.length>0)
            {
                for(int i =0; i < w.length ; i++)
                {
                    int id_delete = -1;
                    for(int j =0; j < v.size()-1 ; j++);
                    {
                        if (v.get(j)==w[i])
                        {
                            id_delete=j;
                        }
                    }
                    if (id_delete>-1)
                    {
                        v.remove(id_delete);
                    }                    }
            }


            adapter.notifyDataSetChanged();
        }
        //on_end 5

    }


    public class HttpClient6 extends AsyncTask<Void, Integer, Long> {

        // запускаем ProgressBar в момент запуска потока
        protected void onPreExecute() {

        }

        // сама работа потока, SendHttpPost() - наш долгоработающий метод
        protected Long doInBackground(Void... params) {

            Singleton q = Singleton.getInstance();
//on_process 6
            String res="";
            HttpGet method = new HttpGet(q.getUrl());
            try {
                res=  WebHttpGet.executeHttpGet(method).trim();
            } catch (Exception e) {
                res="нет интернет соединения";
            }
            //on_process 6

            q.set_6(res);

            return null;
        }

        // как только получили ответ от сервера, выключаем ProgressBar
        protected void onPostExecute(Long unused) {
            Singleton q = Singleton.getInstance();
            Log.v("HttpClient6====","onPostExecute");
            HttpClient6_end();
            super.onPostExecute(unused);
        }

    }

    public void HttpClient6_end()
    {
        dialog6.dismiss();
        Singleton q = Singleton.getInstance();
        String res=q.get_6();
        //on_end 6
        Log.v("activez==",res);
        if (res.equals("1"))//есть активный заказ переходим
        {
            Log.v("activez==4",q.GetZakaz());
            Intent intent = new Intent();
            intent.setClass(this, v_detail.class);
            startActivity(intent);
            super.onDestroy();
            finish();
        }
        else
        {
            Toast.makeText(this, "уже взят другим", Toast.LENGTH_LONG).show();
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////проверяем что этот заказ еще активен = то есть свободен
        //on_end 6

    }



}


