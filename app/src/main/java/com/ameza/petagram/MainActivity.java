package com.ameza.petagram;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.ameza.petagram.adapter.PageAdapter;
import com.ameza.petagram.controlador.Acciones;
import com.ameza.petagram.vista.fragment.PerfilFragment;
import com.ameza.petagram.vista.fragment.RecyclerViewFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private Acciones accionesMenus;

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar=(Toolbar)findViewById(R.id.toolBar);
        tabLayout=(TabLayout) findViewById(R.id.tapLayout);
        viewPager=(ViewPager) findViewById(R.id.viewPager);

        setUpViewPager();

        accionesMenus= Acciones.getInstance();

        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);


        if(toolbar!=null){
            setSupportActionBar(toolbar);
        }

    }

    private ArrayList<Fragment> agregarFragments(){
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new RecyclerViewFragment());
        fragments.add(new PerfilFragment());
        return fragments;
    }

    private void setUpViewPager(){
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(),agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_action_name);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_action_cara_panda);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return accionesMenus.accionesMune(item, super.onOptionsItemSelected(item), this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        /*Intent intent = new Intent(MainActivity.this, DetalleMascota.class);
        intent.putExtra(getResources().getString(R.string.pNombre), mascotas.get(position).getNombre());
        intent.putExtra(getResources().getString(R.string.pTelefono), mascotas.get(position).getTelefono());
        intent.putExtra(getResources().getString(R.string.pEmail), mascotas.get(position).getEmail());
        startActivity(intent); */
        //finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //setUpViewPager();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setUpViewPager();
    }

}
