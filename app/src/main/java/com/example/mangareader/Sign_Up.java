package com.example.mangareader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.muddzdev.styleabletoastlibrary.StyleableToast;

import java.util.regex.Pattern;

public class Sign_Up extends AppCompatActivity {

    private static final Pattern PASSWORD_PATERN = Pattern.compile("^(?=.*[0-9])"  + "(?=.*[a-z])" +
            "(?=.*[A-Z])" + "(?=\\S+$)" + ".{4,}$");

    private ImageView imageView;
    private TextView textView3;
    private TextView subtitle_header,textView,textView2;
    private EditText name,email,password;
    private Button signUp;

    Animation smalltobig,txt,btn;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__up);


        textView3 = (TextView)findViewById(R.id.textView3);
        textView = (TextView)findViewById(R.id.textView);
        textView2 = (TextView)findViewById(R.id.textView2);
        subtitle_header = (TextView)findViewById(R.id.subtitle_header);
        name = (EditText) findViewById(R.id.name);
        email = (EditText)findViewById(R.id.email) ;
        password = (EditText)findViewById(R.id.password);
        signUp = (Button) findViewById(R.id.signUp);
        imageView = (ImageView)findViewById(R.id.imageView);

        smalltobig = AnimationUtils.loadAnimation(this,R.anim.smalltobig);
        txt= AnimationUtils.loadAnimation(this,R.anim.txt);
        btn= AnimationUtils.loadAnimation(this,R.anim.btn);

        imageView.startAnimation(smalltobig);

        textView.startAnimation(txt);
        textView2.startAnimation(txt);
        textView3.startAnimation(txt);
        subtitle_header.startAnimation(txt);

        name.startAnimation(btn);
        password.startAnimation(btn);
        email.startAnimation(btn);
        signUp.startAnimation(btn);

        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Sign_Up.this,Sign_In.class);
                startActivity(intent);
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkEmail() == true && checkPassword()== true) {

                    SQLiteDatabase objDb = new Databaza(Sign_Up.this).getWritableDatabase();
                    ContentValues cv = new ContentValues();

                    cv.put("Name", name.getText().toString().trim());
                    cv.put("Email", email.getText().toString().trim().toUpperCase());
                    cv.put("Password", password.getText().toString().trim().toUpperCase());

                    long res = objDb.insert("SIGNUP", null, cv);
                    if (res == -1) {
                        StyleableToast.makeText(getApplicationContext(), "Not Registered", R.style.exampleToastError).show();
                    } else {
                        StyleableToast.makeText(getApplicationContext(), "Successfully Registerd", R.style.exampleToast).show();
                        Intent intent = new Intent(Sign_Up.this,Sign_In.class);
                        startActivity(intent);
                    }
                }
            }
        });

    }

    private boolean checkEmail() {
        String _email = email.getText().toString().trim();
        if (_email.isEmpty()) {
            email.setError("Fields cannot be empty!");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(_email).matches()) {
            email.setError("Please enter a valid Email");
            return false;
        }else
            email.setError(null);
        return true;
    }

    private boolean checkPassword() {
        String _password = password.getText().toString().trim();
        if (_password.isEmpty()) {
            password.setError("Field Cannot be empty!");
            return false;
        } else if (!PASSWORD_PATERN.matcher(_password).matches()) {
            password.setError("Password To Weak!");
            return false;
        } else
            password.setError(null);
        return true;

    }
}

