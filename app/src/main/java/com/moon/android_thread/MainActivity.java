package com.moon.android_thread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.AsyncQueryHandler;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView clock;
    private static Handler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHandler = new Handler();
        class Diclock implements Runnable {

            @Override
            public void run() {
                while (true){

                    try {
                        Thread.sleep(1000); //1초 대기
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    mHandler.sendEmptyMessage(0); //메인스레드에 메세지를 전송
                }
            }
        }
        Diclock diclock = new Diclock();
        Thread thread = new Thread(diclock);
        thread.start();
    }
    class Handler extends android.os.Handler {
        @Override
        public void handleMessage(@NonNull Message msg) { //메인스레드에서 수신된 메세지를 처리하는 코드(메인 UI 그리기)
            clock = findViewById(R.id.clock);
            Calendar cal = Calendar.getInstance(); //현재시간을 가져올 객체 생성
            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss"); //데이터 포맷터 객체 생성 및 초기화
            String str = format.format(cal.getTime()); //포맷터의 형식으로 getTime()으로 시간을 가져옴
            clock.setText(str); //가져온 시간을 저장한 문자열을 setText()로 설정 하지만 이 상태로는 앱이 멈춤. 화면 그리는기능은 메인 스레드에서만 실행되어야함!!!!!!!
        }
    }
}