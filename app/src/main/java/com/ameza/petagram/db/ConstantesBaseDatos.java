package com.ameza.petagram.db;

/**
 * Created by andres on 5/03/17.
 */

public final class ConstantesBaseDatos {

    public static final String DATABASE_NAME="contactos";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_MASCOTAS = "mascota";
    public static final String TABLE_MASCOTAS_ID = "id";
    public static final String TABLE_MASCOTAS_FOTO_MASCOTA = "foto";
    public static final String TABLE_MASCOTAS_NOMBRE_MASCOTA = "nombre";


    public static final String TABLE_LIKES_MASCOTAS = "mascota_likes";
    public static final String TABLE_LIKES_MASCOTAS_ID = "id";
    public static final String TABLE_LIKES_MASCOTAS_ID_CONTACTO = "id_mascota";
    public static final String TABLE_LIKES_MASCOTAS_NUMERO_LIKES = "numero_likes";
}
