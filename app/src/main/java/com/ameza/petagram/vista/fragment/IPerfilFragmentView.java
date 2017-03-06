package com.ameza.petagram.vista.fragment;

import com.ameza.petagram.adapter.PerfilAdaptador;
import com.ameza.petagram.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by andres on 5/03/17.
 */

public interface IPerfilFragmentView {

    public void generarLinearLayoutVertical();
    public PerfilAdaptador crearAdaptador(ArrayList<Mascota> mascotas);
    public void inicializarAdaptadorRV(PerfilAdaptador adaptador);
}
