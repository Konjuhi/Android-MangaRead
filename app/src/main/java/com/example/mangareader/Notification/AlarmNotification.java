package com.example.mangareader.Notification;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.mangareader.Notes.Notes_activity;
import com.example.mangareader.R;

import java.util.Calendar;
import java.util.Date;

public class AlarmNotification extends AppCompatActivity {
    Button btnSave,btnNotes;
    ToggleButton switchAlarm;
    TimePicker timePicker;
    View back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_notification);
        back = (View)findViewById(R.id.back);

        btnSave = (Button)findViewById(R.id.btnSave);
        btnNotes = (Button)findViewById(R.id.btnNote);

        switchAlarm = (ToggleButton)findViewById(R.id.switchAlarm);
        timePicker = (TimePicker)findViewById(R.id.timePicker);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlarmNotification.this, Notes_activity.class);
                startActivity(intent);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAlarm(switchAlarm.isChecked());
                Toast.makeText(AlarmNotification.this,"SAVED!!!",Toast.LENGTH_SHORT).show();
                finish();
            }
        });


    }

    private void saveAlarm(boolean checked) {

            if(checked){
                AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
                Intent intent;
                PendingIntent pendingIntent;

                intent = new Intent(AlarmNotification.this,AlarmNotificationReceiver.class);
                pendingIntent = PendingIntent.getBroadcast(this,0,intent,0);

                Calendar calendar = Calendar.getInstance();
                Date toDay = Calendar.getInstance().getTime();
                calendar.set(toDay.getYear(),toDay.getMonth(),toDay.getDay(),timePicker.getHour(),timePicker.getMinute());
                manager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);

                Log.d("DEBUG","Alarm will wake at :" + timePicker.getHour() + ":"+timePicker.getMinute());

            }else{

                Intent intent = new Intent(AlarmNotification.this,AlarmNotificationReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,intent,0);
                AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
                manager.cancel(pendingIntent);
            }

        }
}
