package com.example;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;


public class dop extends Activity   implements View.OnClickListener
{
    Button back,dop_uslugi;
    CheckBox kondic, kurevo, baby;
    EditText userrem;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dop);

Log.v("dop=","01");

        back=(Button) findViewById(R.id.back);
        back.setOnClickListener(this);

        dop_uslugi=(Button) findViewById(R.id.uslugi);
        dop_uslugi.setOnClickListener(this);

        Singleton q = Singleton.getInstance();

       // Log.v(  "dop_uslugi=====",q.get_dop_uslugi()   );
        dop_uslugi.setText(  q.get_dop_uslugi()  );


         userrem = (EditText)findViewById(R.id.userrem);
         kondic = (CheckBox) findViewById(R.id.kondic);
         kurevo = (CheckBox) findViewById(R.id.kurevo);
         baby = (CheckBox) findViewById(R.id.baby);



  //
        if (q.get_userrem().trim() == "")
            {
                userrem.setText("");
            }
            else
            {
                userrem.setText(q.get_userrem());
            }




        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            if (q.get_kondic().length() == 0)
            {

            }
            else
            {

              kondic.setChecked(true);
            }



             if (q.get_kurevo().length() == 0)
              {

               }
             else
               {
                 kurevo.setChecked(true);
               }



             if (q.get_baby().length() == 0)
             {

             }
            else
           {
               baby.setChecked(true);
           }

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

Log.v("dop==","06");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case (R.id.back):
                back();
                break;
            case (R.id.uslugi):
                dop_uslugi();
                break;
        }
    }

    private void dop_uslugi() {

        ui_save();

        Intent intent = new Intent();
        intent.setClass(this, dop_uslugi.class);
        startActivity(intent);
        finish();
    }

    private void back() {

        ui_save();

        Intent intent = new Intent();
        intent.setClass(this, MyActivity.class);
        startActivity(intent);
        finish();
    }


    public void finish(){
        Log.v("press back===", "001"    );
    }


    private void ui_save() {
        Singleton q = Singleton.getInstance();

        //



        if (userrem.getText().toString().trim()=="")         {      q.set_userrem(null);      }             else
        {
            q.set_userrem(  userrem.getText().toString().trim()  );
        }

        if ( kondic.isChecked() )
        {
            Log.v("dop=","наличие кондиционера");
            q.set_kondic("наличие кондиционера");
        }
        else
        {
            q.set_kondic(null);
        }

        if ( kurevo.isChecked() )
        {
            Log.v("dop=","курящий салон");
            q.set_kurevo("курящий салон");
        }
        else
        {
            q.set_kurevo(null);
        }

        if ( baby.isChecked() )
        {
            Log.v("dop=","детское кресло");
            q.set_baby("детское кресло");
        }
        else
        {
            q.set_baby(null);
        }


        //
    }

}
