package com.e.fitbit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Emergency extends AppCompatActivity {
    Button emerg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);
        emerg= findViewById(R.id.saveEmerg);
        emerg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent start = new Intent(Emergency.this,Login.class);
                startActivity(start);
            }
        });
    }
}
