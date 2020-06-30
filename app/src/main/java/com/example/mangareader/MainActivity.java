package com.example.mangareader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mangareader.Adapter.MyMangaAdapter;
import com.example.mangareader.AsyncTask.AsynTask;

import com.example.mangareader.Interface.IMangaLoadDone;
import com.example.mangareader.Model.Manga;
import com.example.mangareader.Notification.AlarmNotification;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;

public class MainActivity extends AppCompatActivity implements  IMangaLoadDone {

    RecyclerView recyclerView_manga;
    TextView txt_manga;
    TextView topManga;
    ImageView search_btn;
    CoordinatorLayout rootLayout;

    //Database
    DatabaseReference manga;

    //Listener
    IMangaLoadDone mangaListener;

   // AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootLayout = (CoordinatorLayout)findViewById(R.id.rootLayout);
        txt_manga= (TextView)findViewById(R.id.txt_manga);

        manga = FirebaseDatabase.getInstance().getReference("Manga");

        mangaListener = this;

        search_btn = (ImageView)findViewById(R.id.search_btn);
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AlarmNotification.class));
            }
        });

        txt_manga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(rootLayout, "All NEW MANGA'S IN THIS PAGE", Snackbar.LENGTH_INDEFINITE)
                  .setAction("Action",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                StyleableToast.makeText(getApplicationContext(), "Snackbar action clicked", R.style.exampleToast).show();
                            }
                        }).show();
            }
        });

        loadManga();

        recyclerView_manga = (RecyclerView)findViewById(R.id.recycler_manga);
        recyclerView_manga.setLayoutManager(new LinearLayoutManager(this));

        txt_manga = (TextView)findViewById(R.id.txt_manga);
        topManga = (TextView)findViewById(R.id.topManga);

        topManga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AsynTask.class);
                startActivity(intent);
            }
        });
    }

    private void loadManga() {
        //show dialog
       //alertDialog = new SpotsDialog.Builder().setContext(this).setCancelable(false).setMessage("Please wait...").build();
        manga.addListenerForSingleValueEvent(new ValueEventListener() {
            List<Manga> mangaLoad = new ArrayList<>();
            //Manga manga = new Manga();
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot mangaSnapShot:dataSnapshot.getChildren()){
                   // manga = mangaSnapShot.getValue(Manga.class);
                   // mangaLoad.add(manga);
                    Static.mangaSelected = mangaSnapShot.getValue(Manga.class);
                    mangaLoad.add(Static.mangaSelected);
                }
                //call listener
                mangaListener.onMangaLoadDoneListener(mangaLoad);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this,""+ databaseError.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onMangaLoadDoneListener(List<Manga> mangaList) {
        //List<Manga> mangaLoad = new ArrayList<>();
        //mangaLoad=mangaList;
       // Static.mangaList = mangaList;
        recyclerView_manga.setAdapter(new MyMangaAdapter(this,mangaList));

        txt_manga.setText(new StringBuilder("NEW MANGA(")
        .append(mangaList.size())
                .append(")"));

           // alertDialog.dismiss();
    }
}
