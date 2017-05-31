/*
 * Copyright (c) 2017. Gilang Ramadhan (gilangramadhan96.gr@gmail.com)
 */

package id.sfpcc.sessionandroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Gilang Ramadhan on 31/05/2017.
 */

public class helper {

    public static boolean passwordkosong(EditText editText){
        if (editText.getText().toString().trim().length() > 0){
            return false;
        }else {
            return true;
        }
    }
    public static boolean emailkosong(AutoCompleteTextView textView){
        if (textView.getText().toString().trim().length() > 0){
            return false;
        }else {
            return true;
        }
    }

    //cek email valid
    public static boolean isEmailValid(AutoCompleteTextView textView){
        boolean isValid = false;
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = textView.getText().toString();

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    SharedPreferences.Editor editor;
    Context ctx;
    SharedPreferences preferences;

    //string
    public static final String IS_LOGIN= "login";
    public static final String EMAIL = "email";
    public static final String NAMA = "nama";
    public static final String PASSWORD= "password";
    public helper (Context context){
        this.ctx = context;
        preferences = ctx.getSharedPreferences("login", 0);
        editor = preferences.edit();
    }

    public void createLoginSession (String email, String password, String nama){
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(EMAIL, email);
        editor.putString(PASSWORD, password);
        editor.putString(NAMA, nama);
        editor.commit();
    }

    public boolean isLoginIn (){
        return preferences.getBoolean(IS_LOGIN, false);
    }

    public void cekLogin(){
        if (!this.isLoginIn()){
            Intent i = new Intent(ctx, LoginActivity.class);
            //mmenutup semua activity
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ctx.startActivity(i);
        }
    }

    //untuk mendapatkan data
    public HashMap<String ,String > getUserDetail(){
        HashMap<String, String> user = new HashMap<>();
        user.put(EMAIL, preferences.getString(EMAIL, null));
        user.put(PASSWORD, preferences.getString(PASSWORD, null));
        user.put(NAMA, preferences.getString(NAMA, null));
        return user;
    }

    //logout
    public void logoutUser(){
        editor.clear();
        editor.commit();
        Intent i = new Intent(ctx, LoginActivity.class);
        //mmenutup semua activity
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ctx.startActivity(i);
    }
}
