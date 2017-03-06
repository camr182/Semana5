package com.ameza.petagram.vista.fragment;

import com.ameza.petagram.adapter.MascotaAdaptador;
import com.ameza.petagram.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by andres on 5/03/17.
 */

public interface IRecyvlerViewFragmentView {

    public void generarLinearLayoutVertical();
    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas);
    public void inicializarAdaptadorRV(MascotaAdaptador adaptador);
}
