package com.example;

import android.app.ListActivity;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.ToneGenerator;
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

public class dop_uslugi extends ListActivity implements View.OnClickListener {

    Button back;

    private ListView list1;
    ArrayList<String> v=new ArrayList<String>();//то что отображаем
    ArrayList<String> idz=new ArrayList<String>();// номера заказов
    private ArrayAdapter<String> adapter;


    @Override


    public void onCreate(Bundle icicle)
    {
        super.onCreate(icicle);
        setContentView(R.layout.dop_uslugi);

        Log.v("dop_uslugi===","0");

        back=(Button) findViewById(R.id.back);
        back.setOnClickListener(this);

        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, v);
        setListAdapter(adapter);

        Log.v("dop_uslugi===","1");

        v.add("без дополнительных услуг");
        v.add("доставка продуктов, обедов");
        v.add("доставка пиццы");
        v.add("доставка «Макдональдс»");
        v.add("доставка цветов");
        v.add("габаритный багаж");
        v.add("доставка на сумму свыше 1500 руб.");
        v.add("перевозка животных");

        Log.v("dop_uslugi===","2");

        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String item = (String) getListAdapter().getItem(position);
        Singleton q = Singleton.getInstance();

        q.set_dop_uslugi(item);

        back();

    }


    public void finish(){
        Log.v("press back===", "001"    );
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
        Intent intent = new Intent();
        intent.setClass(this, dop.class);
        startActivity(intent);
        finish();
    }


}


