package com.example;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.location.Location;
import android.widget.ProgressBar;
import android.widget.Toast;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import android.content.SharedPreferences;
import android.content.Context;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.*;
import java.util.Timer;
import java.util.TimerTask;

public class fast extends Activity  implements View.OnClickListener {
    Button vxod,hys,dop,call,callphone,gps,yanakarte;

    EditText ui_gorod, ui_ulica, ui_dom;

  //  public int auth_i=0;

    private LocationManager locManager;         //GPSMAIN
    private LocationListener locListener = new MyLocationListener();//GPSMAIN

    SharedPreferences sPref;
    //SharedPreferences settings ;
  //  private static SharedPreferences.Editor editor = null;

    private String h_phone,h_pas;

    Timer myTimer = new Timer();

    public boolean mestopologenie=false; //GPSMAIN

//
SocketClient SocketMt1;

    HttpClient1 mt1;
    HttpClient2 mt2;
    HttpClient3 mt3;

    HttpClientGPSSAVE mtGPSSAVE;     //GPSMAIN

    //
    private boolean onegps=false;

    //
    private boolean user_press_call;
    private boolean user_press_dop;
    private boolean user_press_hys;
//



    // объявляем диалог
  //  public ProgressDialog dialog;

    public ProgressDialog dialog1;
    public ProgressDialog dialog2;
    public ProgressDialog dialog3;

    public ProgressDialog dialogS;


    // контекст родительского класса
    // Context ctx;
    private boolean onCreateRunned = false;


    // объявляем диалог
  //  public ProgressBar progressBar;
    // контекст родительского класса
   // public  Context ctx;
  //  ProgressDialog dialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fast);
        onCreateRunned = true;
        Singleton q = Singleton.getInstance();


        q.set_yanakarte(false);

        q.set_a(true);
        q.set_tarifcar("км эконом 100/16");
        q.set_dop_uslugi("без дополнительных услуг");
        q.set_userrem("");
        q.set_kondic("");
        q.set_kurevo("");
        q.set_baby("");
        q.set_HysType(1);
        q.set_hys_back_screen(1);
        user_press_call=false;
        user_press_dop=false;
        user_press_hys=false;
        vxod=(Button) findViewById(R.id.vxod);
        vxod.setOnClickListener(this);
        q.set_call_status(0);
        q.set_button_press(0);
        hys=(Button) findViewById(R.id.hys);
        hys.setOnClickListener(this);
        dop=(Button) findViewById(R.id.dop);
        dop.setOnClickListener(this);

        call=(Button) findViewById(R.id.call);
        call.setOnClickListener(this);


        
        callphone=(Button) findViewById(R.id.callphone);
        callphone.setOnClickListener(this);

        yanakarte=(Button) findViewById(R.id.yanakarte);
        yanakarte.setOnClickListener(this);



        gps=(Button) findViewById(R.id.gps);
        gps.setOnClickListener(this);

        ui_gorod= (EditText)findViewById(R.id.gorod);
        ui_ulica= (EditText)findViewById(R.id.ulica);
        ui_dom= (EditText)findViewById(R.id.dom);

        if (q.get_otkuda_gorod()!=null) {         ui_gorod.setText(q.get_otkuda_gorod());  }
        if (q.get_otkuda_ulica()!=null) {  ui_ulica.setText(q.get_otkuda_ulica()); }
        if (q.get_otkuda_dom()!=null) {  ui_dom.setText(q.get_otkuda_dom()); }


        if ( q.get_auth_status() == true )
        {
            vxod.setText("выход");
        }

       // fa=true;


        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //Singleton q = Singleton.getInstance();
        sPref = getSharedPreferences("info",MODE_PRIVATE);
        String h_phone = sPref.getString("phone","");
        String h_pas = sPref.getString("pas","");

        if (h_phone!=null && h_pas!=null)
        {
            q.setPhone(h_phone);
            q.setPas(h_pas);
        }
        if (q.get_auth_status()!=true)
        {
            auth_popitka(); //поток 1 //ВКЛЮЧИТЬ XXX
        }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        q.set_gpstimer(true);
        q.set_gpstimer_socketend(true);
        ST();
        //gpsserver();
        LocationManager myManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        myManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locListener);
        Location  gps_loc=myManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);


    }


    @Override
    protected void onResume() {


        super.onResume();





        Singleton q = Singleton.getInstance();
        if ( q.get_yanakarte() )
        {

           // Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();


            SocketMt1 = new SocketClient();
            SocketMt1.execute((Void)null);



        }
     //   Log.d("!==", "MainActivity: onResume()");

      //  if (fa==false)
        //{
           // active_zakaz();
        }

 //   @Override
  //  protected void onResume() {
    //    super.onResume();
      //  if(onCreateRunned){
         //   onCreateRunned = false; //important, or it will run only once.
        ///}
         //else
       // {
        // Do your code
           // Log.d("!==", "MainActivity: onResume()");
         //    active_zakaz();
            // Do your code
       // }
   // }
    //}










    private void ST() {

        final Handler uiHandler = new Handler();
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                uiHandler.post(new Runnable() {
                    @Override
                    public void run() {





                        Singleton q = Singleton.getInstance();


                        Log.v("time===","1");

                        if (  !q.get_gpstimer()  )
                        {

                            if (  q.get_gpstimer_socketend()  )
                            {
                                q.set_gpstimer(true);
                                Log.v("time===","2");
                            }

                        }




                    }
                });
            }



            ;
        }, 0L, 3000);

    }




    private void posle_active_zakaz_end()
    {
        Singleton q = Singleton.getInstance();
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////   posle_active_zakaz_end
        if (q.get_otkuda_gorod()==null)
        {
            ui_gorod.setText("Москва");
        }

        if (q.get_otkuda_ulica()==null)
        {
            ui_ulica.setText("улица");

        }

        if (q.get_otkuda_dom()==null)
        {
            ui_dom.setText("дом/подъезд");
        }

        ui_gorod.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (ui_ulica.getText().toString().trim().toLowerCase().equals(""))
                {
                    ui_ulica.setText("улица");
                }

                if (ui_dom.getText().toString().trim().toLowerCase().equals(""))
                {
                    ui_dom.setText("дом/подъезд");
                }

                if (ui_gorod.getText().toString().trim().toLowerCase().equals("москва"))
                {
                    ui_gorod.setText("");
                }
                return false;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });

        ui_ulica.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent motionEvent) {

                if (ui_gorod.getText().toString().trim().toLowerCase().equals(""))
                {
                    ui_gorod.setText("москва");
                }

                if (ui_dom.getText().toString().trim().toLowerCase().equals(""))
                {
                    ui_dom.setText("дом/подъезд");
                }

                if (ui_ulica.getText().toString().trim().toLowerCase().equals("улица"))
                {
                    ui_ulica.setText("");
                }

                return false;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });

        ui_dom.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent motionEvent) {

                if (ui_gorod.getText().toString().trim().toLowerCase().equals(""))
                {
                    ui_gorod.setText("москва");
                }

                if (ui_ulica.getText().toString().trim().toLowerCase().equals(""))
                {
                    ui_ulica.setText("улица");
                }

                if (ui_dom.getText().toString().trim().toLowerCase().equals("дом/подъезд"))
                {
                    ui_dom.setText("");
                }
                return false;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });


        if (q.get_focus()!=10)
        {
            Log.v("gps===","1");
            //gps
            LocationManager myManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            myManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locListener);
            Location  gps_loc=myManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            if (gps_loc!=null)   //2
            {
                Log.v("gps===","2");
                q.setX(gps_loc.getLatitude());
                q.setY(gps_loc.getLongitude());
                q.setAccuracy( gps_loc.getAccuracy() );

                //yandex();
                //gpsserver();
            }
            //gps
        }

        ui_ulica.requestFocus();

        if (q.get_call_status()==1)
        {
            zakaz_send();
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    posle_active_zakaz_end
    }






    private void auth_popitka() {
        Log.v("fast ========","auth_popitka");


        Singleton q = Singleton.getInstance();


        if (q.getPhone()!=null && q.getPas()!=null)
        {

            /////////////////////// http поток
                dialog1 = new ProgressDialog(this);
            dialog1.setMessage("Вход...");
            q.set_http_s(1);//   auth_popitka
                dialog1.setIndeterminate(true);
                dialog1.setCancelable(true);
                dialog1.show();

            mt1 = new HttpClient1();
             mt1.execute((Void)null);

            /////////////////////// http поток


        }


    }

    public class HttpClient1 extends AsyncTask<Void, Integer, Long>{

        // запускаем ProgressBar в момент запуска потока
        protected void onPreExecute() {
            Log.v("main=","1");
        }

        // сама работа потока, SendHttpPost() - наш долгоработающий метод
        protected Long doInBackground(Void... params) {
            BufferedReader in = null;
            Singleton q = Singleton.getInstance();
            String page="";

                String res="";
                q.setUrl(  "http://taxi.ru/i/io.php?l="+q.getPhone()+"&p="+q.getPas() +"&o=89"  );

                HttpGet method = new HttpGet(    q.getUrl()      );
                try {
                    res=  WebHttpGet.executeHttpGet(method).trim();

                } catch (Exception e) {

                    res="нет интернет соединения";
                }

            Log.v("auth_popitka_end",q.getUrl());
            Log.v("auth_popitka_end_res",res );

            q.set_1(res);

            return null;
        }

        // как только получили ответ от сервера, выключаем ProgressBar
        protected void onPostExecute(Long unused) {
            Singleton q = Singleton.getInstance();
                    Log.v("onPostExecute===","auth_popitka_end");
            Log.v("main=","2");
                    auth_popitka_end();
            super.onPostExecute(unused);
        }

    }

    public class HttpClient2 extends AsyncTask<Void, Integer, Long>{


        protected void onPreExecute() {

        }


        protected Long doInBackground(Void... params) {

            //process
            Singleton q = Singleton.getInstance();
            q.setUrl("http://geocode-maps.yandex.ru/1.x/?geocode="+q.getX()+","+q.getY());
            String res="";
            HttpGet method = new HttpGet(q.getUrl());
            try {
                res=  WebHttpGet2.executeHttpGet(method).trim();
            } catch (Exception e) {
                res="нет интернет соединения";
            }
            //process

            return null;
        }

        protected void onPostExecute(Long unused) {
                    yandex_end();
           super.onPostExecute(unused);
        }

    }

    private void active_zakaz() {  //проверяем есть ли активный заказ




        /////////////////////// http поток
        dialog3 = new ProgressDialog(this);
        dialog3.setMessage("Инициализация..."); // Получение статуса заказа...
        dialog3.setIndeterminate(true);
        dialog3.setCancelable(true);
        dialog3.show();

        mt3 = new HttpClient3();
        mt3.execute((Void)null);

        /////////////////////// http поток


    }

    public class HttpClient3 extends AsyncTask<Void, Integer, Long>{




        protected void onPreExecute() {

        }


        protected Long doInBackground(Void... params) {



            //process
            Singleton q = Singleton.getInstance();
            q.setUrl3(   "http://taxi.ru/i/io.php?l=" + q.getPhone() + "&p=" + q.getPas() + "&o=5"   );
            String res="";
            HttpGet method3 = new HttpGet(   q.getUrl3()  );
            try {
                res=  WebHttpGet3.executeHttpGet(method3).trim();
            } catch (Exception e) {
                res="нет интернет соединения";
            }
            //process



            q.set_6(res);
            return null;
        }


        protected void onPostExecute(Long unused) {
                    Log.v("onPostExecute===","active_zakaz_end");

                    active_zakaz_end();
            super.onPostExecute(unused);
        }



    }

    public  void  active_zakaz_end()
    {
        dialog3.dismiss();
        //dialog=null;

        Singleton q = Singleton.getInstance();
        String res = q.get_6();

        Log.v("gg==3",res) ;

        if (res.indexOf("интернет соединения")>0)
        {
            boolean fff=false;
            if (user_press_call)             {                 fff=true;             }
            if (user_press_dop)             {                 fff=true;             }
            if (user_press_hys)                                                      {                 fff=true;             }
            if (fff)  {   Toast.makeText(this, "нет интернет соединения", Toast.LENGTH_SHORT).show();    }
        }



        if (res.equals("1"))//есть активный заказ переходим
        {
            Log.v("gg==4===есть активный заказ переходим","zakaz_send()") ;
            //zakaz_send();
            //  Singleton q = Singleton.getInstance();

            q.set_focus(10);
            q.set_a(false);

            Intent intent = new Intent();
            intent.setClass(this, call2.class);
            startActivity(intent);
            finish();
        }
        else
        {

            posle_active_zakaz_end();

        }
    }






    private void auth_popitka_end() {
        int ir;
        dialog1.dismiss();


        Log.v("auth_popitka_end","1");

        Singleton q = Singleton.getInstance();
        String res=q.get_1();

        if (res.indexOf("интернет соединения")>0)
        {
            Log.v("auth_popitka_end","нет интернет соединения21");
            boolean fff=false;
            if (user_press_call)             {                 fff=true;             }
            if (user_press_dop)             {                 fff=true;             }
            if (user_press_hys)                                                      {                 fff=true;             }
            if (fff)  {   Toast.makeText(this, "нет интернет соединения", Toast.LENGTH_SHORT).show();    }
        }

        Log.v("auth_popitka_end_res",res);

        try
        {
            ir=Integer.parseInt( res );
        }
        catch (NumberFormatException e)
        {
            e.printStackTrace();
            ir=2;
        }


        if (ir==8)//1 8
        {
            Log.v("auth_popitka_end","клиент");

            q.set_auth_status(true);
            vxod.setText("выход");

            if (q.get_auth_status()==true)
            {
                vxod.setText("выход");
            }

            active_zakaz();   //поток 2
        }


        if (ir==9)//3
        {
            Log.v("auth_popitka_end","водитель");

            q.set_auth_status(true);
            voditel();

        }

        if (q.get_auth_status()==false)
        {
            posle_active_zakaz_end();
        }

    }



    private void yandex_end ()
    {
        dialog2.dismiss();

        Singleton q = Singleton.getInstance();
        String res = q.get_yandex();

        if (res.indexOf("интернет соединения")>0)
        {
            boolean fff=false;
            if (user_press_call)             {                 fff=true;             }
            if (user_press_dop)             {                 fff=true;             }
            if (user_press_hys)                                                      {                 fff=true;             }
            if (fff)  {   Toast.makeText(this, "нет интернет соединения", Toast.LENGTH_SHORT).show();    }
        }

        //end
        String gorod=null;
        String ulica=null;
        String dom=null;

        int i1 = res.indexOf("<LocalityName")+28;
        int i2 = res.indexOf("</LocalityName>");
        if (i1>0 && i2>0)
        {
            gorod = res.substring(i1,i2) ;
            ui_gorod= (EditText)findViewById(R.id.gorod);
            ui_gorod.setText(gorod);
        }

        i1 = res.indexOf("<ThoroughfareName")+32;
        i2 = res.indexOf("</ThoroughfareName>");
        if (i1>0 && i2>0)
        {
            ulica = res.substring(i1,i2) ;
            ui_ulica= (EditText)findViewById(R.id.ulica);
            ui_ulica.setText(ulica);
        }

        i1 = res.indexOf("<PremiseNumber")+15;
        i2 = res.indexOf("</PremiseNumber>");
        if (i1>0 && i2>0)
        {
            dom = res.substring(i1,i2) ;
            ui_dom= (EditText)findViewById(R.id.dom);
            ui_dom.setText(dom);
        }

        q.set_otkuda_gorod(gorod);
        q.set_otkuda_ulica(ulica);
        q.set_otkuda_dom(dom);

        verify();
        //end

    }




    private void voditel() {
        Log.v("fast===","voditel");
        // .makeText(this, "водитель2",  .LENGTH_SHORT).show();
        Intent intent = new Intent();

        intent.setClass(this, zakazi.class);

        startActivity(intent);

        finish();


    }

    private void yandex() {


        /////////////////////// http поток
        dialog2 = new ProgressDialog(this);
        dialog2.setMessage("Определение местоположения");
        dialog2.setIndeterminate(true);
        dialog2.setCancelable(true);
        dialog2.show();

     //   q.set_http_s(2);//   yandex

     //   mt2 = new HttpClient2();
       // mt2.execute((Void)null);
        /////////////////////// http поток




    }

    private void verify() {

        Log.v("fast===","verify") ;

        Singleton q = Singleton.getInstance();

        //ui2sigleton();
          save_otkuda();

        String gorod=q.get_otkuda_gorod();
        String ulica=q.get_otkuda_ulica();
        String dom=q.get_otkuda_dom();

        int f=0;
        int fdom=0;
        String plus="укажите пожалуйста ";

        if (ulica.equals("улица") || ulica.trim().equals(""))
        {
            plus=plus+"улицу";
            f=f+1;
        }

        if (dom.equals("дом/подъезд") || dom.trim().equals(""))
        {
            if (f==1)
            {
                plus=plus+", ";
            }
            plus=plus+"дом и подъезд";
            f=f+1;
            fdom=1;
        }


        if (f>0)
        {
            boolean fff=false;
            if (user_press_call)             {                 fff=true;             }
            if (user_press_dop)             {                 fff=true;             }
            if (user_press_hys)                                                      {                 fff=true;             }
            if (fff)
            {
                Toast.makeText(this, plus, Toast.LENGTH_SHORT).show();
            }
          //  if (user_press_hys)
            //{
              //  Toast.makeText(this, plus, Toast.LENGTH_SHORT).show();
           // }
        }
        else
        {
           q.set_call_status(1);
        }
       // Log.v("fast===",    Integer.toString    ( q.get_button_press() ) );

        if (f>1 && fdom==0)
        {
            ui_ulica.requestFocus();
        }
        if (f==1 && fdom==1)
        {
            ui_dom.requestFocus();
        }

    }

    public void onClick(View v) {

        Log.v("fast===","onClick") ;

        switch (v.getId())
        {
            case (R.id.vxod):
                vxod();
                break;

            case (R.id.hys):
                hys();
                break;

            case (R.id.dop):
                dop();
                break;

            case (R.id.call):
                call();
                break;


            case (R.id.callphone):
                callphone();
                break;

            case (R.id.yanakarte):
                yanakarte();
                break;


            case (R.id.gps):
                gps();
                break;
        }
    }

    private void yanakarte() {

        Singleton q = Singleton.getInstance();
        q.set_yanakarte(true);

        Intent intent = new Intent();
        intent.setClass(this, yanakarte2.class);
        startActivity(intent);
        //finish();
    }

    private void callphone() {


            try {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:495"));
                startActivity(callIntent);
            } catch (ActivityNotFoundException e) {
                //Log.e("helloandroid dialing example", "Call failed", e);
            }

    }

    private void gps() {
        Singleton q = Singleton.getInstance();
        q.set_gpstimer_socketend(false);
        q.set_gpstimer(false);
        gpsserver();

    }

    public void vxod()    {

        Log.v("fast===","vxod") ;

        save_otkuda();

        Singleton q = Singleton.getInstance();

        if (q.get_auth_status())
        {
            moveTaskToBack(true);
            System.exit(0);
        }
        else
        {
            Intent intent = new Intent();
            intent.setClass(this, auth.class);
            startActivity(intent);
        }

    }

    private void save_otkuda() {

        Log.v("fast===","save_otkuda") ;

        Singleton q = Singleton.getInstance();
        q.set_otkuda_gorod(ui_gorod.getText().toString() );
        q.set_otkuda_ulica(ui_ulica.getText().toString() );
        q.set_otkuda_dom(ui_dom.getText().toString() );
    }

    public void hys()    {

        Log.v("fast===","hys") ;

        user_press_hys=true;

        save_otkuda();

        Singleton q = Singleton.getInstance();

        q.set_button_press(3);

        if (q.get_auth_status()==false)
        {
            Intent intent = new Intent();
            intent.setClass(this, auth.class);
            startActivity(intent);
        }
        else
        {
           // q.set_HysType(1);

            //q.set_hys_back_screen(1);
            Log.v("fast back() ========== q.get_hys_back_screen ",String.valueOf(q.get_hys_back_screen()));
                Intent intent = new Intent();
                intent.setClass(this, hystory.class);  //история     hystory   zakazi
                startActivity(intent);
        }

    }

    private void dop() {

        Log.v("fast===","dop") ;
        user_press_dop=true;
        Singleton q = Singleton.getInstance();

        save_otkuda();

        verify();

        q.set_button_press(1);

        if (q.get_auth_status()==false)
        {
            Intent intent = new Intent();
            intent.setClass(this, auth.class);
            startActivity(intent);
        }
        else
        {
            if (q.get_call_status()==1){
                Intent intent = new Intent();
                intent.setClass(this, MyActivity.class);
                startActivity(intent);
            }

        }

    }

    private void call()
    {
        Log.v("call===","call") ;

        user_press_call=true;

        Singleton q = Singleton.getInstance();

        //ui2sigleton();
        save_otkuda();

        //автоматический переход сраху на call2 можем делать если все в порядке с откуда

        //



        if (q.get_auth_status()==false)
        {
            verify();

            q.set_button_press(2);

            if (q.get_call_status()==1)
            {
                q.set_button_press(4);
            }

            Intent intent = new Intent();
            intent.setClass(this, auth.class);
            startActivity(intent);
        }

        else

        {

            verify();


            if (q.get_call_status()==1)
            {

                zakaz_send();

            }


        }
    }

    public void finish(){
        Log.v("press back===", "001"    );
    }

    private void zakaz_send() {

        Log.v("fast===","zakaz_send") ;

        Singleton q = Singleton.getInstance();

        if (q.get_call_status()==1)
        {

            goto_call2();
        }
    }

    private void goto_call2() {

if (user_press_call)
{
    //отключаем все таймеры
    if (myTimer!=null)
    {
        myTimer.cancel();
        myTimer.purge();
        myTimer=null;
    }

    //отключаем все таймеры


    Log.v("q.set_focus(10)====", "goto_call2") ;

        Singleton q = Singleton.getInstance();

        q.set_focus(10);

        Intent intent = new Intent();
        intent.setClass(this, call2.class);
        startActivity(intent);
        finish();
}
    }






    public class SocketClient extends AsyncTask<Void, Integer, Long>{

        // запускаем ProgressBar в момент запуска потока
        protected void onPreExecute() {

        }

        // сама работа потока, SendHttpPost() - наш долгоработающий метод
        protected Long doInBackground(Void... params) {

             //google & gpsserver = php script


            Singleton q = Singleton.getInstance();

            q.set_otkuda_gorod("-");//ОТКЛЮЧИТЬ
            q.set_otkuda_ulica("-");
            q.set_otkuda_dom("-"  );


            String st = "---";
            try {
              //  Socket s = new Socket("192.168.0.66",11000);
                Socket s = new Socket("54.243.249.206",11000);
                BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
                //send output msg
//                String outMsg = location.getLatitude()+"," + location.getLongitude() +"<EOF>";


                String outMsg = q.getY()+"," + q.getX() +"<EOF>"+   String.format("%.1f", q.getAccuracy()  )    + "<L>"+q.getPhone()+"<P>"+q.getPas();
                out.write(outMsg);
                out.flush();
                Log.i("TcpClient===", "sent: " + outMsg);

                //accept server response
                String inMsg = in.readLine() + System.getProperty("line.separator");
                Log.i("TcpClient===", "received: " + inMsg);

                 st=inMsg;



                //close connection
                s.close();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (st.indexOf("alse")>0)
            {

            }
                else
            {
                Integer i1 = st.indexOf("[0]");
                Integer i2 = st.indexOf("[1]");

                q.set_otkuda_gorod(st.substring(0, i1 ));
                q.set_otkuda_ulica(st.substring( i1+3,i2 ));
                 q.set_otkuda_dom(st.substring(i2+3,st.length())  );
            }



            return null;
        }

        // как только получили ответ от сервера, выключаем ProgressBar
        protected void onPostExecute(Long unused) {


            SocketClient_End();
            super.onPostExecute(unused);

        }

    }

    private void SocketClient_End() {

        Singleton q = Singleton.getInstance();

        ui_gorod.setText(  q.get_otkuda_gorod() );
        ui_ulica.setText(  q.get_otkuda_ulica() );
        ui_dom.setText(  q.get_otkuda_dom() );

        if ( q.get_yanakarte() )
        {
            q.set_yanakarte(false);
        }
        else
        {
            q.set_gpstimer_socketend(true);
        }

        save_gps();

       // Log.v("TcpClient===SocketClient_End_2",   q.get_otkuda_ulica() )   ;
    }



















       //СКОПИРОВАТЬ В ВОДИТЕЛЯ
    private void save_gps() {
        //q.getX(),q.getY(),q.get_otkuda_gorod(),q.get_otkuda_ulica(),q.get_otkuda_dom()
        /////////////////////// http поток
        mtGPSSAVE = new HttpClientGPSSAVE();
        mtGPSSAVE.execute((Void)null);
        /////////////////////// http поток
    }




    public class HttpClientGPSSAVE extends AsyncTask<Void, Integer, Long>{

        // запускаем ProgressBar в момент запуска потока
        protected void onPreExecute() {
            Log.v("GPSSAVE===",   "1")   ;
        }

        // сама работа потока, SendHttpPost() - наш долгоработающий метод
        protected Long doInBackground(Void... params)  {

            //BufferedReader in = null;
            Singleton q = Singleton.getInstance();
            //String page="";

            String res="";
         //   q.setUrl7(  "http://taxi.ru/i/io.php?l="+q.getPhone()+"&p="+q.getPas() +"&o=55" +"&x="+q.getX()+"&y="+q.getY()+  encode_url      );


          //HttpGet method = new HttpGet(    q.getUrl7()      );
//            HttpGet method = new HttpGet(   "http://taxi.ru/i/io.php?l=89531107315&p=888&o=55&x=38.6&y=45.7&gorod=москва отделение совхоза 2-е СКНИИВ&ulica=Главная&dom=44(63817м)"     );
            //HttpGet method = new HttpGet(   "http://taxi.ru/i/io.php?l=89531107315&p=888&o=55&x=38.6&y=45.7&gorod=москва отделение совхоза 2-е СКНИИВ&ulica=Красная&dom=1"     );

            //String jsonText = EntityUtils.toString( q.getUrl7()    , HTTP.UTF_8 );
          //  String entity =   q.getUrl7() ;
            //String str = EntityUtils.toString(entity, "UTF-8");
          //  String url =  q.getUrl7();
            String e1= null;
            String e2= null;
            String e3= null;

            try {
                e1 = URLEncoder.encode(q.get_otkuda_gorod(),"UTF-8");
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


            String oo = "http://taxi.ru/i/io.php?l="+q.getPhone()+"&p="+q.getPas() +"&o=55" +"&x="+q.getX()+"&y="+q.getY()  +  "&gorod="+e1+      "&ulica="+e2+    "&dom="+e3;
               q.setUrl7(oo);
            Log.v("GPSSAVE1===", q.getUrl7())   ;
            HttpGet method = new HttpGet(       q.getUrl7()  );

            //кодировать строку в utf8

          try {
              //res=  WebHttpGETGPSSAVE.executeHttpGet(method).trim();
              res=  WebHttpGETGPSSAVE.executeHttpGet(method).trim();

          } catch (Exception e) {

              res="нет интернет соединения";
          }

            Log.v("GPSSAVE===",   "2")   ;
//            q.set_1(res);

            return null;
        }

        // как только получили ответ от сервера, выключаем ProgressBar
        protected void onPostExecute(Long unused) {
            HttpClientGPSSAVE_END();
            Log.v("GPSSAVE===",   "3")   ;
            super.onPostExecute(unused);
        }

    }


    private void HttpClientGPSSAVE_END() {
        Log.v("GPSSAVE===",   "4")   ;
        dialogS.dismiss();
    }

    //СКОПИРОВАТЬ В ВОДИТЕЛЯ

















    class MyLocationListener implements LocationListener {
       // public ProgressDialog dialogS;
        @Override
        public void onLocationChanged(Location location) {

            Log.v("TcpClient===","onLocationChanged")   ;

            if (location != null) {
            //    locManager.removeUpdates(locListener);
                                              //1
                Singleton q = Singleton.getInstance();
                q.setX(location.getLatitude());
                q.setY(location.getLongitude());
                q.setAccuracy( location.getAccuracy() );

                if (!onegps)     //location + socket проходит только один раз больше только по нажатию кнопки GPS обновить
                {
                     onegps=true;
                      //  yandex();
                        //  gpsserver//  gpsserver//  gpsserver//  gpsserver//  gpsserver//  gpsserver//  gpsserver//  gpsserver//  gpsserver//  gpsserver//  gpsserver//  gpsserver//  gpsserver//  gpsserver//  gpsserver//  gpsserver

                        ///////////////////////   поток
                     //   dialogS = new ProgressDialog(this);
                       // dialogS.setMessage("Определение адреса...");

                      //  dialogS.setIndeterminate(true);
                      //  dialogS.setCancelable(true);
                      //  dialogS.show();
                        if (q.get_gpstimer())
                        {

                            if (  q.get_gpstimer_socketend()  )
                            {
                                q.set_gpstimer_socketend(false);
                                q.set_gpstimer(false);
                                gpsserver();
                            }


                        }

                        ///////////////////////   поток
                        //  gpsserver//  gpsserver//  gpsserver//  gpsserver//  gpsserver//  gpsserver//  gpsserver//  gpsserver//  gpsserver//  gpsserver//  gpsserver//  gpsserver//  gpsserver//  gpsserver//  gpsserver//  gpsserver
                        }


            }
        }
        @Override
        public void onProviderDisabled(String provider) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onProviderEnabled(String provider) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            // TODO Auto-generated method stub

        }
    }

    private void gpsserver() {

        dialogS = new ProgressDialog(this);
        dialogS.setMessage("Определение местоположения");
        dialogS.setIndeterminate(true);
        dialogS.setCancelable(true);
        dialogS.show();


        SocketMt1 = new SocketClient();
        SocketMt1.execute((Void)null);

    }

}
