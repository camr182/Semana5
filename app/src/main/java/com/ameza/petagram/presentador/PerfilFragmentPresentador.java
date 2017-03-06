package com.ameza.petagram.presentador;

import android.content.Context;

import com.ameza.petagram.pojo.Mascota;
import com.ameza.petagram.vista.fragment.IPerfilFragmentView;
import com.ameza.petagram.db.ConstructorMascotas;

import java.util.ArrayList;

/**
 * Created by andres on 5/03/17.
 */

public class PerfilFragmentPresentador implements IRecyclerViewFragmentPresenter{

    private IPerfilFragmentView iPerfilFragmentView;
    private Context context;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<Mascota> mascotas;

    public PerfilFragmentPresentador(IPerfilFragmentView iPerfilFragmentView, Context context) {
        this.iPerfilFragmentView = iPerfilFragmentView;
        this.context = context;
        constructorMascotas = new ConstructorMascotas(context);
        obtenerContactosBaseDatos();
    }

    @Override
    public void obtenerContactosBaseDatos() {
        mascotas=constructorMascotas.obtenerDatosPrimeraMascota();
        mostrarContactosRV();
    }

    @Override
    public void mostrarContactosRV() {
        iPerfilFragmentView.inicializarAdaptadorRV(iPerfilFragmentView.crearAdaptador(mascotas));
        iPerfilFragmentView.generarLinearLayoutVertical();
    }
}
