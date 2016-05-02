package com.project.letsnote;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.File;

public class DetailActivity extends AppCompatActivity {

    TextView nombreUsuario, descripcionNota;
    ImageView imagenNota;
    VideoView videoNota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nombreUsuario = (TextView) findViewById(R.id.textTitle);
        descripcionNota = (TextView) findViewById(R.id.textReport);
        imagenNota = (ImageView) findViewById(R.id.photoReport);
        videoNota = (VideoView) findViewById(R.id.videoReport);
/*

        imagenNota.setVisibility(View.INVISIBLE);
        videoNota.setVisibility(View.INVISIBLE);

        nombreUsuario.setText(model.getTitle());
        descripcionNota.setText(model.getNota());

        if(model.getImagePath()!= null) {
            imageView.setVisibility(View.VISIBLE);
            File imagePath = new File(model.getImagePath());
            Picasso.with(getContext()).load(imagePath).fit().into(imagenNota);
        }
        if(model.getVideoPath()!= null) {
            MediaController mediaController = new MediaController(getContext());
            videoNota.setVisibility(View.VISIBLE);
            videoNota.setVideoPath(model.getVideoPath());
            videoNota.setMediaController(mediaController);
            mediaController.hide();
            //videoView.requestFocus();
            videoNota.start();

        }
        if(model.getImagePath() == null && model.getVideoPath()== null){
            imagenNota.setVisibility(View.VISIBLE);
            Picasso.with(getContext()).load(R.drawable.noimageavailable).fit().into(imagenNota);
        }

*/


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
