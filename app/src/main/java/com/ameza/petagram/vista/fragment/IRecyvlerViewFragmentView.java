package com.ameza.petagram.vista.fragment;

import com.ameza.petagram.adapter.MascotaAdaptador;
import com.ameza.petagram.pojo.Mascota;

import java.util.ArrayList;



public interface IRecyvlerViewFragmentView {

    public void generarLinearLayoutVertical();
    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas);
    public void inicializarAdaptadorRV(MascotaAdaptador adaptador);
}
