package com.example;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Location;
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

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class hystory extends ListActivity implements View.OnClickListener {

    Button back;

    private ListView list1;
    ArrayList<String> v=new ArrayList<String>();//то что отображаем
    ArrayList<String> idz=new ArrayList<String>();// номера заказов
    private ArrayAdapter<String> adapter;

    //
    HttpClient1 mt1;

    public ProgressDialog dialog1;

    //

    //private String[] w2 = new String[] { };// с сервера

    @Override


    public void onCreate(Bundle icicle)
    {
        super.onCreate(icicle);
        setContentView(R.layout.hystory);

          back=(Button) findViewById(R.id.back);
          back.setOnClickListener(this);

        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, v);
        setListAdapter(adapter);

        AddZ();

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

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
                    //int l1=w[i].indexOf(",");
                    //  int l2=w2[i].length();
                    // String nz =  w[i].substring(0,l1);

                    //   idz.add( nz  );
                    // v.add(  w2[i].substring(l1+1,l2)  );
                    v.add(w[i]);

                }



            }
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        adapter.notifyDataSetChanged();

    }

   public void finish(){
        Log.v("press back===", "001");
    }

    private void AddZ() {

        Singleton q = Singleton.getInstance();
        //q.setUrl("http://taxi.ru/i/io.php?l="+q.getPhone()+"&p="+q.getPas()+"&o=4"+"&m="+q.getMaxIdZakaz());

     //   String ss=  "http://taxi.ru/i/io.php?l="+q.getPhone()+"&p="+q.getPas()+"&o=9"+"&type="+q.get_HysType();
        q.setUrl("http://taxi.ru/i/io.php?l="+q.getPhone()+"&p="+q.getPas()+"&o=9"+"&type="+q.get_HysType());

       // Toast.makeText(this, ss, Toast.LENGTH_SHORT).show();

        /////////////////////// http поток
        dialog1 = new ProgressDialog(this);
        dialog1.setMessage("Загружаем историю..."); // Получение статуса заказа...
        dialog1.setIndeterminate(true);
        dialog1.setCancelable(true);
        dialog1.show();



        mt1 = new HttpClient1();
        mt1.execute((Void)null);

        /////////////////////// http поток





    }





    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String item = (String) getListAdapter().getItem(position);
        Singleton q = Singleton.getInstance();

        if (q.get_HysType()==1)
        {
            int l1 = item.indexOf(",");
            int l2 = item.length();
            q.set_otkuda_gorod(  item.substring(0,l1)   );

            String t = item.substring(l1+2,l2) ;
            l1 = t.indexOf(",");
            l2 = item.length();
             q.set_otkuda_ulica(  t.substring(0,l1)   );

            l1 = t.indexOf(",");
            l2 = t.length();
            q.set_otkuda_dom(  t.substring(l1+2,l2)   );
        }
        else
        {
            int l1 = item.indexOf(",");
            int l2 = item.length();
            q.set_kuda_gorod(  item.substring(0,l1)   );

            String t = item.substring(l1+2,l2) ;
            l1 = t.indexOf(",");
            l2 = item.length();
            q.set_kuda_ulica(  t.substring(0,l1)   );

            l1 = t.indexOf(",");
            l2 = t.length();
            q.set_kuda_dom(  t.substring(l1+2,l2)   );
        }

        q.set_focus(10);
        back();

    }

    public void onClick(View v) {
        switch (v.getId())
        {
            case (R.id.back):
                back();
                break;

        }
    }

    private void back() {

        Singleton q = Singleton.getInstance();

        Log.v("hystory back() ========== q.get_hys_back_screen ",String.valueOf(q.get_hys_back_screen()));

        if (q.get_hys_back_screen()==1)
        {
            Intent intent = new Intent();
            intent.setClass(this, fast.class);
            startActivity(intent);
            finish();
        }
        if (q.get_hys_back_screen()==2)
        {
            Intent intent = new Intent();
            intent.setClass(this, otkuda.class);
            startActivity(intent);
            finish();
        }
        if (q.get_hys_back_screen()==3)
        {
            Intent intent = new Intent();
            intent.setClass(this, kuda.class);
            startActivity(intent);
            finish();
        }


    }


}


