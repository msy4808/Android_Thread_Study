package com.moon.android_thread;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.os.Handler;
import android.util.Half;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView clock;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clock = findViewById(R.id.clock);
        class Diclock implements Runnable {
            Calendar cal = Calendar.getInstance(); //현재시간을 가져올 객체 생성
            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss"); //데이터 포맷터 객체 생성 및 초기화
            @Override
            public void run() {
                while (true){
                    String str = format.format(cal.getTime()); //포맷터의 형식으로 getTime()으로 시간을 가져옴
                    clock.setText(str); //가져온 시간을 저장한 문자열을 setText()로 설정 하지만 이 상태로는 앱이 멈춤. 화면 그리는기능은 메인 스레드에서만 실행되어야함!!!!!!!
                    try {
                        Thread.sleep(1000); //1초 대기
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
        Diclock diclock = new Diclock();
        Thread thread = new Thread(diclock);
        thread.start();
    }
}