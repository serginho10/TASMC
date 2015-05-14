package com.example.vivanco.tasmc;

/**
 * Created by ISC_SERGIO on 13/05/15.
 */
public class EquipajeHasObjeto {
    private int Equipaje_idEquipaje;
    private int Objeto_idObjeto;

    @Override
    public String toString() {
        return "EquipajeHasObjeto{" +
                "Equipaje_idEquipaje=" + Equipaje_idEquipaje +
                ", Objeto_idObjeto=" + Objeto_idObjeto +
                '}';
    }

    public int getEquipaje_idEquipaje() {
        return Equipaje_idEquipaje;
    }

    public void setEquipaje_idEquipaje(int equipaje_idEquipaje) {
        Equipaje_idEquipaje = equipaje_idEquipaje;
    }

    public int getObjeto_idObjeto() {
        return Objeto_idObjeto;
    }

    public void setObjeto_idObjeto(int objeto_idObjeto) {
        Objeto_idObjeto = objeto_idObjeto;
    }

    public EquipajeHasObjeto(int equipaje_idEquipaje, int objeto_idObjeto) {

        Equipaje_idEquipaje = equipaje_idEquipaje;
        Objeto_idObjeto = objeto_idObjeto;
    }
}
