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
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.ui.FirebaseListAdapter;
import com.project.letsnote.login.User;

public class ListaNotasActivity extends AppCompatActivity {

    ListView noteList;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_notas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        noteList = (ListView) findViewById(R.id.noteList);

        //Le decimos a Firebase que este sera el contexto
        Firebase.setAndroidContext(this);

        //Creamos una referencia a nuestra bd de Firebase
        final Firebase refNotas = new Firebase("https://letsnote.firebaseio.com/").child("notas");

        final FirebaseListAdapter adapter = new FirebaseListAdapter<Nota>(this, Nota.class, R.layout.lista_notas_row, refNotas) {
            @Override
            protected void populateView(View v, Nota model, int position) {
                super.populateView(v, model, position);

                //Toast.makeText(getBaseContext(), "Cargando notas", Toast.LENGTH_SHORT).show();
                final  ImageView foto = (ImageView) v.findViewById(R.id.imagenUserLista);
                final TextView nombre = (TextView) v.findViewById(R.id.nombreUserLista);
                TextView titulo = (TextView) v.findViewById(R.id.tituloNotaLista);
                TextView likes = (TextView) v.findViewById(R.id.likesNotaLista);

                titulo.setText(model.getTitulo());



                //Creamos una referencia a nuestra bd de Firebase
                final Firebase refUsers = new Firebase("https://letsnote.firebaseio.com/").child("users");

                Query queryRef = refUsers.orderByChild("facebookID").equalTo(model.getUser());

                queryRef.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot snapshot, String previousChild) {
                        User a =  snapshot.getValue(User.class);

                        Glide.with(getBaseContext()).load(a.getPictureUrl()).asBitmap().fitCenter().into(new BitmapImageViewTarget(foto) {
                            @Override
                            protected void setResource(Bitmap resource) {
                                RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(getBaseContext().getResources(), resource);
                                circularBitmapDrawable.setCircular(true);
                                foto.setImageDrawable(circularBitmapDrawable);
                            }
                        });
                        nombre.setText(a.getName());
                    }
                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });

            }
        };

        noteList.setAdapter(adapter);

        noteList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                intent = new Intent(getBaseContext(), DetailActivity.class);
                Firebase ref = adapter.getRef(position);
                Toast.makeText(getApplicationContext(), ref.toString(), Toast.LENGTH_LONG).show();
                intent.putExtra("nota_ref", ref.toString());
                startActivity(intent);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_app, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.item_uno) {
            intent = new Intent(this, MapsActivity.class);
            startActivity(intent);
            return true;
        }else if (id == R.id.item_dos) {
            intent = new Intent(this, ListaNotasActivity.class);
            startActivity(intent);
            return true;
        }else if (id == R.id.item_tres) {
            /*intent = new Intent(this, MiPerfil.class);
            startActivity(intent);*/
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
