package com.example.vivanco.tasmc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ISC_SERGIO on 15/05/15.
 */
public class Grupo {
    public String string;
    public final List<String> children = new ArrayList<String>();

    public Grupo(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
}
