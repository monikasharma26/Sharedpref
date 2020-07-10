package com.example.sharedpref;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    public static final String SHARED_PREFERENCES_NAME = "username";
    public static final String KEY_NAME = "Key_username";
    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //there is a class named SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME,MODE_PRIVATE);
        //write into SharedPreferences
        sharedPreferences.edit().putString(KEY_NAME,"AAAA").apply();
        //read from SharedPreferences
        String name = sharedPreferences.getString(KEY_NAME,"NA");
        ArrayList<String> names = new ArrayList<>(Arrays.asList("Aaa","aaaa","Monika"));

        sharedPreferences.edit().putStringSet(KEY_NAME,new HashSet<String>(names)).apply();
        Set<String> receiveNames = sharedPreferences.getStringSet(KEY_NAME,new HashSet<String>());
        Log.i(TAG,"Oncreate" + names);
        Log.i(TAG,"Oncreate1" + receiveNames);

        try {
            sharedPreferences.edit().putString("KeySerial",ObjectSerializer.serialize(names)).apply();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> data = new ArrayList<>();
        String s = sharedPreferences.getString("KeySerial",null);
        try {
            data = (List<String>) ObjectSerializer.deserialize(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i(TAG,"onTESt" + data);
    }



}