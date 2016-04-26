package com.project.letsnote;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.project.letsnote.login.FacebookLogout;
import com.project.letsnote.login.PrefUtils;
import com.project.letsnote.login.User;

public class PerfilAjeno extends AppCompatActivity {

    TextView nombreUsuario, localidadUsuario, numeroNotas, numeroLikes, numeroSeguidos, numeroSeguidores, descripcion;
    ImageView fotoPerfil;
    ImageButton btnSeguir;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_ajeno);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        nombreUsuario = (TextView) findViewById(R.id.nombreUsuario);
        localidadUsuario = (TextView) findViewById(R.id.localidadUsuario);
        numeroNotas = (TextView) findViewById(R.id.numeroNotas);
        numeroLikes = (TextView) findViewById(R.id.numeroLikes);
        numeroSeguidos = (TextView) findViewById(R.id.numeroSeguidos);
        numeroSeguidores = (TextView) findViewById(R.id.numeroSeguidores);
        descripcion = (TextView) findViewById(R.id.descripcionUser);
        btnSeguir = (ImageButton) findViewById(R.id.btnSeguir);
        fotoPerfil = (ImageView) findViewById(R.id.imagenPerfil);


        user = PrefUtils.getCurrentUser(this);

        nombreUsuario.setText(user.getName());
        localidadUsuario.setText(user.getLocation());
        numeroNotas.setText(String.valueOf(user.getNumNotas()));
        numeroLikes.setText(String.valueOf(user.getTotalLikes()));
        numeroSeguidos.setText(String.valueOf(user.getSeguidos()));
        numeroSeguidores.setText(String.valueOf(user.getSeguidores()));
        descripcion.setText(user.getDescripcion());


        Glide.with(this).load(user.getPictureUrl()).asBitmap().centerCrop().into(new BitmapImageViewTarget(fotoPerfil) {
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
                Intent salir = new Intent (PerfilAjeno.this, FacebookLogout.class);
                startActivity(salir);

            }
        });
    }

}
