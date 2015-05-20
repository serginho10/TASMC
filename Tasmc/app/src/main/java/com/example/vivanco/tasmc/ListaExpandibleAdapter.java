package com.example.vivanco.tasmc;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by ISC_SERGIO on 15/05/15.
 */
public class ListaExpandibleAdapter extends BaseExpandableListAdapter {
    private final SparseArray<Grupo> groups;
    public LayoutInflater inflater;
    public Activity activity;
    Context context;

    public ListaExpandibleAdapter(Activity act, SparseArray<Grupo> groups, Context context) {
        activity = act;
        this.context = context;
        this.groups = groups;
        inflater = act.getLayoutInflater();
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return groups.get(groupPosition).children.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    static class ViewHolder {
        protected TextView text;
        protected CheckBox checkbox;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        final String children = (String) getChild(groupPosition, childPosition);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.filadetalle, null);
        }
        final CheckBox cb = (CheckBox) convertView.findViewById(R.id.checkBox1);
        cb.setText(children);
        cb.setTextSize(18);
        cb.setTag(0, new Objeto(1, children, groups.get(groupPosition).getString(), false));
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (cb.isChecked()) {
                    Objeto obj = (Objeto) cb.getTag(0);
                    obj.setChecked(true);
                    cb.setTag(0, obj);
                } else {
                    Objeto obj = (Objeto) cb.getTag(0);
                    obj.setChecked(false);
                    cb.setTag(0, obj);
                }
/*
                grabar(buttonView.getText().toString());
                String[] archivos = context.fileList();

                if (existe(archivos, "notas.txt"))
                    try {
                        InputStreamReader archivo = new InputStreamReader(context.openFileInput("notas.txt"));
                        BufferedReader br = new BufferedReader(archivo);
                        String linea = br.readLine();
                        String todo = "";
                        while (linea != null) {
                            todo = todo + linea + "\n";
                            linea = br.readLine();
                        }
                        br.close();
                        archivo.close();
                        System.out.println(todo);
                    } catch (IOException e) {
                    }*/
            }
        });
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(activity, children,Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }

    public void grabar(String objeto) {
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(context.openFileOutput(
                    "notas.txt", Activity.MODE_PRIVATE));
            archivo.write(objeto);
            archivo.flush();
            archivo.close();
        } catch (IOException e) {
        }
        Toast.makeText(context, "Los datos fueron grabados",Toast.LENGTH_SHORT).show();
    }

    private boolean existe(String[] archivos, String archbusca) {
        for (int f = 0; f < archivos.length; f++)
            if (archbusca.equals(archivos[f]))
                return true;
        return false;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return groups.get(groupPosition).children.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public void onGroupCollapsed(int groupPosition) {
        super.onGroupCollapsed(groupPosition);
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.filagrupo, null);
        }
        Grupo group = (Grupo) getGroup(groupPosition);
        ((CheckedTextView) convertView).setText(group.string);
        ((CheckedTextView) convertView).setChecked(isExpanded);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
