package com.example;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.HttpClient;

import java.io.BufferedReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Timer;
import java.util.TimerTask;


public class call2 extends Activity   implements View.OnClickListener
{

    //
    HttpClient1 mt1;
    HttpClient2 mt2;
    HttpClient3 mt3;
    public ProgressDialog dialog1;
    public ProgressDialog dialog2;
    public ProgressDialog dialog3;
    //

    public boolean igem=false;
    public boolean zavershen=false;



    Button otmena;
    TextView status;
    int autovixod=0;
    Timer myTimerCall = new Timer();
    @Override
    public void onCreate(Bundle savedInstanceState)
    {


        Log.v("zakaz_send====","1");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.call2);

       otmena=(Button) findViewById(R.id.otmena);
        otmena.setOnClickListener(this);

        status   = (TextView)findViewById(R.id.status);

        Singleton q = Singleton.getInstance();
        Log.v("zakaz_send====","2");

        if (q.get_a())
        {
            zakaz_send();//записываем новый заказ
        }
        else
        {
            //вытаскиваем параметры активного заказа


          //  if (status.toString().indexOf("машина")>0)
           // {
                otmena.setVisibility(View.GONE);
          //  }

           // if (status.toString().indexOf("завершен")>0)
            //{
              //  otmena.setVisibility(View.VISIBLE);
                //beep=true;
            //}

            STCall();
        }



    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case (R.id.otmena):
                Otmena();
                break;
        }
    }

    private void STCall() {

        final Handler uiHandler = new Handler();
        myTimerCall.schedule(new TimerTask() {
            @Override
            public void run() {
                uiHandler.post(new Runnable() {
                    @Override
                    public void run() {
                            if (!zavershen)
                            {
                                Status();
                            }
                    }
                });
            }



            ;
        }, 0L, 1000);

    }

    private void Status() {
        Log.v("Status====","Узнаем статус");

        Singleton q = Singleton.getInstance();

        q.setUrl("http://taxi.ru/i/io.php?l="+q.getPhone()+"&p="+q.getPas()+"&o=4");//status


/////////////////////// http поток
    //    dialog2 = new ProgressDialog(this);
      // dialog2.setMessage("Проверяем статус..."); // Получение статуса заказа...
        //dialog2.setIndeterminate(true);
       // dialog2.setCancelable(true);
       // dialog2.show();



        mt2 = new HttpClient2();
        mt2.execute((Void)null);

        /////////////////////// http поток



    }

    private void Otmena() {
        Singleton q = Singleton.getInstance();
        Log.v("Otmena====",String.valueOf( q.get_call_status()  ));


        if (q.get_call_status()!=30)  {//то отменяем
        timer_stop();

        q.setUrl("http://taxi.ru/i/io.php?l="+q.getPhone()+"&p="+q.getPas()+"&o=3");//otmena

            /////////////////////// http поток
            dialog3 = new ProgressDialog(this);
            dialog3.setMessage("Отменяем заказ..."); // Получение статуса заказа...
            dialog3.setIndeterminate(true);
            dialog3.setCancelable(true);
            dialog3.show();



            mt3 = new HttpClient3();
            mt3.execute((Void)null);

            /////////////////////// http поток
        }
        if (q.get_call_status()==30)  {//то отменяем
            back();
        }

    }

    private void back() {

          igem=false;
         zavershen=false;

        Singleton q = Singleton.getInstance();
        Log.v("back()_focus====", String.valueOf(q.get_focus())    );

      //  q.set_nazad_from_call2(true);

        if (q.get_focus()==10)
        {
            Intent intent = new Intent();
            intent.setClass(this, fast.class);
            startActivity(intent);
            finish();
        }
        if (q.get_focus()==20)
        {
            Intent intent = new Intent();
            intent.setClass(this, MyActivity.class);
            startActivity(intent);
            finish();
        }

    }

    public void finish(){
            Log.v("finish()====", "6"    );
    }



    private void nazad() {
        if (igem)
        {
            Log.v("завершен=nazad()====","7");

            Singleton q = Singleton.getInstance();
         //   timer_stop();
            q.set_call_status(30);
            otmena.setText("назад");
        }
    }

    private void timer_stop() {

        Log.v("timer_stop()====","8");

        if (myTimerCall!=null)
        {
            myTimerCall.cancel();
            myTimerCall.purge();
            myTimerCall=null;
        }
        //otmena.setVisibility(View.GONE);

    }

    private void zakaz_send() {

        Log.v("zakaz_send()====","9");


        Singleton q = Singleton.getInstance();




        String a1="";
        String a2="";
        String a3="";

        String b1="";
        String b2="";
        String b3="";

        String c1="";
        String c2="";
        String c3="";
        String c4="";
        String c5="";

        String d1="";//tarifcar
        String d2="";
        String d3="";


        String kondic="";
        String kurevo="";
        String baby="";

        String mcena,skidka,cenaoptext,mktime,mkm;
        String yandx;
        String send;


        String e1= null;
        String e2= null;
        String e3= null;
        try {
            e1 = URLEncoder.encode(q.get_otkuda_gorod(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        try {
            e2 = URLEncoder.encode(q.get_otkuda_ulica(),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        try {
            e3 = URLEncoder.encode(q.get_otkuda_dom(),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        a1="&in_city="+e1;
        a2="&in_ulica="+e2;
        a3="&in_dom="+e3;



        if (q.get_kuda_gorod()!=null) { b1="&out_city="+q.get_kuda_gorod(); }
        if (q.get_kuda_ulica()!=null)  { b2="&out_ulica="+q.get_kuda_ulica();}
        if (q.get_kuda_dom()!=null) { b3="&out_dom="+q.get_kuda_dom();                                     }

        c1="&hour="+q.get_hour();
        c2="&minute="+q.get_minute();
        c3="&dday="+q.get_day();
        c4="&mmonth="+q.get_month();
        c5="&yyear="+q.get_year();



        //dday         mmonth                 hour         minute


        String tt="";

        if ( q.get_tarifcar().equals("км эконом 100/16") )   {    d1="&tarifcar=0";   }
        if ( q.get_tarifcar().equals("км норма 130/18") )    {    d1="&tarifcar=1";   }
        if ( q.get_tarifcar().equals("км комфорт 150/20") )   {    d1="&tarifcar=2";   }
        if ( q.get_tarifcar().equals("км vip 200/25") )   {    d1="&tarifcar=3";   }

        if ( q.get_tarifcar().equals("часы эконом 360") )    {    d1="&tarifcar=4";   }
        if ( q.get_tarifcar().equals("часы норма 420") )   {    d1="&tarifcar=5";   }
        if ( q.get_tarifcar().equals("часы комфорт 480") )   {    d1="&tarifcar=6";   }
        if ( q.get_tarifcar().equals("часы vip 700") )   {    d1="&tarifcar=7";   }




        if (q.get_userrem()!=null) { d3="&userrem="+q.get_userrem();                                            }

        if (q.get_kondic()!=null) {         kondic="&kondic="+q.get_kondic();                                            }
        if (q.get_kurevo()!=null) { kurevo="&kurevo="+q.get_kurevo();                                             }
        if (q.get_baby()!=null) { baby="&baby="+q.get_baby();                                                     }


            Log.v("zakaz_send====1",q.get_dop_uslugi());

            if (  q.get_dop_uslugi().indexOf( "ез дополнительных услуг" )>0) { d2="&uslugi=0"  ;  }
            if (  q.get_dop_uslugi().indexOf( "оставка продуктов, обедов" )>0) { d2="&uslugi=1"  ;  }
            if (  q.get_dop_uslugi().indexOf( "оставка пиццы" )>0) { d2="&uslugi=2"  ;  }
            if (  q.get_dop_uslugi().indexOf( "оставка «Макдональдс»" )>0) { d2="&uslugi=3"  ;  }
            if (  q.get_dop_uslugi().indexOf( "оставка цветов" )>0) { d2="&uslugi=4"  ;  }
            if (  q.get_dop_uslugi().indexOf( "оставка багаж" )>0) { d2="&uslugi=5"  ;  }
            if (  q.get_dop_uslugi().indexOf( "оставка на сумму свыше 1500 руб." )>0) { d2="&uslugi=6"  ;  }
            if (  q.get_dop_uslugi().indexOf( "еревозка животных" )>0) { d2="&uslugi=7"  ;  }



        //  mcena="&mcena="+document.getElementById("mcena").innerHTML;
        // skidka="&skidka="+document.getElementById("skidka").innerHTML;
        // cenadoptext="&cenadoptext="+document.getElementById("cenadoptext").innerHTML;
        //  mtime="&mtime="+document.getElementById("mtime").innerHTML;
        // mkm="&mkm="+document.getElementById("mkm").innerHTML;
        yandx = "";//mcena+skidka+cenadoptext+mtime+mkm;
        //

        send =  "http://taxi.ru/i/io.php?l="+q.getPhone()+"&p="+q.getPas()+"&o=1&ts=1";//вызов такси прямо сейчас
          if (q.get_year()>=12)
          {
                send =  "http://taxi.ru/i/io.php?l="+q.getPhone()+"&p="+q.getPas()+"&o=1";//вызов такси прямо сейчас
          }

        send=send+a1+a2+a3+b1+b2+b3+c1+c2+c3+c4+c5+d1+d2+d3+yandx+kondic+kurevo+baby;
        send=send.replaceAll(" ","%20");

        Log.v("zakaz_send====1",send);

        q.setUrl(send);



/////////////////////// http поток
        dialog1 = new ProgressDialog(this);
        dialog1.setMessage("Отправляем заказ..."); // Получение статуса заказа...
        dialog1.setIndeterminate(true);
        dialog1.setCancelable(true);
        dialog1.show();



        mt1 = new HttpClient1();
        mt1.execute((Void)null);

 /////////////////////// http поток

        Log.v("zakaz_send====","заказ успешко сохранен переходим на ожидание");
        //если заказ успешко сохранен переходим на ожидание


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
            Log.v("HttpClient1====","doInBackground");
            try {
                res=  WebHttpGet.executeHttpGet(method).trim();
            } catch (Exception e) {
              res= "нет интернет соединения";
                back();
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

    public class HttpClient2 extends AsyncTask<Void, Integer, Long>{


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
               res= "нет интернет соединения";
            }
            //

             q.set_2(res);

            return null;
        }

        // как только получили ответ от сервера, выключаем ProgressBar
        protected void onPostExecute(Long unused) {

            // progressBar.setVisibility(View.INVISIBLE);





            Log.v("HttpClient2====","onPostExecute");
            HttpClient2_end();



            super.onPostExecute(unused);
        }



    }

    public class HttpClient3 extends AsyncTask<Void, Integer, Long>{



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
               res=  "нет интернет соединения";
            }


            q.set_3(res);


            return null;
        }

        // как только получили ответ от сервера, выключаем ProgressBar
        protected void onPostExecute(Long unused) {

            // progressBar.setVisibility(View.INVISIBLE);





            Log.v("HttpClient3====","onPostExecute");
            HttpClient3_end();



            super.onPostExecute(unused);
        }



    }

    private void HttpClient1_end() {
        dialog1.dismiss();
        Singleton q = Singleton.getInstance();
        String res=q.get_1();

        if (res.indexOf("интернет соединения")>0)
        {
            Toast.makeText(this, "нет интернет соединения", Toast.LENGTH_SHORT).show();
        }

        STCall();
    }

    private void HttpClient2_end() {
      //  dialog2.dismiss();
        Singleton q = Singleton.getInstance();
        String res=q.get_2();

        if (res.indexOf("интернет соединения")>0)
        {
            Toast.makeText(this, "нет интернет соединения", Toast.LENGTH_SHORT).show();
        }
        else
            {
            res=res.replace("</br></br><a href=\"flow_otmena.php\" rel=\"external\">отменить заказ</a>","");
            res=res.replace("</br></br>","\n");
            res=res.replace("</br>","\n");

            //q.set_beep_status(  q.get_beep_status()+1 );

            String out=res;
            boolean beep=false;

          //  Log.v("HttpClient2_end====","HttpClient2_end");
            //Log.v("statusRES====",out);
            Log.v("res=====",res);

            if (res.equals("Ищем водителя, пожалуйста ожидайте ..."))
            {
                igem=true;
                otmena.setVisibility(View.VISIBLE);
                out="Ищем водителя,\nпожалуйста ожидайте ...";
            }

            if (res.equals("Ваш заказ принят диспетчером, пожалуйста ожидайте."))
            {

                if (igem==true)
                {
                    otmena.setVisibility(View.VISIBLE);
                    out="Ваш заказ принят диспетчером,\nпожалуйста ожидайте.";
                    beep=true;
                    timer_stop();
                    nazad();
                }
            }

            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            if (res.indexOf("выехала машина")>0)
            {
                if (igem==true)
                {


                    if (status.getText().toString().indexOf("выехала машина")>0)
                    {
                    }
                    else
                    {
                        beep=true;
                    }

                    otmena.setVisibility(View.GONE);
                }
            }

            if (res.indexOf("ожидает машина")>0)
            {
                if (igem==true)
                    {


                    otmena.setVisibility(View.GONE);

                    if (status.getText().toString().indexOf("ожидает машина")>0)
                    {
                    }
                    else
                    {
                        beep=true;
                    }
                }
            }
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            Log.v("1завершен=",String.valueOf(  res.indexOf("завершен")  ));
            if (res.indexOf("завершен")>0)
            {
                if (igem==true)
                {
                     Log.v("2завершен=",out);
                    zavershen=true;
                    otmena.setVisibility(View.VISIBLE);
                    beep=true;
                    timer_stop();
                    nazad();
                }
            }



            status.setText(out);

            if (beep)
            {
                android.media.ToneGenerator tg=new ToneGenerator(AudioManager.STREAM_ALARM, 100);
                tg.startTone(ToneGenerator.TONE_CDMA_ABBR_ALERT);

            }
        }
    }

    private void HttpClient3_end() {
        Log.v("HttpClient3====","back");
        dialog3.dismiss();
        back();
    }



}
