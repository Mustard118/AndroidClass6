package com.bytedance.todolist.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bytedance.todolist.R;

public class TodoInput extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_input);
        final EditText input=findViewById(R.id.et_input);
        Button confirm=findViewById(R.id.bt_confirm);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input_content=input.getText().toString();
                Intent back=new Intent();
                Bundle bundle=new Bundle();
                bundle.putString("input_content",input_content);
                back.putExtras(bundle);
                setResult(2,back);
                finish();
            }
        });
    }
}