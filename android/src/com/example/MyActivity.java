package com.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
//import android.widget.Toast;
// Toast.makeText(this, "It works", Toast.LENGTH_SHORT).show();

public class MyActivity extends Activity  implements View.OnClickListener {
    /**
     * Called when the activity is first created.
     */
    WebView browser;
    Button vxod,karta,otkuda,kuda,kogda,tarif,kuponi,dop,call,skidki,priglasit,pravila;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        Log.v("MyActivity", "onCreate1");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

      //  update_webbrowser();

        Singleton q = Singleton.getInstance();
        q.set_focus(20) ;
        otkuda=(Button) findViewById(R.id.otkuda);
        otkuda.setOnClickListener(this);

        kuda=(Button) findViewById(R.id.kuda);
        kuda.setOnClickListener(this);

        kogda=(Button) findViewById(R.id.kogda);
        kogda.setOnClickListener(this);

        tarif=(Button) findViewById(R.id.tarif);
        tarif.setOnClickListener(this);

     //   kuponi=(Button) findViewById(R.id.kuponi);
       // kuponi.setOnClickListener(this);

        dop=(Button) findViewById(R.id.dop);
        dop.setOnClickListener(this);

        call=(Button) findViewById(R.id.call);
        call.setOnClickListener(this);

        skidki=(Button) findViewById(R.id.skidki);
        skidki.setOnClickListener(this);

        pravila=(Button) findViewById(R.id.pravila);
        pravila.setOnClickListener(this);

        Log.v("MyActivity", "onCreate2");
        String tu=q.get_otkuda_ulica().replace("улица","ул.");
        otkuda.setText("ОТКУДА: "+q.get_otkuda_gorod()+" "+tu+" "+q.get_otkuda_dom());
        Log.v("MyActivity", "onCreate3");

        verify_kuda();

        verify_kogda();

        verify_tarif();

    }

    private void verify_tarif() {
        Singleton q = Singleton.getInstance();


            tarif.setText(  q.get_tarifcar().trim()  );


    }


    public void finish(){
        Log.v("press back===", "001"    );
    }


    private void verify_kogda() {
        Singleton q = Singleton.getInstance();

        if (       q.get_hour()  == 0 &&  q.get_minute()  == 0 &&  q.get_year()  == 0  &&  q.get_month()  == 0 &&  q.get_day()  == 0  )
        {
             Date live = java.util.Calendar.getInstance ().getTime();
             Date date = new Date(live.getTime() +  300000    );

                String hs=Integer.toString( date.getHours() );
                int hi = date.getHours();
                if (hi==0) { hs="00"; }
                if (hi==1) { hs="01"; }
                if (hi==2) { hs="02"; }
                if (hi==3) { hs="03"; }
                if (hi==4) { hs="04"; }
                if (hi==5) { hs="05"; }
                if (hi==6) { hs="06"; }
                if (hi==7) { hs="07"; }
                if (hi==8) { hs="08"; }
                if (hi==9) { hs="09"; }

                String ms=Integer.toString( date.getMinutes() );
                int mi = date.getMinutes();
                if (mi==0) { ms="00"; }
                if (mi==1) { ms="01"; }
                if (mi==2) { ms="02"; }
                if (mi==3) { ms="03"; }
                if (mi==4) { ms="04"; }
                if (mi==5) { ms="05"; }
                if (mi==6) { ms="06"; }
                if (mi==7) { ms="07"; }
                if (mi==8) { ms="08"; }
                if (mi==9) { ms="09"; }

                String dt=new java.text.SimpleDateFormat("dd.MM.yyyy").format(date);
                kogda.setText(   dt + " " + hs + ":" +  ms );

                q.set_hour( hi );
                q.set_minute( mi );

                q.set_day(  Integer.parseInt( new java.text.SimpleDateFormat("dd").format(date)  )   );
                q.set_month(  Integer.parseInt( new java.text.SimpleDateFormat("MM").format(date)  )   );
                q.set_year(  Integer.parseInt( new java.text.SimpleDateFormat("yyyy").format(date)  )   );

        }
        else
        {
                String hs=Integer.toString( q.get_hour() );
                int hi = q.get_hour();
                if (hi==0) { hs="00"; }
                if (hi==1) { hs="01"; }
                if (hi==2) { hs="02"; }
                if (hi==3) { hs="03"; }
                if (hi==4) { hs="04"; }
                if (hi==5) { hs="05"; }
                if (hi==6) { hs="06"; }
                if (hi==7) { hs="07"; }
                if (hi==8) { hs="08"; }
                if (hi==9) { hs="09"; }

                String ms=Integer.toString( q.get_minute() );
                int mi = q.get_minute();
                if (mi==0) { ms="00"; }
                if (mi==1) { ms="01"; }
                if (mi==2) { ms="02"; }
                if (mi==3) { ms="03"; }
                if (mi==4) { ms="04"; }
                if (mi==5) { ms="05"; }
                if (mi==6) { ms="06"; }
                if (mi==7) { ms="07"; }
                if (mi==8) { ms="08"; }
                if (mi==9) { ms="09"; }

                String dt =
                            Integer.toString(    q.get_day()   )   + "."+
                            Integer.toString(    q.get_month() ) +   "."+
                            Integer.toString(    q.get_year()  ) + " "+
                               hs + ":"+  ms  ;
                kogda.setText(dt);


        }

    }

    public void onClick(View v) {

        Log.v("MyActivity", "onClick");

        switch (v.getId())
        {


            case (R.id.otkuda):
                otkuda();
                break;
            case (R.id.kuda):
                kuda();
                break;
            case (R.id.kogda):
                kogda();
                break;
            case (R.id.tarif):
                tarif();
                break;
          //  case (R.id.kuponi):
            //    kuponi();
              //  break;
            case (R.id.dop):
                dop();
                break;
            case (R.id.call):
                call();
                break;
            case (R.id.skidki):
                skidki();
                break;
          //  case (R.id.priglasit):
            //    priglasit();
              //  break;
            case (R.id.pravila):
                pravila();
                break;



        }
    }


    public void vxod()
    {
        Log.v("MyActivity", "vxod");

        Intent intent = new Intent();
        intent.setClass(this, auth.class);
        startActivity(intent);

    }



    private void otkuda() {

        Log.v("MyActivity", "otkuda");

        Intent intent = new Intent();
        intent.setClass(this, otkuda.class);
        startActivity(intent);

    }

    private void kuda() {

        Log.v("MyActivity", "kuda");

        Intent intent = new Intent();
        intent.setClass(this, kuda.class);
        startActivity(intent);

    }

    private void kogda() {

        Log.v("MyActivity", "kogda");

        Intent intent = new Intent();
        intent.setClass(this, kogda.class);
        startActivity(intent);
        finish();
    }

    private void tarif() {

        Log.v("MyActivity", "tarif");

        Intent intent = new Intent();
        intent.setClass(this, tarif.class);
        startActivity(intent);

    }

    private void kuponi() {

        Log.v("MyActivity", "kuponi");

        Intent intent = new Intent();
        intent.setClass(this, kuponi.class);
        startActivity(intent);

    }

    private void dop() {

        Log.v("MyActivity", "dop");

        Intent intent = new Intent();
        intent.setClass(this, dop.class);
        startActivity(intent);

    }

    private void call() {

        Log.v("MyActivity", "call");

        Intent intent = new Intent();
        intent.setClass(this, call2.class);
        startActivity(intent);

    }

    private void skidki() {

        Log.v("MyActivity", "skidki");

        Intent intent = new Intent();
        intent.setClass(this, skidki.class);
        startActivity(intent);

    }

    private void pravila() {

        Log.v("MyActivity", "pravila");

        Intent intent = new Intent();
        intent.setClass(this, pravila.class);
        startActivity(intent);

    }

    private void update_webbrowser() {

        Log.v("MyActivity", "webbrowser");

        String send="";

        Singleton q = Singleton.getInstance();

        String f1,f2,f3,f4,f5,f6,tarif;

        f1="?f1="+q.get_otkuda_gorod();
        f2="&f2="+q.get_otkuda_ulica();
        f3="&f3="+q.get_otkuda_dom();

        f4="?f4="+q.get_kuda_gorod();
        f5="&f5="+q.get_kuda_ulica();
        f6="&f6="+q.get_kuda_dom();

        tarif="&tarif=0";

        String day= "&day="+Integer.toString( q.get_day()  );
        String hour="&hour="+ Integer.toString(      q.get_hour()  );
        String minute= "&minute="+Integer.toString( q.get_minute() );

        send="http://taxi.ru/mya3.php"+f1+f2+f3+f4+f5+f6+tarif+day+hour+minute;

        Log.v("yasend==",send);

        browser=(WebView)findViewById(R.id.webkit);
        browser.loadUrl(send);
        browser.getSettings().setJavaScriptEnabled(true);


    }

    private void verify_kuda() {

        Log.v("MyActivity","verify_otkuda1") ;


        Singleton q = Singleton.getInstance();

        String gorod,ulica,dom;

        if (q.get_kuda_gorod()==null)        {             gorod="москва";   q.set_otkuda_gorod(gorod);      }         else          {             gorod=q.get_kuda_gorod();          }
        if (q.get_kuda_ulica()==null)        {             ulica="";         }         else          {             ulica=q.get_kuda_ulica();          }
        if (q.get_kuda_dom()==null)        {             dom="";         }             else          {             dom=q.get_kuda_dom();          }

        Log.v("MyActivity","verify_otkuda2") ;


        if (ulica.toLowerCase().equals("улица") || ulica.trim().equals("")  || ulica==null)
        {

            ulica="";
        }

        if (dom.toLowerCase().equals("дом/подъезд") || dom.trim().equals("") || dom==null)
        {
            dom="";
        }

        Log.v("MyActivity","verify_otkuda5") ;

        ulica=ulica.replace("улица", "ул.");
        kuda.setText("КУДА: "+gorod+" "+ulica+" "+dom);


    }

}
