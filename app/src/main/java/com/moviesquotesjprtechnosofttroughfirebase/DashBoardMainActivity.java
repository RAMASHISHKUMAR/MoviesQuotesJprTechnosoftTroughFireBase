package com.moviesquotesjprtechnosofttroughfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import java.util.ArrayList;
import java.util.List;

import Adapter.MoviesQuotesListAdapter;
import model.MoviesQuotes;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class DashBoardMainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    List<MoviesQuotes> moviesQuotesList = new ArrayList<>();
    RecyclerView recyclerView;
    private MoviesQuotesListAdapter moviesQuotesListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recylerView);
        View cardView = findViewById(R.id.cardview);

        recyclerViewOperation();


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
      /*  NavigationView navigationView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);*/
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }else if(id == R.id.action_icon)
        {
            Intent intent=new Intent(DashBoardMainActivity.this,ProfileActivity.class);
            startActivity(intent);
            return true;}

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
     @Override
   public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
            moviesQuotes.setQuoteTxt("\"The problem is not the the problem. The problem is your attitude about the problem\"");
            moviesQuotes.setWriterName(" -- Raj kumar"+i);
            moviesQuotesList.add(moviesQuotes);
        }

        //clickListner
        moviesQuotesListAdapter.setmoviesQuotesListInterface(new MoviesQuotesListAdapter.MoviesQuotesListInterface() {
            @Override
            public void moviesQuotesListItem(int position) {
                Intent i = new Intent(DashBoardMainActivity.this,SharedDataActivity.class);
                i.addFlags(FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("Text", moviesQuotesList.get(position).getQuoteTxt());
                i.putExtra("Writtername", moviesQuotesList.get(position).getWriterName());

                startActivity(i);

            }
        });

    }
}
