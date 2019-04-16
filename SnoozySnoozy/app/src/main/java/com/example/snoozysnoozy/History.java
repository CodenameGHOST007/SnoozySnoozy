package com.example.snoozysnoozy;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class History extends AppCompatActivity {
    Button backToMainActivity;               //sends back to main activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);




        backToMainActivity=findViewById(R.id.button7);                            //ID creation
        TableLayout tableLayout =findViewById(R.id.tab);
        tableLayout.removeAllViews();



        SQLiteDatabase data=openOrCreateDatabase("snoozysnoozy",MODE_PRIVATE,null);              //attaches the database
        data.execSQL("create table if not exists history(subject varchar,time varchar,result varchar)");
        String s4="select * from history";              //selects all enteries in the database
        Cursor cursor=data.rawQuery(s4,null);
        if (cursor.getCount()==0)                         //checks if there is no entry in database
        {
            Toast.makeText(History.this, "No History Available", Toast.LENGTH_SHORT).show();
            Intent i=new Intent(History.this,MainActivity.class);    //sends back if there is no entry in database
            startActivity(i);
            finish();
        }
        else                      //if there is an entry in database
        {

            {TableRow tablerow= new TableRow(getApplicationContext());                        //to show the headings of the database in the first row of the table
            tablerow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
            {
                String col="Subject"+"   ";
                TextView TextView90 = new TextView(getApplicationContext());
                TextView90.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                TextView90.setTextColor(0xFFFFFFFF);
                TextView90.setText(col);
                tablerow.addView(TextView90);}
            {
                String col="Date and Time";
                TextView TextView90 = new TextView(getApplicationContext());
                TextView90.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                TextView90.setTextColor(0xFFFFFFFF);
                TextView90.setText(col);
                tablerow.addView(TextView90);}
            {
                String col="Result"+"   ";
                TextView TextView90 = new TextView(getApplicationContext());
                TextView90.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                TextView90.setTextColor(0xFFFFFFFF);
                TextView90.setText(col);
                tablerow.addView(TextView90);}
            tableLayout.addView(tablerow);}





            cursor.moveToFirst();        //sends the cursor to the start of the history table in database
            for(int i=0;i<cursor.getCount();i++){                        //to show the entries of the database in the table

                TableRow tablerow= new TableRow(getApplicationContext());       //for rows
                tablerow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
                for(int j=0;j<cursor.getColumnCount();j++){                     //for columns in each row
                    String col=cursor.getString(j)+"   ";
                    TextView TextView90 = new TextView(getApplicationContext());
                    TextView90.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                    TextView90.setTextColor(0xFFFFFFFF);

                    TextView90.setText(col);
                    tablerow.addView(TextView90);         //adds text to the table row
                }
                cursor.moveToNext();                      //moves the cursor to start of the history
                tableLayout.addView(tablerow);              //adds a table row

            }
        }


        backToMainActivity.setOnClickListener(new View.OnClickListener() {                 //to send you back to the main activity page
            @Override
            public void onClick(View v) {
                Intent back = new Intent(History.this,MainActivity.class);
                startActivity(back);
                finish();
            }
        });
    }
}
