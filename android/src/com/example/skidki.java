package com.example;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class skidki extends Activity   implements View.OnClickListener
{
    Button back;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.skidki);

        back=(Button) findViewById(R.id.back);
        back.setOnClickListener(this);


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

   // public void finish(){
     //   Log.v("press back===", "001");
    //}


}
