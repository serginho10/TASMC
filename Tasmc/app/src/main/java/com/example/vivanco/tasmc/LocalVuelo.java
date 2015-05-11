package com.example.vivanco.tasmc;

/**
 * Created by VIVANCO on 11/05/2015.
 */
public class LocalVuelo {
    private int id;
    private String precio;
    private String fecha;
    private int aerolinea;
    private String origen;
    private String destino;
    private String horaSalida;
    private String horaLlegada;
    private String tiempo;
    private String escalas;

    public LocalVuelo(int id, String precio, String fecha, int aerolinea, String origen, String destino, String horaSalida, String horaLlegada, String tiempo, String escalas) {
        this.id = id;
        this.precio = precio;
        this.fecha = fecha;
        this.aerolinea = aerolinea;
        this.origen = origen;
        this.destino = destino;
        this.horaSalida = horaSalida;
        this.horaLlegada = horaLlegada;
        this.tiempo = tiempo;
        this.escalas = escalas;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getAerolinea() {
        return aerolinea;
    }

    public void setAerolinea(int aerolinea) {
        this.aerolinea = aerolinea;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(String horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getEscalas() {
        return escalas;
    }

    public void setEscalas(String escalas) {
        this.escalas = escalas;
    }

    @Override
    public String toString() {
        return "LocalVuelo{" +
                "id=" + id +
                ", precio='" + precio + '\'' +
                ", fecha='" + fecha + '\'' +
                ", aerolinea=" + aerolinea +
                ", origen='" + origen + '\'' +
                ", destino='" + destino + '\'' +
                ", horaSalida='" + horaSalida + '\'' +
                ", horaLlegada='" + horaLlegada + '\'' +
                ", tiempo='" + tiempo + '\'' +
                ", escalas='" + escalas + '\'' +
                '}';
    }
}

