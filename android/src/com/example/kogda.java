package com.example;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;


public class kogda extends Activity   implements View.OnClickListener
{
    Button back;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kogda);

        back=(Button) findViewById(R.id.back);
        back.setOnClickListener(this);

        Singleton q = Singleton.getInstance();

        DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
        TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker);

        if (       q.get_hour()  == 0 &&  q.get_minute()  == 0 &&  q.get_year()  == 0  &&  q.get_month()  == 0 &&  q.get_day()  == 0  )
        {
            Date live = java.util.Calendar.getInstance ().getTime();
            Date date = new Date(live.getTime() +  300000    );

            String yyyy = new java.text.SimpleDateFormat("yyyy").format(date);
            int yyyy2 = Integer.parseInt(yyyy);

            String mm = new java.text.SimpleDateFormat("MM").format(date);
            int mm2 = Integer.parseInt(mm)-1;

            String dd = new java.text.SimpleDateFormat("dd").format(date);
            int dd2 = Integer.parseInt(mm);

            datePicker.updateDate(      yyyy2,    mm2,   dd2  );



            timePicker.setCurrentHour(   date.getHours()  );
            timePicker.setCurrentMinute( date.getMinutes()  );

        }


            else
        {
            //установка времени из sigleton = если оно там есть
            datePicker.updateDate(q.get_year(), q.get_month()-1, q.get_day());

            timePicker.setCurrentHour(   q.get_hour()  );
            timePicker.setCurrentMinute( q.get_minute()  );
            //установка времени из sigleton = если оно там есть
        }


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

    public void finish(){
        Log.v("press back===", "001"    );
    }

    private void back() {
        Singleton q = Singleton.getInstance();

        DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
        TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker);

        //проверка время больше или равно текущему

        Date live = java.util.Calendar.getInstance ().getTime();


        Date ui = new Date (datePicker.getYear() - 1900, datePicker.getMonth(), datePicker.getDayOfMonth()  ,  timePicker.getCurrentHour(), timePicker.getCurrentMinute()     );

        Log.v("kogda_back===_____","_________________________________________________________________________________");
        Log.v("kogda_back===live=",new java.text.SimpleDateFormat("dd.MM.yyyy hh:mm aaa").format(live));
        Log.v("kogda_back===  ui=",new java.text.SimpleDateFormat("dd.MM.yyyy hh:mm aaa").format(ui));

        Log.v("kogda_back===    =",  Integer.toString(   ui.compareTo(live) )    );

        Log.v("kogda_back===_____","_________________________________________________________________________________");


        if (     ui.compareTo(live)     >=0)
        {
            q.set_day(datePicker.getDayOfMonth());
            q.set_month(  datePicker.getMonth()+1  );
            q.set_year( datePicker.getYear() );

            q.set_hour(  timePicker.getCurrentHour()  );
            q.set_minute( timePicker.getCurrentMinute() );

            Intent intent = new Intent();
            intent.setClass(this, MyActivity.class);
            startActivity(intent);
            finish();
        }
        else
        {
            Toast.makeText(this, "выбранное время меньше текущего", Toast.LENGTH_SHORT).show();
        }











    }






}
