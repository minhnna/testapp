package com.base.testapp.base.app;

public class Constant {

    // Pattern param
    public static final String EMAIL_PATTERN                 = "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    /*----------------------------------------------NOTE PARAM REQUEST------------------------------------------*/
    // Response Message
    public static final String SUCCESS                       = "success";
    public static final String ERROR                         = "error";

    /*--------------------------------------Format date time ----------------------------------------------------*/
    public static final int DEFAULT_YEAR                     = 1995 ;
    public static final int DEFAULT_MONTH                    = 0 ;
    public static final int DEFAULT_DAY                      = 1 ;
    public static final int DEFAULT_HOUR                     = 12 ;
    public static final int DEFAULT_MINUTE                   = 0 ;

    public static final String FORMAT_DISPLAY_TIME_JP        = "MM月dd日";
    public static final String FORMAT_DATE_TIME              = "yyyyMMddHHmmss";
    public static final String FORMAT_DATE_TIME_S            = "yyyyMMddHHmmsss";
    public static final String FORMAT_CURRENT_TIME           = "yyyy/MM/dd HH:mm:ss";
    public static final String FORMAT_DISPLAY_DATE           = "dd/MM/yyyy";
    public static final String FORMAT_DATE                   = "yyyyMMdd";
    public static final String FORMAT_TIME                   = "HHmm";
    public static final String FORMAT_DISPLAY_TIME           = "HH:mm";

    /*--------------------------------------Main Activity constant ----------------------------------------------------*/
    public static final String INIT_OFFSET = "0";
    public static final float SPACE_IN_PERCENT = 0.02f;

    /*--------------------------------------Youtube API key -------------------------------------------------------------*/
    public static final String API_KEY= "AIzaSyBOzPklNKHQFgQZry6vQY8lrbytpXsbGWg";
    public static final CharSequence YOUTUBE_LINK = "www.youtube.com";

    /*---------------------------------------Share content constant -------------------------------------------------*/
    public static final String FACEBOOK_PACKAGE_NAME = "com.facebook.katana";
    public static final String ZALO_PACKAGE_NAME = "com.zing.zalo";
}
