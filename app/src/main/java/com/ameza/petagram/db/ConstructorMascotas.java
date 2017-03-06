package com.ameza.petagram.db;

import android.content.ContentValues;
import android.content.Context;

import com.ameza.petagram.controlador.Acciones;
import com.ameza.petagram.pojo.Mascota;
import com.ameza.petagram.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by andres on 5/03/17.
 */

public class ConstructorMascotas {
    private static final int LIKE = 1;
    private Context context;
    private Acciones acciones;

    public ConstructorMascotas(Context context) {
        this.context = context;
        acciones = Acciones.getInstance();
    }

    public ArrayList<Mascota> obtenerDatos(){
        BaseDatos bd = new BaseDatos(context);
        insertarMascotas(bd);
        return bd.obtenerMascotas();
    }

    public void insertarMascotas(BaseDatos bd){
        bd.insertarMascota(crearValoresMascotas(R.drawable.conejo,"Pablito"));
        bd.insertarMascota(crearValoresMascotas(R.drawable.gato,"Gardfield"));
        bd.insertarMascota(crearValoresMascotas(R.drawable.pato,"Lucas"));
        bd.insertarMascota(crearValoresMascotas(R.drawable.tortuga,"Miguelito"));
        bd.insertarMascota(crearValoresMascotas(R.drawable.perro,"Boby"));
            bd.insertarMascota(crearValoresMascotas(R.drawable.perro2,"Bimbo"));
        bd.insertarMascota(crearValoresMascotas(R.drawable.perro3,"El maykol"));

        Mascota mas1=new Mascota(R.drawable.conejo,"Pablito",true,1);
        mas1.setId(1);
        darLikeMascota(mas1);
        darLikeMascota(mas1);
        darLikeMascota(mas1);
        darLikeMascota(mas1);
        darLikeMascota(mas1);

        Mascota mas2=new Mascota(R.drawable.gato,"Gardfield",true,1);
        mas2.setId(2);
        darLikeMascota(mas2);

        mas2.setId(3);
        darLikeMascota(mas2);

        mas2.setId(5);
        darLikeMascota(mas2);

        mas2.setId(6);
        darLikeMascota(mas2);


        /*
        bd.insertarMascota(crearValoresMascotas(R.drawable.conejo,"Conejo Pancho", true, 5));
        bd.insertarMascota(crearValoresMascotas(R.drawable.gato,"Gato Pancho", false, 4));
        bd.insertarMascota(crearValoresMascotas(R.drawable.pato,"Pato Donald", true, 3));
        bd.insertarMascota(crearValoresMascotas(R.drawable.tortuga,"Tortuga Nija", false, 2));
        bd.insertarMascota(crearValoresMascotas(R.drawable.perro,"Perro Dalmata", true, 4));
        bd.insertarMascota(crearValoresMascotas(R.drawable.perro2,"Perra lika", true, 5));
        bd.insertarMascota(crearValoresMascotas(R.drawable.perro3,"Perro Lufi", true, 5));
        */
    }

    public ContentValues crearValoresMascotas(int foto, String nombre){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO_MASCOTA, foto);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE_MASCOTA, nombre);
        return contentValues;
    }

    //////////////////////////////////////////////////

    public void darLikeMascota(Mascota mascota){
        BaseDatos bd = new BaseDatos(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_ID_CONTACTO,mascota.getId());
        contentValues.put(ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_NUMERO_LIKES,LIKE);
        bd.insertarLikeContacto(contentValues);
    }

    public int obtenerLikeMascota(Mascota mascota){
        BaseDatos bd = new BaseDatos(context);
        return bd.obtenerLikeMascota(mascota);
    }

    public ArrayList<Mascota> obtenerDatosFavoritos() {
        BaseDatos bd = new BaseDatos(context);
        List<Mascota> mascotas = bd.obtenerMascotas();
        LinkedList<Mascota> mas = new LinkedList<>(mascotas);
        Iterator<Mascota> itMascota=mas.descendingIterator();
        int datos=0;
        while (itMascota.hasNext() && datos<5){
            Mascota mascota=itMascota.next();
            if(mascota.getCalificacion()>0) {
                if(acciones.anadirMascotaFavorita(mascota)){
                    datos++;
                }
            }
        }
        return acciones.getUltimasMascotasFavoritas();
    }

    public ArrayList<Mascota> obtenerDatosPrimeraMascota() {
        BaseDatos bd = new BaseDatos(context);
        return bd.obtenerListaLikeMascota();
    }
    //obtener mascotaSeleccionada
}
