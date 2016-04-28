package com.project.letsnote;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.project.letsnote.login.FacebookLogout;
import com.project.letsnote.login.PrefUtils;
import com.project.letsnote.login.User;
import com.project.letsnote.star.LikeButtonView;

public class PruebaPerfil extends AppCompatActivity {
    ImageView menu1, menu2, menu3, menu4;
    TextView nombreUsuario, localidadUsuario, numeroNotas, numeroLikes, numeroSeguidos, numeroSeguidores, descripcion;
    ImageView fotoPerfil;
    LikeButtonView btnSeguir;
    User user;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba_perfil);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        nombreUsuario = (TextView) findViewById(R.id.nombreUsuario);
        localidadUsuario = (TextView) findViewById(R.id.localidadUsuario);
        numeroNotas = (TextView) findViewById(R.id.numeroNotas);
        numeroLikes = (TextView) findViewById(R.id.numeroLikes);
        //numeroSeguidos = (TextView) findViewById(R.id.numeroSeguidos);
        numeroSeguidores = (TextView) findViewById(R.id.numeroSeguidores);
        descripcion = (TextView) findViewById(R.id.descripcionUser);
        btnSeguir = (LikeButtonView) findViewById(R.id.starButton);
        fotoPerfil = (ImageView) findViewById(R.id.imagenPerfil);


        user = PrefUtils.getCurrentUser(this);

        nombreUsuario.setText(user.getName());
        localidadUsuario.setText(user.getLocation());
        numeroNotas.setText(String.valueOf(user.getNumNotas()));
        numeroLikes.setText(String.valueOf(user.getTotalLikes()));
        //numeroSeguidos.setText(String.valueOf(user.getSeguidos()));
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
                Intent salir = new Intent (PruebaPerfil.this, FacebookLogout.class);
                startActivity(salir);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_map){
            Intent intent = new Intent(getBaseContext(), MapsActivity.class);
            startActivity(intent);
        }else if(id == R.id.action_notes){
            Intent intent = new Intent(getBaseContext(), PruebaPerfil.class);
            startActivity(intent);
        }else if(id == R.id.action_profile){
            Intent intent = new Intent(getBaseContext(), PerfilAjeno.class);
            startActivity(intent);
        }else if(id == R.id.action_messages){
            Intent intent = new Intent(getBaseContext(), PerfilAjeno.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}
