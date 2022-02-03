package com.moon.android_thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean running;
    int value = 0;
    @Override
    protected void onPause() {
        super.onPause();
        running = false;
        value = 0;
    }

    @Override
    protected void onResume() {
        super.onResume();
        running = true;
        Thread t = new BackgrundThread();
        t.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView text = findViewById(R.id.text);
        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text.setText("스레드로 증가시킨 값 : "+value);
            }
        });
    }
    class BackgrundThread extends Thread{
        @Override
        public void run() {
            while (running){
                try {
                    Thread.sleep(1000);
                    value ++;

                }catch (Exception e){
                    System.out.println(e);
                }
            }
        }
    }
}