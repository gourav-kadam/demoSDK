package com.example.mylibrary;

import android.content.Context;
import android.widget.Toast;

public class Toasty {
    public static void simpler(Context context, String value){
        Toast.makeText(context, value, Toast.LENGTH_SHORT).show();
    }
}
