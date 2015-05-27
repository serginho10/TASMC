package com.example.vivanco.tasmc;

/**
 * Created by VIVANCO on 11/05/2015.
 */
public class LocalVuelo {
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
    private String fechav;
    private int aerolineav;
    private String origenv;
    private String horaSalidav;
    private String destinov;
    private String horaLlegadav;
    private String tiempov;
    private String escalasv;

    public LocalVuelo(int id, String precio, String fechai, int aerolineai, String origeni, String horaSalidai, String destinoi, String horaLlegadai, String tiempoi, String escalasi, String fechav, int aerolineav, String origenv, String horaSalidav, String destinov, String horaLlegadav, String tiempov, String escalasv) {
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
        this.fechav = fechav;
        this.aerolineav = aerolineav;
        this.origenv = origenv;
        this.horaSalidav = horaSalidav;
        this.destinov = destinov;
        this.horaLlegadav = horaLlegadav;
        this.tiempov = tiempov;
        this.escalasv = escalasv;
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

    public String getFechav() {
        return fechav;
    }

    public void setFechav(String fechav) {
        this.fechav = fechav;
    }

    public int getAerolineav() {
        return aerolineav;
    }

    public void setAerolineav(int aerolineav) {
        this.aerolineav = aerolineav;
    }

    public String getOrigenv() {
        return origenv;
    }

    public void setOrigenv(String origenv) {
        this.origenv = origenv;
    }

    public String getHoraSalidav() {
        return horaSalidav;
    }

    public void setHoraSalidav(String horaSalidav) {
        this.horaSalidav = horaSalidav;
    }

    public String getDestinov() {
        return destinov;
    }

    public void setDestinov(String destinov) {
        this.destinov = destinov;
    }

    public String getHoraLlegadav() {
        return horaLlegadav;
    }

    public void setHoraLlegadav(String horaLlegadav) {
        this.horaLlegadav = horaLlegadav;
    }

    public String getTiempov() {
        return tiempov;
    }

    public void setTiempov(String tiempov) {
        this.tiempov = tiempov;
    }

    public String getEscalasv() {
        return escalasv;
    }

    public void setEscalasv(String escalasv) {
        this.escalasv = escalasv;
    }

    @Override
    public String toString() {
        return "LocalVuelo{" +
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
                ", fechav='" + fechav + '\'' +
                ", aerolineav=" + aerolineav +
                ", origenv='" + origenv + '\'' +
                ", horaSalidav='" + horaSalidav + '\'' +
                ", destinov='" + destinov + '\'' +
                ", horaLlegadav='" + horaLlegadav + '\'' +
                ", tiempov='" + tiempov + '\'' +
                ", escalasv='" + escalasv + '\'' +
                '}';
    }
}