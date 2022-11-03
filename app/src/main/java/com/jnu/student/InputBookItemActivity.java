package com.jnu.student;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class InputBookItemActivity extends AppCompatActivity {


    public static final int RESULT_CODE_SUCCESS = 666;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        EditText editTextTitle = findViewById(R.id.edit_text_book_item_title);
        TextView textView = findViewById(R.id.text_view_title);

        Button buttonOk = findViewById(R.id.button_ok);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("Title",editTextTitle.getText().toString());
                intent.putExtras(bundle);
                setResult(RESULT_CODE_SUCCESS,intent);
                InputBookItemActivity.this.finish();
            }
        });
    }
}