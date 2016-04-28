package com.project.letsnote;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

public class MiPerfil extends AppCompatActivity {

    TextView nombreUsuario, localidadUsuario, numeroNotas, numeroLikes, numeroSeguidos, numeroSeguidores, descripcion;
    ImageView fotoPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_perfil);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nombreUsuario = (TextView) findViewById(R.id.nombreMiPerfil);
        localidadUsuario = (TextView) findViewById(R.id.localidadMiPerfil);
        numeroNotas = (TextView) findViewById(R.id.numeroNotas);
        numeroLikes = (TextView) findViewById(R.id.numeroLikes);
        numeroSeguidos = (TextView) findViewById(R.id.numeroSeguidos);
        numeroSeguidores = (TextView) findViewById(R.id.numeroSeguidores);
        descripcion = (TextView) findViewById(R.id.descripcionUser);
        fotoPerfil = (ImageView) findViewById(R.id.imagenPerfil);

        nombreUsuario.setText("Alex Ruiz Medina");
        localidadUsuario.setText("Barcelona, ES");
        numeroNotas.setText("16");
        numeroLikes.setText("100");
        numeroSeguidos.setText("200");
        numeroSeguidores.setText("150");
        descripcion.setText("Aloha!");

        Glide.with(this).load(R.drawable.fotoperfil).asBitmap().centerCrop().into(new BitmapImageViewTarget(fotoPerfil) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(getBaseContext().getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                fotoPerfil.setImageDrawable(circularBitmapDrawable);
            }
        });

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