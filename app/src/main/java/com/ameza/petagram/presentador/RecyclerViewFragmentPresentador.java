package com.ameza.petagram.presentador;

import android.content.Context;

import com.ameza.petagram.pojo.Mascota;
import com.ameza.petagram.vista.fragment.IRecyvlerViewFragmentView;
import com.ameza.petagram.db.ConstructorMascotas;

import java.util.ArrayList;

/**
 * Created by andres on 5/03/17.
 */

public class RecyclerViewFragmentPresentador implements IRecyclerViewFragmentPresenter{

    private IRecyvlerViewFragmentView iRecyvlerViewFragmentView;
    private Context context;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<Mascota> mascotas;

    public RecyclerViewFragmentPresentador(IRecyvlerViewFragmentView iRecyvlerViewFragmentView, Context context) {
        this.iRecyvlerViewFragmentView = iRecyvlerViewFragmentView;
        this.context = context;
        constructorMascotas = new ConstructorMascotas(context);
        obtenerContactosBaseDatos();
    }

    @Override
    public void obtenerContactosBaseDatos() {
        mascotas=constructorMascotas.obtenerDatos();
        mostrarContactosRV();
    }

    @Override
    public void mostrarContactosRV() {
        iRecyvlerViewFragmentView.inicializarAdaptadorRV(iRecyvlerViewFragmentView.crearAdaptador(mascotas));
        iRecyvlerViewFragmentView.generarLinearLayoutVertical();
    }
}
