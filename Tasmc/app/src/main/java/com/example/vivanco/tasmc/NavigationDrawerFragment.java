package com.example.vivanco.tasmc;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment {

    private RecyclerView recyclerView;
    public static final String PREF_FILE_NAME = "testpref";
    public static final String KEY_USER_LEARNED_DRAWER = "user_learned_drawer";
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mdDrawerLayout;
    private boolean mUserLearnedDrawer;
    private boolean mFromSavedInstanceState;
    private View containerView;
    private Adaptador adaptador;

    public NavigationDrawerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserLearnedDrawer = Boolean.valueOf(readFromPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, "false"));
        if (savedInstanceState != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        recyclerView = (RecyclerView) layout.findViewById(R.id.drawerList);
        adaptador = new Adaptador(getActivity(), getDatos());
        recyclerView.setAdapter(adaptador);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return layout;
    }

    //metodo para añadir la informacion
    public static List<Informacion> getDatos() {
        List<Informacion> datos = new ArrayList<>();
        int[] iconos = {R.drawable.hoteln, R.drawable.avion, R.drawable.infoaicmn, R.drawable.equipajen,
                R.drawable.itinerarion, R.drawable.rutan, R.drawable.ubiktn, R.drawable.infovuelon};
        String[] titulos = {"Hoteles", "Vuelos", "Info AICM", "Lista de Equipaje", "Itinerario de Viaje", "Ruta al AICM",
                "Ubicate", "Info de Vuelo"};
        for (int i = 0; i < titulos.length && i < iconos.length; i++) {
            Informacion current = new Informacion();
            current.iconId = iconos[i];
            current.titulo = titulos[i];
            datos.add(current);
        }
        return datos;
    }


    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar) {
        containerView = getActivity().findViewById(fragmentId);
        mdDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                if (!mUserLearnedDrawer) {
                    mUserLearnedDrawer = true;
                    saveToPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, mUserLearnedDrawer + "");
                }
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }
/*Metodo para obscurecer el toolbar
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                //Log.d("XIM","offset"+slideOffset); muestra el crecimiento 1.0 abierto 0.0 cerrado
                if (slideOffset<0.6){//solo oscurece hasta un punto
                    toolbar.setAlpha(1-slideOffset);//se va obscureciendo como va avanzando
                }
            }*/
        };
        if (!mUserLearnedDrawer && !mFromSavedInstanceState) {
            mdDrawerLayout.openDrawer(containerView);
        }
        mdDrawerLayout.setDrawerListener(mDrawerToggle);
        mdDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
    }

    public static void saveToPreferences(Context context, String preferenceName, String preferenceValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(preferenceName, preferenceValue);
        editor.apply();
    }

    public static String readFromPreferences(Context context, String preferenceName, String defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(preferenceName, defaultValue);
    }


}
