package com.example.mangareader.Notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.mangareader.R;

import java.util.HashSet;

public class NoteEditor extends AppCompatActivity {

    private EditText noteEditor;
    int noteID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);

        View view_back  = (View)findViewById(R.id.view_back1);

        view_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        noteEditor = findViewById(R.id.editText);

        Intent intent = getIntent();
        noteID = intent.getIntExtra("noteID",-1);

        Log.i("noteID",noteID+"");

        if(noteID != -1){
            String incomingNote = Notes_activity.notes.get(noteID);
            noteEditor.setText(incomingNote);

        }else{
            Notes_activity.notes.add("");
            noteID = Notes_activity.notes.size() -1;
            Notes_activity.listAdapter.notifyDataSetChanged();
        }

        noteEditor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Notes_activity.notes.set(noteID,String.valueOf(s));
                Notes_activity.listAdapter.notifyDataSetChanged();

                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(getPackageName(),MODE_PRIVATE);
                HashSet<String> set = new HashSet<>(Notes_activity.notes);
                sharedPreferences.edit().putStringSet("notes",set).apply();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
