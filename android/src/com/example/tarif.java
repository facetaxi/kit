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

public class tarif extends ListActivity implements View.OnClickListener {

    Button back;

    private ListView list1;
    ArrayList<String> v=new ArrayList<String>();//то что отображаем
    ArrayList<String> idz=new ArrayList<String>();// номера заказов
    private ArrayAdapter<String> adapter;


    @Override


    public void onCreate(Bundle icicle)
    {
        super.onCreate(icicle);
        setContentView(R.layout.tarif);

        back=(Button) findViewById(R.id.back);
        back.setOnClickListener(this);

        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, v);
        setListAdapter(adapter);

        v.add("км эконом 100/16");
        v.add("км норма 130/18");
        v.add("км комфорт 150/20");
        v.add("км vip 200/25");

        v.add("часы эконом 360");
        v.add("часы норма 420");
        v.add("часы комфорт 480");
        v.add("часы vip 700");

        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String item = (String) getListAdapter().getItem(position);
        Singleton q = Singleton.getInstance();


        Log.v("set_tarifcar=",item);
        q.set_tarifcar(item);

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
            intent.setClass(this, MyActivity.class);
            startActivity(intent);
            finish();
    }


}


