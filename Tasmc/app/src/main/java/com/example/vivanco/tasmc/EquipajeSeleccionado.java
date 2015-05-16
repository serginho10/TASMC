package com.example.vivanco.tasmc;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.SparseArray;
import android.widget.ExpandableListView;

/**
 * Created by ISC_SERGIO on 15/05/15.
 */
public class EquipajeSeleccionado extends ActionBarActivity {
    SparseArray<Grupo> groups = new SparseArray<Grupo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listaseleccionada);
        createData();
        ExpandableListView listView = (ExpandableListView) findViewById(R.id.listView);
        ListaExpandibleAdapter adapter = new ListaExpandibleAdapter(this,
                groups);
        listView.setAdapter(adapter);
    }

    public void createData() {
        for (int j = 0; j < 5; j++) {
            Grupo group = new Grupo("Test " + j);
            for (int i = 0; i < 5; i++) {
                group.children.add("Sub Item" + i);
            }
            groups.append(j, group);
        }
    }
}
