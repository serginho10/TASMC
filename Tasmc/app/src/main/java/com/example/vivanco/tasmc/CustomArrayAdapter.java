package com.example.vivanco.tasmc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import java.util.List;

/**
 * Created by VIVANCO on 20/04/2015.
 */
public class CustomArrayAdapter extends ArrayAdapter<RenglonCheck> {
    private LayoutInflater layoutInflater;

    public CustomArrayAdapter(Context context, List<RenglonCheck> objects) {
        super(context, 0, objects);
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // holder pattern
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();

            convertView = layoutInflater.inflate(R.layout.renglon_check, null);
            holder.setTextViewTitle((TextView) convertView.findViewById(R.id.textViewTitle));
            holder.setCheckBox((CheckBox) convertView.findViewById(R.id.checkBox));
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        final RenglonCheck row = getItem(position);
        holder.getTextViewTitle().setText(row.getObjeto());
        holder.getCheckBox().setTag(row.getObjeto());
        holder.getCheckBox().setChecked(row.isChecked());
        changeBackground(getContext(), convertView, row.isChecked());
        final View fila = convertView;
        holder.getCheckBox().setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton view, boolean isChecked) {
                //asegura que se modifica la Row originalmente asociado a este checkbox
                //para evitar que al reciclar la vista se reinicie el row que antes se mostraba en esta
                //fila. Es imprescindible tagear el Row antes de establecer el valor del checkbox
                if (row.getObjeto().equals(view.getTag().toString())) {
                    row.setChecked(isChecked);
                    changeBackground(CustomArrayAdapter.this.getContext(), fila, isChecked);
                }
            }
        });
        return convertView;
    }

    /**
     * Set the background of a row based on the value of its checkbox value. Checkbox has its own style.
     */
    @SuppressWarnings("deprecation")
    private void changeBackground(Context context, View row, boolean checked) {
        if (checked) {
            row.setBackgroundDrawable((context.getResources().getDrawable(R.drawable.listview_selector_checked)));
        } else {
            row.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.listview_selector));
        }
    }

}

class Holder {
    TextView textViewTitle;
    CheckBox checkBox;

    public TextView getTextViewTitle() {
        return textViewTitle;
    }

    public void setTextViewTitle(TextView textViewTitle) {
        this.textViewTitle = textViewTitle;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }
}