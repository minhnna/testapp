package com.base.testapp.base.app.utilities;

import android.util.Log;

import com.base.testapp.base.app.Config;


public class LogUtils {

    public static void d(String message) {
        StackTraceElement stackTraceElement = (new Throwable()).getStackTrace()[1];
        if (Config.DEBUG) {
            Log.d(stackTraceElement.getFileName() + " in " + stackTraceElement.getMethodName() +
                    " at line " + stackTraceElement.getLineNumber(), message);
        }
    }

    public static void e(String message) {
        StackTraceElement stackTraceElement = (new Throwable()).getStackTrace()[1];
        if (Config.DEBUG) {
            Log.e(stackTraceElement.getFileName() + " in " + stackTraceElement.getMethodName() +
                    " at line " + stackTraceElement.getLineNumber(), message);
        }
    }

    public static void i(String message) {
        StackTraceElement stackTraceElement = (new Throwable()).getStackTrace()[1];
        if (Config.DEBUG) {
            Log.i(stackTraceElement.getFileName() + " in " + stackTraceElement.getMethodName() +
                    " at line " + stackTraceElement.getLineNumber(), message);
        }
    }

    public static void w(String message) {
        StackTraceElement stackTraceElement = (new Throwable()).getStackTrace()[1];
        if (Config.DEBUG) {
            Log.w(stackTraceElement.getFileName() + " in " + stackTraceElement.getMethodName() +
                    " at line " + stackTraceElement.getLineNumber(), message);
        }
    }
}
