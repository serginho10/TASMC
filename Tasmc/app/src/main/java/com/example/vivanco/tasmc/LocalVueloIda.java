package com.example.vivanco.tasmc;

/**
 * Created by VIVANCO on 27/05/2015.
 */
public class LocalVueloIda {
    private int id;
    private String precio;
    private String fechai;
    private int aerolineai;
    private String origeni;
    private String horaSalidai;
    private String destinoi;
    private String horaLlegadai;
    private String tiempoi;
    private String escalasi;

    public LocalVueloIda(int id, String precio, String fechai, int aerolineai, String origeni, String horaSalidai, String destinoi, String horaLlegadai, String tiempoi, String escalasi) {
        this.id = id;
        this.precio = precio;
        this.fechai = fechai;
        this.aerolineai = aerolineai;
        this.origeni = origeni;
        this.horaSalidai = horaSalidai;
        this.destinoi = destinoi;
        this.horaLlegadai = horaLlegadai;
        this.tiempoi = tiempoi;
        this.escalasi = escalasi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getFechai() {
        return fechai;
    }

    public void setFechai(String fechai) {
        this.fechai = fechai;
    }

    public int getAerolineai() {
        return aerolineai;
    }

    public void setAerolineai(int aerolineai) {
        this.aerolineai = aerolineai;
    }

    public String getOrigeni() {
        return origeni;
    }

    public void setOrigeni(String origeni) {
        this.origeni = origeni;
    }

    public String getHoraSalidai() {
        return horaSalidai;
    }

    public void setHoraSalidai(String horaSalidai) {
        this.horaSalidai = horaSalidai;
    }

    public String getDestinoi() {
        return destinoi;
    }

    public void setDestinoi(String destinoi) {
        this.destinoi = destinoi;
    }

    public String getHoraLlegadai() {
        return horaLlegadai;
    }

    public void setHoraLlegadai(String horaLlegadai) {
        this.horaLlegadai = horaLlegadai;
    }

    public String getTiempoi() {
        return tiempoi;
    }

    public void setTiempoi(String tiempoi) {
        this.tiempoi = tiempoi;
    }

    public String getEscalasi() {
        return escalasi;
    }

    public void setEscalasi(String escalasi) {
        this.escalasi = escalasi;
    }

    @Override
    public String toString() {
        return "LocalVueloIda{" +
                "id=" + id +
                ", precio='" + precio + '\'' +
                ", fechai='" + fechai + '\'' +
                ", aerolineai=" + aerolineai +
                ", origeni='" + origeni + '\'' +
                ", horaSalidai='" + horaSalidai + '\'' +
                ", destinoi='" + destinoi + '\'' +
                ", horaLlegadai='" + horaLlegadai + '\'' +
                ", tiempoi='" + tiempoi + '\'' +
                ", escalasi='" + escalasi + '\'' +
                '}';
    }
}
