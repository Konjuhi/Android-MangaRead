package com.example.mangareader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mangareader.Adapter.MyMangaAdapter;
import com.example.mangareader.Model.Manga;
import com.example.mangareader.Notes.Notes_activity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FilterSearchActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    RecyclerView recycler_filter_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_search);

        View view_back = (View)findViewById(R.id.view_back);
        view_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recycler_filter_search = (RecyclerView)findViewById(R.id.recycler_filter_search);
        recycler_filter_search.setHasFixedSize(true);
        recycler_filter_search.setLayoutManager(new GridLayoutManager(this,2));

        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_nav);
        bottomNavigationView.inflateMenu(R.menu.main_menu);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.action_search:showSearchDialog();
                        break;
                    case R.id.action_notes:
                        Intent intent = new Intent(FilterSearchActivity.this, Notes_activity.class);
                        startActivity(intent);
                        break;
                    default:break;
                }
                return true;
            }
        });
    }

    private void showSearchDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(FilterSearchActivity.this);
        alertDialog.setTitle("Search ");

        final LayoutInflater inflater = this.getLayoutInflater();
        View search_layout = inflater.inflate(R.layout.dialog_search,null);
        final EditText edt_search = (EditText)search_layout.findViewById(R.id.edt_search);
        alertDialog.setView(search_layout);
        alertDialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.setPositiveButton("SEARCH", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                fetchSearchManga(edt_search.getText().toString());
            }
        });
        alertDialog.show();

    }

    private void fetchSearchManga(String query) {
        List<Manga> manga_search = new ArrayList<>();
        for(Manga manga:Static.mangaList){
            if(manga.getName().contains(query));
            manga_search.add(manga);
        }
        if(manga_search.size()!=0)
            recycler_filter_search.setAdapter(new MyMangaAdapter(getBaseContext(),manga_search));
        else
            StyleableToast.makeText(getApplicationContext(), "No Results", R.style.exampleToast).show();
    }

}
