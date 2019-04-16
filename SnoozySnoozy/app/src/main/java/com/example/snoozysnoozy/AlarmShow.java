package com.example.snoozysnoozy;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AlarmShow extends AppCompatActivity {
    Button stop_button1;             //to stop alarm
    TextView t1;                     //for the question
    RadioButton r1,r2,r3;            //for the options

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_show);

        stop_button1=findViewById(R.id.button3);                              //ID creation
        t1 = findViewById(R.id.textView);
        r1 = findViewById(R.id.radioButton10);
        r2 = findViewById(R.id.radioButton7);
        r3 = findViewById(R.id.radioButton11);



        final SQLiteDatabase data=openOrCreateDatabase("snoozysnoozy",MODE_PRIVATE,null);                 //database attached
        data.execSQL("create table if not exists history(subject varchar,time varchar,result varchar)");

        final String time =new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date());         //to set the current alarm time



        final String quesMaths="How much is 2+2*2-4/2*2-1 equal to?";                             //to set questions
        final String quesPhysics="What is the order of Planks constant?";



        final Ringtone r = RingtoneManager.getRingtone(getApplicationContext(),RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)); //to sound the alarm but only when it comes directly through the broadcast page not by back
        if(MainActivity.def==1)
        {
            r.play();
        }





        if(MainActivity.question_type==1){                                                 //Maths question
            t1.setText(quesMaths);
            r1.setText("2");
            r2.setText("1");
            r3.setText("4");
            stop_button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {                                                      //stopping the alarm
                    if(r2.isChecked()){                  //checks if the answer is right or wrong
                    MainActivity.setTime="";
                    MainActivity.def=0;
                    if(r.isPlaying()){                  //stops if right answer
                        r.stop();
                    }
                    Toast.makeText(AlarmShow.this, "Alarm has been stopped", Toast.LENGTH_SHORT).show();

                    data.execSQL("insert into history values('Mathematics','"+time+"','Right Answer')");    //inserts the entry into the database

                    Intent off=new Intent(AlarmShow.this,MainActivity.class);
                    startActivity(off);

                    }
                    else{                           //for wrong answer
                        Toast.makeText(AlarmShow.this, "Wrong ANSWER GRRR!!!!", Toast.LENGTH_SHORT).show();
                        data.execSQL("insert into history values('Mathematics','"+time+"','Wrong Answer')");     //inserts the entry in the database
                    }


                }
            });
        }
        else{                                             //for physics question
            t1.setText(quesPhysics);
            r1.setText("34");
            r2.setText("-34");
            r3.setText("-19");
            stop_button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {                                                      //stopping the alarm

                    if (r2.isChecked()) {                  //checks for right or wrong answer
                        MainActivity.setTime = "";
                        MainActivity.def = 0;
                        if (r.isPlaying()) {                   //stops if right answer
                            r.stop();
                        }
                        Toast.makeText(AlarmShow.this, "Alarm has been stopped", Toast.LENGTH_SHORT).show();
                        data.execSQL("insert into history values('Physics','"+time+"','Right Answer')");                  //makes entry in database
                        Intent off=new Intent(AlarmShow.this,MainActivity.class);                            //sends you back to main activity
                        startActivity(off);
                    }
                    else{                                  //for wrong answer
                        Toast.makeText(AlarmShow.this, "Wrong ANSWER GRRR!!!!", Toast.LENGTH_SHORT).show();
                        data.execSQL("insert into history values('Physics','"+time+"','Wrong Answer')");                //makes entry in database
                    }

                }
            });
        }






    }
}
