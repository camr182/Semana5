package com.ameza.petagram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.ameza.petagram.adapter.MascotaAdaptador;
import com.ameza.petagram.controlador.Acciones;
import com.ameza.petagram.db.ConstructorMascotas;
import com.ameza.petagram.pojo.Mascota;
import com.ameza.petagram.presentador.IRecyclerViewFragmentPresenter;

import java.util.ArrayList;

public class MascotasFavoritas extends AppCompatActivity implements IRecyclerViewFragmentPresenter {

    private ArrayList<Mascota> mascotasFavoritas;
    private RecyclerView listaMascotasFavoritas;
    private MascotaAdaptador mascotaAdaptadorFavoritas;
    private ConstructorMascotas constructorMascotas;

    private Acciones acciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascotas_favoritas);

        acciones = Acciones.getInstance();
        constructorMascotas = new ConstructorMascotas(this);
        mascotasFavoritas = new ArrayList<Mascota>();


        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBarFavoritas);
        setSupportActionBar(miActionBar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listaMascotasFavoritas = (RecyclerView) findViewById(R.id.rvLstMascotasFavoritas);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        listaMascotasFavoritas.setLayoutManager(llm);
        inicializarListaContactos();
        inicializarAdaptador();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_favoritos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return acciones.accionesMune(item,super.onOptionsItemSelected(item),this);
    }

    public void inicializarListaContactos(){
        mascotasFavoritas = constructorMascotas.obtenerDatosFavoritos();
        if(mascotasFavoritas.isEmpty())Toast.makeText(MascotasFavoritas.this,"No tienes mascotas Favoritas!",Toast.LENGTH_SHORT).show();
    }

    public void inicializarAdaptador(){
        mascotaAdaptadorFavoritas = new MascotaAdaptador(mascotasFavoritas,this);
        listaMascotasFavoritas.setAdapter(mascotaAdaptadorFavoritas);
        mascotaAdaptadorFavoritas.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        inicializarListaContactos();
        inicializarAdaptador();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        inicializarListaContactos();
        inicializarAdaptador();
    }

    @Override
    public void obtenerContactosBaseDatos() {

    }

    @Override
    public void mostrarContactosRV() {

    }
}
