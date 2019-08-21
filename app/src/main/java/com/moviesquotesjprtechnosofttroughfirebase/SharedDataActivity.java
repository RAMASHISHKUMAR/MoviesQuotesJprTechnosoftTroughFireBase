package com.moviesquotesjprtechnosofttroughfirebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import Adapter.MoviesQuotesListAdapter;
import model.MoviesQuotes;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class SharedDataActivity extends AppCompatActivity {
    private TextView quotes,writername;
    List<MoviesQuotes> moviesQuotesList = new ArrayList<>();
    RecyclerView recyclerView;
    private MoviesQuotesListAdapter moviesQuotesListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_data);

        quotes = findViewById(R.id.shared_qutoes);
        writername = findViewById(R.id.writer);
        recyclerView = findViewById(R.id.recyclershared);

        Bundle bundle = getIntent().getExtras();
        final String dialog = bundle.getString("Text");
        String w_nmae = bundle.getString("Writtername");

        quotes.setText(dialog);
        writername.setText(w_nmae);

        recyclerViewOperation();



    }

    public void recyclerViewOperation() {

        //  moviesQuotesListAdapter = new MoviesQuotesListAdapter(moviesQuotesList);

        MoviesQuotesListAdapter moviesQuotesListAdapter= new MoviesQuotesListAdapter(this, moviesQuotesList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());




        // recyclerView.setAdapter(moviesQuotesListAdapter);
        recyclerView.setAdapter(moviesQuotesListAdapter);


        for (int i =0 ; i<100; i++){
            MoviesQuotes moviesQuotes = new  MoviesQuotes();
            moviesQuotes.setQuoteTxt("This is dummy Quetes This is dummy Quetes This is dummy Quetes This is dummy QuetesThis is dummy Quetes"+i);
            moviesQuotes.setWriterName(" -- Raj kumar"+i);
            moviesQuotesList.add(moviesQuotes);
        }





    }
}
