package com.ameza.petagram.pojo;

import java.io.Serializable;


@SuppressWarnings("serial")
public class Mascota implements Serializable {

    private int id;
    private int fotoMascota;
    private String nombreMascota;
    private boolean favorito;
    private int calificacion;

    public Mascota(int fotoMascota, String nombreMascota, boolean favorito, int calificacion) {
        this.fotoMascota = fotoMascota;
        this.nombreMascota = nombreMascota;
        this.favorito = favorito;
        this.calificacion = calificacion;
    }

    public Mascota() {

    }

    public int getFotoMascota() {
        return fotoMascota;
    }

    public void setFotoMascota(int fotoMascota) {
        this.fotoMascota = fotoMascota;
    }

    public String getNombreMascota() {
        return nombreMascota;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public Mascota setMascota(Mascota mas){
        this.setCalificacion(mas.getCalificacion());
        this.setFavorito(mas.isFavorito());
        this.setFotoMascota(mas.getFotoMascota());
        return this;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Foto id: "+getFotoMascota()+" Nombre: "+getNombreMascota()+" es favorito: "+isFavorito()+" calificación "+getCalificacion();
    }

}
