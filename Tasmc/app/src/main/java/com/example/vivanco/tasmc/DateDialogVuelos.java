package com.example.vivanco.tasmc;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

/**
 * Created by VIVANCO on 11/05/2015.
 */
public class DateDialogVuelos extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    EditText fechaIda;
    EditText fechaI;
    EditText fechaV;


    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int dia = c.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), this, year, month, dia);

    }



    @Override
    public void onDateSet(DatePicker view, int year, int month, int dia) {
        String fecha = dia + "/" + (month + 1) + "/" + year;
        fechaIda.setText(fecha);
        fechaI.setText(fecha);
        fechaV.setText(fecha);


    }


}
