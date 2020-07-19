package com.example.mangareader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.muddzdev.styleabletoastlibrary.StyleableToast;

public class Sign_In extends AppCompatActivity {

     private ImageView imageView;
     private Animation smalltobig,txt,btn;
     private TextView textView,subtitle_header,textView2,textView3;
     private EditText memail,mpassword;
     private Button signIn;
     Intent intent;
    private boolean check = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__in);

        smalltobig = AnimationUtils.loadAnimation(this,R.anim.smalltobig);
        txt= AnimationUtils.loadAnimation(this,R.anim.txt);
        btn= AnimationUtils.loadAnimation(this,R.anim.btn);

        imageView = (ImageView)findViewById(R.id.imageView);
        textView = (TextView)findViewById(R.id.textView);
        subtitle_header = (TextView)findViewById(R.id.subtitle_header);
        textView2 = (TextView)findViewById(R.id.textView2);
        textView3 = (TextView)findViewById(R.id.textView3);

        memail = (EditText)findViewById(R.id.email);
        mpassword = (EditText)findViewById(R.id.password);
        signIn = (Button)findViewById(R.id.signIn);

        imageView.startAnimation(smalltobig);
        textView.startAnimation(txt);
        textView2.startAnimation(txt);
        textView3.startAnimation(txt);
        subtitle_header.startAnimation(txt);

        memail.startAnimation(btn);
        mpassword.startAnimation(btn);
        signIn.startAnimation(btn);

        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Sign_In.this,Sign_Up.class);
                startActivity(intent);
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase objDb = new Databaza(Sign_In.this).getReadableDatabase();
                Cursor c = objDb.rawQuery("select * from SIGNUP", null);

                if (c.getCount() > 0) {
                    c.moveToFirst();
                    while (c.isAfterLast() == false) {

                        if (c.getString(2).equals(memail.getText().toString().trim().toUpperCase())
                                && c.getString(3).equals(mpassword.getText().toString().trim().toUpperCase())) {

                            StyleableToast.makeText(getApplicationContext(), getString(R.string.succes), R.style.exampleToast).show();
                            intent = new Intent(Sign_In.this, MainActivity.class);
                            startActivity(intent);
                            check = true;
                        }
                        c.moveToNext();
                    }

                    if (memail.getText().toString().trim().isEmpty()) {
                        memail.setError(getString(R.string.empty));
                    }

                    else if (mpassword.getText().toString().trim().isEmpty()) {
                        mpassword.setError(getString(R.string.empty));
                    }

                    else if (!check) {
                        StyleableToast.makeText(getApplicationContext(), getString(R.string.pewrong), R.style.exampleToastError).show();
                        memail.setError(null);
                        mpassword.setError(null);
                    }
                }}
        });
    }
}
