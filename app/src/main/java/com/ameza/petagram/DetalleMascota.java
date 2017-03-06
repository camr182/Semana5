package com.ameza.petagram;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ameza.petagram.controlador.Acciones;
import com.ameza.petagram.pojo.Mascota;
import com.ameza.petagram.db.ConstructorMascotas;

public class DetalleMascota extends AppCompatActivity {

    private ImageView imgVFotoMascota;
    private TextView tvNombreMascota;
    private ImageButton imgBHuesoCalificacion;
    private TextInputEditText edTexCalificacion;
    private Button btnGuardar;

    private Acciones accionesMenus;
    private Mascota mascota;

    private boolean favorito = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_mascota);

        accionesMenus = Acciones.getInstance();

        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle parametros = getIntent().getExtras();
        final int imagen = parametros.getInt(getResources().getString(R.string.pImagen));//imagen
        final String nombre = parametros.getString(getResources().getString(R.string.pNombre));//nombre
        final int calificacion = parametros.getInt(getResources().getString(R.string.pCalificacion));//calificación
        mascota = (Mascota) parametros.getSerializable("Mascota");

        imgVFotoMascota         = (ImageView) findViewById(R.id.imageView_detalle_mascota);
        tvNombreMascota         = (TextView) findViewById(R.id.tvNombre_mascota_detalle_mascota);
        imgBHuesoCalificacion   = (ImageButton) findViewById(R.id.imgvHuesoLlenoMascotaDetalle);
        edTexCalificacion       = (TextInputEditText) findViewById(R.id.editTextHusesoMascotaDetalle);

        btnGuardar              = (Button) findViewById(R.id.btnGuardarDetalle);

        modificarFavoritoInicial();

        imgBHuesoCalificacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modificarFavorito();
            }
        });

        btnGuardar.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    int calificacion=Integer.parseInt(edTexCalificacion.getText().toString());

                    ConstructorMascotas constructorMascotas = new ConstructorMascotas(DetalleMascota.this);
                    constructorMascotas.darLikeMascota(mascota);
                    mascota.setCalificacion(mascota.getCalificacion()+1);
                    accionesMenus.anadirMascotaFavorita(mascota);
                    boolean cambio=accionesMenus.cambiarDatosMascotas(mascota,new Mascota(imagen,nombre,favorito,calificacion));
                    if(cambio)Toast.makeText(DetalleMascota.this,"Mascota "+mascota.getNombreMascota()+" Guardada Correctamente!",Toast.LENGTH_SHORT).show(); else Toast.makeText(DetalleMascota.this,"Error al guardar la mascota "+mascota.getNombreMascota()+"!",Toast.LENGTH_SHORT).show();
                    finish();
                }catch (Exception ex){
                    edTexCalificacion.setError("Solo ingrese números");
                    Toast.makeText(DetalleMascota.this,"Error al guardar la mascota "+mascota.getNombreMascota()+"!",Toast.LENGTH_SHORT).show();
                }

            }
        });

        imgVFotoMascota.setImageResource(imagen);
        tvNombreMascota.setText(nombre);
        imgBHuesoCalificacion.setImageResource(calificacion>0?R.drawable.hueso_lleno:R.drawable.hueso_lleno);
        edTexCalificacion.setText(calificacion+"");
    }

    private void modificarFavoritoInicial(){
        if(mascota.isFavorito()){
            imgBHuesoCalificacion.setImageResource(R.drawable.hueso_lleno);
            favorito=true;
        }else{
            imgBHuesoCalificacion.setImageResource(R.drawable.hueso_lleno);
            favorito=false;
        }
        System.out.println("mascota es +"+mascota);
    }

    private void modificarFavorito(){
        if(favorito){
            imgBHuesoCalificacion.setImageResource(R.drawable.hueso_lleno);
            favorito=false;
            //Toast.makeText(DetalleMascota.this,""+mascota.getNombreMascota()+" ya no pertenece a tus favoritos!",Toast.LENGTH_SHORT).show();
        }else{
            imgBHuesoCalificacion.setImageResource(R.drawable.hueso_lleno);
            favorito=true;
            //Toast.makeText(DetalleMascota.this,""+mascota.getNombreMascota()+" ahora pertenece a tus favoritos!",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_favoritos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return accionesMenus.accionesMune(item, super.onOptionsItemSelected(item),this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        modificarFavoritoInicial();
    }

    @Override
    protected void onResume() {
        super.onResume();
        modificarFavoritoInicial();
    }
}
