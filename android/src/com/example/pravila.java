package com.example;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;


public class pravila extends Activity   implements View.OnClickListener
{
    Button back;
    WebView browser;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pravila);

        back=(Button) findViewById(R.id.back);
        back.setOnClickListener(this);

        update();
    }


 //   public void finish(){
   //     Log.v("press back===", "001");
    //}

    private void update() {
        browser=(WebView)findViewById(R.id.webkit);
        browser.loadUrl("http://taxi.ru/mobile/pravila.html");
        browser.getSettings().setJavaScriptEnabled(true);
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

        finish();
    }

}
