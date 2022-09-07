package com.jnu.student;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textViewHelloWorld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Log.v("试一试","不好玩");

        setContentView(R.layout.activity_main);
        textViewHelloWorld= this.findViewById(R.id.text_hello_world);

        String strHelloWorld = textViewHelloWorld.getText().toString();
        Log.i("MainActivity",strHelloWorld);

        textViewHelloWorld.setText("text");
        textViewHelloWorld.setText(R.string.string_hello_world);



        String stringHelloWorld = this.getResources().getText(R.string.app_name1).toString();
        Log.i("MainActivity","nihao");
        textViewHelloWorld.setText(stringHelloWorld);
    }
}