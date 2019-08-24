package com.moviesquotesjprtechnosofttroughfirebase;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Adapter.MoviesQuotesListAdapter;
import model.MoviesQuotes;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class SharedDataActivity extends AppCompatActivity {
    private TextView quotes,writername;
    List<MoviesQuotes> moviesQuotesList = new ArrayList<>();
    RecyclerView recyclerView;
    private ImageView imageView,shareQuotes;
    private FrameLayout contentContainer;
    private MoviesQuotesListAdapter moviesQuotesListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_data);

        quotes = findViewById(R.id.shared_qutoes);
        imageView = findViewById(R.id.arrowId);
        shareQuotes = findViewById(R.id.shareQuotes);
        contentContainer = findViewById(R.id.contentContainer);

        writername = findViewById(R.id.writer);
        recyclerView = findViewById(R.id.recyclershared);

        Bundle bundle = getIntent().getExtras();
        final String dialog = bundle.getString("Text");
        String w_nmae = bundle.getString("Writtername");

        quotes.setText(dialog);
        writername.setText(w_nmae);

        recyclerViewOperation();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SharedDataActivity.this, DashBoardMainActivity.class);
                startActivity(intent);
            }
        });

        shareQuotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                contentContainer.setDrawingCacheEnabled(true);

                contentContainer.buildDrawingCache();

                Bitmap bitmap = contentContainer.getDrawingCache();
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);

                shareImage(bitmap);
            }
        });
    }
        private void shareImage(Bitmap bitmap){
            // save bitmap to cache directory
            try {
                File cachePath = new File(this.getCacheDir(), "images");
                cachePath.mkdirs(); // don't forget to make the directory
                FileOutputStream stream = new FileOutputStream(cachePath + "/image.png"); // overwrites this image every time
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                stream.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
            File imagePath = new File(this.getCacheDir(), "images");
            File newFile = new File(imagePath, "image.png");
            Uri contentUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".fileprovider", newFile);

            if (contentUri != null) {
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); // temp permission for receiving app to read this file
                shareIntent.setDataAndType(contentUri, getContentResolver().getType(contentUri));
                shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri);
                shareIntent.setType("image/png");
                startActivity(Intent.createChooser(shareIntent, "Choose an app"));
            }
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
                Intent i = new Intent(SharedDataActivity.this,SharedDataActivity.class);
                i.addFlags(FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("Text", moviesQuotesList.get(position).getQuoteTxt());
                i.putExtra("Writtername", moviesQuotesList.get(position).getWriterName());

                startActivity(i);

            }
        });

    }
}
