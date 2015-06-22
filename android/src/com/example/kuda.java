package com.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class kuda extends Activity  implements View.OnClickListener {

    Button back,hys;

    EditText ui_gorod, ui_ulica, ui_dom;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kuda);

        Singleton q = Singleton.getInstance();

        back=(Button) findViewById(R.id.back);
        back.setOnClickListener(this);

        q.set_call_status(0);
        q.set_button_press(0);

        hys=(Button) findViewById(R.id.hys);
        hys.setOnClickListener(this);



        ui_gorod= (EditText)findViewById(R.id.gorod);
        ui_ulica= (EditText)findViewById(R.id.ulica);
        ui_dom= (EditText)findViewById(R.id.dom);


        if (q.get_kuda_gorod()!=null) {         ui_gorod.setText(q.get_kuda_gorod());  }
        if (q.get_kuda_ulica()!=null) {  ui_ulica.setText(q.get_kuda_ulica()); }
        if (q.get_kuda_dom()!=null) {  ui_dom.setText(q.get_kuda_dom()); }




        if (q.get_kuda_gorod()==null)
        {
            ui_gorod.setText("москва");
        }

        if (q.get_kuda_ulica()==null  || q.get_kuda_ulica().trim().equals(""))
        {
            ui_ulica.setText("улица");

        }

        if (q.get_kuda_dom()==null || q.get_kuda_dom().trim().equals(""))
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

        ui_ulica.requestFocus();

    }

    public void finish(){
        Log.v("press back===", "001");
    }


    public void onClick(View v) {
        switch (v.getId())
        {
            case (R.id.back):
                back();
                break;

            case (R.id.hys):
                hys();
                break;


        }
    }

    private void back() {
        save_kuda();

        Intent intent = new Intent();
        intent.setClass(this, MyActivity.class);
        startActivity(intent);
        finish();
    }



    private void verify() {
        Singleton q = Singleton.getInstance();

        save_kuda();

        String gorod=q.get_kuda_gorod();
        String ulica=q.get_kuda_ulica();
        String dom=q.get_kuda_dom();

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
            Toast.makeText(this, plus, Toast.LENGTH_SHORT).show();
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


    private void save_kuda() {

        Singleton q = Singleton.getInstance();
        q.set_kuda_gorod(ui_gorod.getText().toString() );
        q.set_kuda_ulica(ui_ulica.getText().toString() );
        q.set_kuda_dom(ui_dom.getText().toString() );
    }

    public void hys()    {

        save_kuda();

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
            q.set_HysType(2);
            q.set_hys_back_screen(3);

            Intent intent = new Intent();
            intent.setClass(this, hystory.class);  //история     hystory   zakazi
            startActivity(intent);
        }

    }






}
