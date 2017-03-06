package com.ameza.petagram.controlador;

import android.app.Activity;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.ameza.petagram.BiografiaActivity;
import com.ameza.petagram.MascotasFavoritas;
import com.ameza.petagram.Correo.MenuContacto;
import com.ameza.petagram.R;
import com.ameza.petagram.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by andres on 18/02/17.
 */

public class Acciones implements View.OnClickListener{

    /**objeto Singleton*/
    private static Acciones singlenAcciones=null;
    private ArrayList<Mascota> ultimasMascotasFavoritas;

    private Acciones(){
        this.ultimasMascotasFavoritas = new ArrayList< Mascota>();
    }

    public static Acciones getInstance() {
        if (singlenAcciones == null) {
            synchronized (Acciones.class) {
                if (singlenAcciones == null) {
                    singlenAcciones = new Acciones();
                }
            }
        }
        return singlenAcciones;
    }

    @Override
    public void onClick(View v) {

    }

    public boolean accionesMune(MenuItem item, boolean superOption, Activity activity){
        Intent intent=null;
        switch (item.getItemId()) {
            case R.id.actionFavoritos:
                intent=new Intent(activity, MascotasFavoritas.class);
                activity.startActivity(intent);
                //Log.i("ActionBar", "actionFavoritos!");
                return true;
            case R.id.actionAcercaDe:
                intent=new Intent(activity, BiografiaActivity.class);
                activity.startActivity(intent);
                Toast.makeText(activity.getApplicationContext(),"Aplicación realizada por ANDRES FELIPE RAMIREZ CAICEDO: andresyfr@gmail.com",Toast.LENGTH_SHORT).show();
                //Log.i("ActionBar", "acerca de!");;
                return true;
            case R.id.actionContacto:
                intent=new Intent(activity, MenuContacto.class);
                activity.startActivity(intent);
                Toast.makeText(activity.getApplicationContext(),"Contacto Aplicación realizada por ANDRES FELIPE RAMIREZ CAICEDO: andresyfr@gmail.com",Toast.LENGTH_SHORT).show();
                //Log.i("ActionBar", "acerca de!");;
                return true;
            default:
                return superOption;
        }
    }

    public boolean cambiarDatosMascotas(Mascota mascotaOriginal, Mascota mascotaCambiada){
        int i=0;
        while (i< ultimasMascotasFavoritas.size()){
            if(ultimasMascotasFavoritas.get(i).getCalificacion()==mascotaOriginal.getCalificacion() && ultimasMascotasFavoritas.get(i).getNombreMascota().equals(mascotaOriginal.getNombreMascota()) && ultimasMascotasFavoritas.get(i).getFotoMascota() == mascotaOriginal.getFotoMascota()){
                ultimasMascotasFavoritas.set(i,mascotaCambiada);
                return true;
            }
            i++;
        }return false;
    }

    public boolean anadirMascotaFavorita(Mascota mascota){
        if(mascota.getCalificacion()>0) {
            if (ultimasMascotasFavoritas.size() >= 5) {
                ultimasMascotasFavoritas.remove(0);
                ultimasMascotasFavoritas.add(mascota);
            } else {
                ultimasMascotasFavoritas.add(mascota);
            }
            return true;
        }else{
            return false;
        }
    }

    private int idRepetido(Mascota mascota){
        int i=0;
        while (i<ultimasMascotasFavoritas.size()){
            if(ultimasMascotasFavoritas.get(i).getId()==mascota.getId()){
                return i;
            }
        }
        return -1;
    }

    public ArrayList<Mascota> getUltimasMascotasFavoritas() {
        return ultimasMascotasFavoritas;
    }
}
