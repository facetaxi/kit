package com.example;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;


public class karta extends Activity   implements View.OnClickListener
{
    Button back;
    WebView browser;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.karta);

        back=(Button) findViewById(R.id.back);
        back.setOnClickListener(this);

        update();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case (R.id.back):
                back();
                break;
        }
    }

    private void back() {
        //finish();
        Intent intent = new Intent();
        intent.setClass(this, v_detail.class);
        startActivity(intent);
        finish();
    }


    private void update() {
      //  browser=(WebView)findViewById(R.id.webkit);
      //  browser.loadUrl("taxi.ru/yandexmap.php?f1=Москва&f2=Тверская&f3=200&f4=Москва&f5=Тверская&f6=250");
      //  browser.getSettings().setJavaScriptEnabled(true);
    }


   // public void finish(){
       // Log.v("press back===", "001");

   // }
}
