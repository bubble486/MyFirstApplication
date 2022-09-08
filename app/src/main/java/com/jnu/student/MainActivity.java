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
        setContentView(R.layout.activity_main);
//        通过id找到TextView
        textViewHelloWorld= this.findViewById(R.id.text_view_hello_world);

        String strHelloWorld = textViewHelloWorld.getText().toString();
        Log.i("MainActivity",strHelloWorld);

        textViewHelloWorld.setText(R.string.hello_android);

        String stringHelloWorld = this.getResources().getText(R.string.string_hello_world).toString();
        textViewHelloWorld.setText(stringHelloWorld);
    }
}