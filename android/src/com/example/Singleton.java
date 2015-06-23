package com.example;

public class Singleton {
    private static Singleton ourInstance = new Singleton();


    private  boolean yanakarte;


    private  boolean gpstimer;
    private  boolean gpstimer_socketend;

    private  boolean gpstimerV;
    private  boolean gpstimer_socketendV;


    private  boolean aaa;
    private boolean auth_status;

    private boolean stop;

    private String status;

    private String dop_uslugi;

    //
    private String auth_popitka;
    private String yandex;
    private String active_zakaz;

    //

    private float Accuracy;

    //
    private String res1;
    private String res2;
    private String res3;
    private String res4;
    private String res5;
    private String res6;

    private String res10;

    //


    private String otkuda_gorod, otkuda_ulica, otkuda_dom;
    private String kuda_gorod, kuda_ulica, kuda_dom;

    private String url,url2,url3,url4,url5,url6,url7,phone, pas,IdZakaz , MaxIdZakaz , MinIdZakaz,Zakaz;
    double geox,geoy;

    private int call_status;
    private int focus;
    private int HysType;

    private int hys_back_screen;

    private int hour,minute,date, year, month, day;
    private String tarifcar;
    private String uslugi,userrem;
    private String kondic,kurevo,baby;

    private int beep_status;

    private int http_s;


    private int button_press;

    public static Singleton getInstance() {
        return ourInstance;
    }

    private Singleton() {
    }



    public String get_dop_uslugi()         {         return dop_uslugi;    }
    public void set_dop_uslugi(String var) {         dop_uslugi = var;     }

    //
    public String get_1()         {         return res1;    }
    public void set_1(String var) {         res1 = var;     }
    //

    //
    public String get_2()         {         return res2;    }
    public void set_2(String var) {         res2 = var;     }
    //

    //
    public String get_3()         {         return res3;    }
    public void set_3(String var) {         res3 = var;     }
    //

    //
    public String get_4()         {         return res4;    }
    public void set_4(String var) {         res4 = var;     }
    //

    //
    public String get_5()         {         return res5;    }
    public void set_5(String var) {         res5 = var;     }
    //

    //
    public String get_6()         {         return res6;    }
    public void set_6(String var) {         res6 = var;     }
    //

    //
    public String get_10()         {         return res10;    }
    public void set_10(String var) {         res10 = var;     }
    //


    //
    public String get_auth_popitka()         {         return auth_popitka;    }
    public void set_auth_popitka(String var) {         auth_popitka = var;     }
    //
    //
    public String get_yandex()         {         return yandex;    }
    public void set_yandex(String var) {         yandex = var;     }
    //
    //
    public String get_active_zakaz()         {         return active_zakaz;    }
    public void set_active_zakaz(String var) {         active_zakaz = var;     }
    //

    public int get_http_s()         {         return http_s;    }
    public void set_http_s(int var) {         http_s = var;     }


    public boolean  get_a()         {         return aaa;    }
    public void set_a(boolean var) {         aaa = var;     }





    public boolean  get_gpstimer()         {         return gpstimer;    }
    public void set_gpstimer(boolean var) {         gpstimer = var;     }

    public boolean  get_gpstimer_socketend()         {         return gpstimer_socketend;    }
    public void set_gpstimer_socketend(boolean var) {         gpstimer_socketend = var;     }





    public boolean  get_gpstimerV()         {         return gpstimerV;    }
    public void set_gpstimerV(boolean var) {         gpstimerV = var;     }

    public boolean  get_gpstimer_socketendV()         {         return gpstimer_socketendV;    }
    public void set_gpstimer_socketendV(boolean var) {         gpstimer_socketendV = var;     }



    public int get_hys_back_screen()         {         return hys_back_screen;    }
    public void set_hys_back_screen(int var) {         hys_back_screen = var;     }




    public int get_beep_status()         {         return beep_status;    }
    public void set_beep_status(int var) {         beep_status = var;     }


    public int get_hour()         {         return hour;    }
    public void set_hour(int var) {         hour = var;     }

    public int get_minute()         {         return minute;    }
    public void set_minute(int var) {         minute = var;     }

    public int get_date()         {         return date;    }
    public void set_date(int var) {         date = var;     }

    public int get_year()         {         return year;    }
    public void set_year(int var) {         year = var;     }

    public int get_month()         {         return month;    }
    public void set_month(int var) {         month = var;     }

    public int get_day()         {         return day;    }
    public void set_day(int var) {         day = var;     }


    //

    public String get_tarifcar()         {         return tarifcar;    }
    public void set_tarifcar(String var) {        tarifcar = var;     }

    public String get_uslugi()         {         return uslugi;    }
    public void set_uslugi(String var) {         uslugi = var;     }

    public String get_userrem()         {         return userrem;    }
    public void set_userrem(String var) {         userrem = var;     }

    //

    public String get_status()         {         return status;    }
    public void set_status(String var) {        status = var;     }

    //

    public String get_kondic()         {         return kondic;    }
    public void set_kondic(String var) {        kondic = var;     }

    public String get_kurevo()         {         return kurevo;    }
    public void set_kurevo(String var) {         kurevo = var;     }

    public String get_baby()         {         return baby;    }
    public void set_baby(String var) {         baby = var;     }

    //



    public boolean get_stop()         {         return stop;    }
    public void set_stop(boolean var) {         stop = var;     }




    public boolean get_yanakarte()         {         return yanakarte;    }
    public void set_yanakarte(boolean var) {         yanakarte = var;     }




    public boolean get_auth_status()         {         return auth_status;    }
    public void set_auth_status(boolean var) {         auth_status = var;     }


    public String get_otkuda_gorod()         { return otkuda_gorod; }
    public void set_otkuda_gorod(String var) { otkuda_gorod = var;  }

    public String get_otkuda_ulica()         { return otkuda_ulica; }
    public void set_otkuda_ulica(String var) { otkuda_ulica = var;  }

    public String get_otkuda_dom()         { return otkuda_dom; }
    public void set_otkuda_dom(String var) { otkuda_dom = var;  }



    public String get_kuda_gorod()         { return kuda_gorod; }
    public void set_kuda_gorod(String var) { kuda_gorod = var;  }
    public String get_kuda_ulica()         { return kuda_ulica; }
    public void set_kuda_ulica(String var) { kuda_ulica = var;  }
    public String get_kuda_dom()         { return kuda_dom; }
    public void set_kuda_dom(String var) { kuda_dom = var;  }


    public int get_focus()         {         return focus;    }
    public void set_focus(int var) {         focus = var;     }

    public int get_HysType()         {         return HysType;    }
    public void set_HysType(int var) {         HysType = var;     }

    public int get_button_press()         {         return button_press;    }
    public void set_button_press(int var) {         button_press = var;     }


    public int get_call_status()         {         return call_status;    }
    public void set_call_status(int var) {         call_status = var;     }


    public String getUrl()         {         return url;    }
    public void setUrl(String var) {         url = var;     }

    public String getUrl2()         {         return url2;    }
    public void setUrl2(String var) {         url2 = var;     }

    public String getUrl3()         {         return url3;    }
    public void setUrl3(String var) {         url3 = var;     }

    public String getUrl4()         {         return url4;    }
    public void setUrl4(String var) {         url4 = var;     }

    public String getUrl5()         {         return url5;    }
    public void setUrl5(String var) {         url5 = var;     }

    public String getUrl6()         {         return url6;    }
    public void setUrl6(String var) {         url6 = var;     }

    public String getUrl7()         {         return url7;    }
    public void setUrl7(String var) {         url7 = var;     }

    public String getPhone()         {         return phone;    }
    public void setPhone(String var) {                phone = var;     }

    public String getPas()         {         return pas;    }
    public void setPas(String var) {                pas = var;     }

    public String getIdZakaz()         {         return IdZakaz;    }
    public void setIdZakaz(String var) {                IdZakaz = var;     }

    public void setX(double var) { geox = var; }
    public void setY(double var) { geoy = var; }


    public void setAccuracy(float var) { Accuracy = var; }
    public float  getAccuracy( ) { return Accuracy; }


    public double getY() { return geox; }
    public double getX() { return geoy; }

    public String getMaxIdZakaz()         {         return MaxIdZakaz;    }
    public void setMaxIdZakaz(String var) {                MaxIdZakaz = var;     }

    public String getMinIdZakaz()         {         return MinIdZakaz;    }
    public void setMinIdZakaz(String var) {                MinIdZakaz = var;     }

    public String GetZakaz()         {         return Zakaz;    }
    public void SetZakaz(String var) {                Zakaz = var;     }


}
