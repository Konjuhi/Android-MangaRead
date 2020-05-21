package com.example.mangareader.Notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.mangareader.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Notes_activity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    static ArrayList<String> notes = new ArrayList<>();
    static ArrayAdapter<String> listAdapter;

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_activity);

        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_nav2);
        bottomNavigationView.inflateMenu(R.menu.menu_item);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.addNote:
                        Intent intent = new Intent(Notes_activity.this,NoteEditor.class);
                        startActivity(intent);break;
                    default:break;
                }
                return true;
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar3);
        toolbar.setNavigationIcon(R.drawable.ic_keyboard1_arrow_left_black_40dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        listView = findViewById(R.id.notesList);

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(getPackageName(), MODE_PRIVATE);
        Set<String> set = sharedPreferences.getStringSet("notes", null);

        if (set == null) {
            notes.add("Example first note");

        } else {
            notes = new ArrayList<>(set);
        }

        listAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, notes);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Notes_activity.this,NoteEditor.class);
                intent.putExtra("noteID",position);
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(Notes_activity.this)
                        .setIcon(R.drawable.ic_add_alert_black_24dp)
                        .setTitle("Confirm Delete")
                        .setMessage("Are you sure you want to delete the item")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                notes.remove(position);
                                listAdapter.notifyDataSetChanged();

                                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(getPackageName(),MODE_PRIVATE);
                                HashSet<String> set = new HashSet<>(Notes_activity.notes);
                                sharedPreferences.edit().putStringSet("notes",set).apply();
                            }
                        })
                        .setNegativeButton("No",null)
                        .show();
                return true;

            }
        });
    }
}