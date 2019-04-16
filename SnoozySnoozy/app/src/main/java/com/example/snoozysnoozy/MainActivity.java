package com.example.snoozysnoozy;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    TimePicker alarm;  //to pick time
    RadioButton r1,r2;

    Button set_alarm_button;  //set alarm button
    static String setTime="";  //string used for alarm
    static Integer def=0;      //For the intent to it back
    static Integer question_type=1;  //to see maths or physics question
    Button historypage;      //to shift to history page





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        alarm=findViewById(R.id.simpleTimePicker);                                                  //ID creation
        set_alarm_button=findViewById(R.id.button2);
        r1 = findViewById(R.id.radioButton8);
        r2 = findViewById(R.id.radioButton9);
        historypage=findViewById(R.id.button);






        final Intent intentAlarm=new Intent(this,AlarmReciever.class);

        set_alarm_button.setOnClickListener(new View.OnClickListener() {                            //to set the alarm at current time
            @Override
            public void onClick(View v) {                                                           //set alarm by button
                setTime="";
                Integer alarmHours=alarm.getHour();
                Integer alarmMinute=alarm.getMinute();

                if(alarmHours<10)
                {
                    setTime="0";
                }
                setTime=setTime+alarmHours.toString()+":";
                if(alarmMinute<10)
                {
                    setTime=setTime+"0";
                }


                Calendar calendar= Calendar.getInstance();        //to get current the time set in alarm clock in milliseconds to provide input to alarmManager
                calendar.set(
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH),
                        alarm.getHour(),
                        alarm.getMinute(),
                        0
                );
                long time=calendar.getTimeInMillis();    //stored in a long variable




                setTime=setTime+alarmMinute.toString();
                Toast.makeText(MainActivity.this, "Alarm has been set to start at "+setTime, Toast.LENGTH_SHORT).show();   //Displays a message of the Alarm time set by the user



                if(r1.isChecked()){                                                  //checks which subject is selected
                    question_type=1;           //for maths
                }
                else{
                    question_type=2;          //for physics
                }



                if(time-System.currentTimeMillis()<=0) {                             //if the alarm is set for next day
                    time = 24 * 60 * 60 * 1000 + time;
                }

                AlarmManager alarmmanager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);                      //to run the alarm even if we close the app
                alarmmanager.set(AlarmManager.RTC_WAKEUP,time , PendingIntent.getBroadcast(getBaseContext(), 1, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));
            }
        });



        historypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {                                                              //to send to the history page
                Intent toHistory = new Intent(MainActivity.this,History.class);
                startActivity(toHistory);
                finish();
            }
        });









    }


}
