package com.ameza.petagram.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ameza.petagram.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by andres on 5/03/17.
 */

public class BaseDatos extends SQLiteOpenHelper{

    private Context context;

    public BaseDatos(Context context) {
        super(context, ConstantesBaseDatos.DATABASE_NAME, null, ConstantesBaseDatos.DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCrearTablaMascota = "CREATE TABLE "+ConstantesBaseDatos.TABLE_MASCOTAS+"("+
                                        ConstantesBaseDatos.TABLE_MASCOTAS_ID                       +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                                        ConstantesBaseDatos.TABLE_MASCOTAS_FOTO_MASCOTA             +" INTEGER, "+
                                        ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE_MASCOTA           +" TEXT"+
                                        ")";

        db.execSQL(queryCrearTablaMascota);

        String queryCrearTablaLikesMascotas = "CREATE TABLE " + ConstantesBaseDatos.TABLE_LIKES_MASCOTAS + "(" +
                ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_ID                 +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_ID_CONTACTO        +" INTEGER, "+
                ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_NUMERO_LIKES       +" INTEGER, "+
                "FOREIGN KEY (" + ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_ID_CONTACTO + ") " +
                "REFERENCES " + ConstantesBaseDatos.TABLE_MASCOTAS+ "(" + ConstantesBaseDatos.TABLE_MASCOTAS_ID + ")" +
                ")";

        db.execSQL(queryCrearTablaLikesMascotas);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST"+ConstantesBaseDatos.TABLE_MASCOTAS);
        db.execSQL("DROP TABLE IF EXIST"+ConstantesBaseDatos.TABLE_LIKES_MASCOTAS);
        onCreate(db);
    }

    public ArrayList<Mascota> obtenerMascotas(){
        //int flag = (boolValue)? 1 : 0;
        ArrayList<Mascota> mascotas= new ArrayList<>();

        String query = "SELECT * FROM "+ConstantesBaseDatos.TABLE_MASCOTAS;

        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor= db.rawQuery(query,null);

        while (cursor.moveToNext()){
            Mascota mascota=new Mascota();

            mascota.setId(cursor.getInt(0));
            mascota.setFotoMascota(cursor.getInt(1));
            mascota.setNombreMascota(cursor.getString(2));
            mascota.setCalificacion(obtenerLikeMascota(mascota));



            mascotas.add(mascota);
        }

        db.close();

        return mascotas;
    }

    public void insertarMascota(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_MASCOTAS,null,contentValues);
        db.close();
    }

    public void insertarLikeContacto(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_LIKES_MASCOTAS,null,contentValues);
        db.close();
    }

    public int obtenerLikeMascota(Mascota mascota){
        int likes = 0;
        String query = "SELECT COUNT ("+ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_NUMERO_LIKES+")"+
                " FROM "+ConstantesBaseDatos.TABLE_LIKES_MASCOTAS+
                " WHERE "+ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_ID_CONTACTO+" = "+mascota.getId();
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor registros = db.rawQuery(query,null);
        if(registros.moveToNext()){
            likes=registros.getInt(0);
        }
        db.close();
        return likes;
    }

    public ArrayList<Mascota> obtenerListaLikeMascota(){

        String query1 = "SELECT * FROM "+ConstantesBaseDatos.TABLE_MASCOTAS;

        SQLiteDatabase db1=this.getWritableDatabase();
        Cursor cursor= db1.rawQuery(query1,null);
        Mascota mascota = new Mascota();

        ArrayList<Mascota> mascotas = new ArrayList<>();

        if (cursor.moveToNext()){
            mascota.setId(cursor.getInt(0));
            mascota.setFotoMascota(cursor.getInt(1));
            mascota.setNombreMascota(cursor.getString(2));
            mascota.setCalificacion(obtenerLikeMascota(mascota));

                String query = "SELECT "+ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_NUMERO_LIKES +
                    " FROM "+ConstantesBaseDatos.TABLE_LIKES_MASCOTAS+
                    " WHERE "+ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_ID_CONTACTO+" = "+mascota.getId();

                db1.close();
                SQLiteDatabase db=this.getWritableDatabase();
                Cursor registros = db.rawQuery(query,null);

                while(registros.moveToNext()){
                    mascotas.add(new Mascota(mascota.getFotoMascota(),mascota.getNombreMascota(), true, registros.getInt(0)));
                }
                db.close();
        }
        System.out.println("tama;o de lista primera mascota>>>>>>>>>>>>>>>>> "+mascotas.size());
        return mascotas;
    }

}
