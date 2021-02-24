package com.example.loginandproduclist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private Button buttonclick;
    EditText closedEdittext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonclick = findViewById(R.id.buttonclick);
        closedEdittext = findViewById(R.id.closedEdittext);
        closedEdittext.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        buttonclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent urunsayfasinagit = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(urunsayfasinagit);
            }
        });
    }
}