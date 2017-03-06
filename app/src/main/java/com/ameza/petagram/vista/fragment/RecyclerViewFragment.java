package com.ameza.petagram.vista.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ameza.petagram.adapter.MascotaAdaptador;
import com.ameza.petagram.controlador.Acciones;
import com.ameza.petagram.R;
import com.ameza.petagram.pojo.Mascota;
import com.ameza.petagram.presentador.IRecyclerViewFragmentPresenter;
import com.ameza.petagram.presentador.RecyclerViewFragmentPresentador;

import java.util.ArrayList;

/**
 * Created by andres on 2/03/17.
 */

public class RecyclerViewFragment extends Fragment implements IRecyvlerViewFragmentView{

    private ArrayList<Mascota> mascotas;
    private RecyclerView listaMascotas;
    private IRecyclerViewFragmentPresenter presenter;


    private Acciones accionesMenus;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_recyclerview, container, false);

        accionesMenus = Acciones.getInstance();

        listaMascotas = (RecyclerView) v.findViewById(R.id.rvLstMascotas);

        presenter = new RecyclerViewFragmentPresentador(this,getContext());
        //inicializarListaContactos();


        return v;
    }

    @Override
    public void generarLinearLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        listaMascotas.setLayoutManager(llm);
    }

    @Override
    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas) {
        MascotaAdaptador mascotaAdaptador = new MascotaAdaptador(mascotas,getActivity());
        return mascotaAdaptador;
    }

    @Override
    public void inicializarAdaptadorRV(MascotaAdaptador adaptador) {
        listaMascotas.setAdapter(adaptador);
        adaptador.notifyDataSetChanged();
        //invalidateViews();
    }

    /*
    @Override
    protected void onResume() {
        super.onResume();
        inicializarListaPerfiles();
        inicializarAdaptador();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        inicializarListaPerfiles();
        inicializarAdaptador();
    }
*/

/*
    public void inicializarListaContactos(){
        mascotas = accionesMenus.getUltimasMascotasFavoritas();
    }
*/

}
