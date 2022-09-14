package com.jnu.student;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textViewHelloWorld;
    private Button buttonChinese;
    private Button buttonEnglish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        通过id找到TextView
        textViewHelloWorld= this.findViewById(R.id.text_view_hello_world);
        buttonChinese=this.findViewById(R.id.button_chinese);
        buttonEnglish=this.findViewById(R.id.button_english);

        String strHelloWorld = textViewHelloWorld.getText().toString();
        Log.i("MainActivity",strHelloWorld);
        textViewHelloWorld.setText(R.string.hello_android);
        String stringHelloWorld = this.getResources().getText(R.string.string_hello_world).toString();
        textViewHelloWorld.setText(stringHelloWorld);

        buttonChinese.setOnClickListener(new View.OnClickListener() {
//            匿名类只用一次不想取名字，父类是View.OnClickListener
            @Override
            public void onClick(View view) {
                textViewHelloWorld.setText(R.string.string_chinese);
            }
        });

        buttonEnglish.setOnClickListener(new EnglishClickListener());
    }

    private class EnglishClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            textViewHelloWorld.setText(R.string.string_english);
        }
    }
}