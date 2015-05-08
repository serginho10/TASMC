package com.example.vivanco.tasmc;

/**
 * Created by VIVANCO on 17/03/2015.
 */
public class Vuelo {
    private int id;
    private String categoria;
    private String aerolinea;
    private String vuelo;
    private String fechaSalida;
    private String fechaLlegada;
    private String origen;
    private String destino;
    private String estado;
    private String horaSalida;
    private String horaLlegada;
    private String terminal;
    private String puerta;
    private String tiempo;
    private String escalas;
    private String precio;

    public Vuelo(int id, String categoria, String aerolinea, String vuelo, String fechaSalida, String fechaLlegada, String origen, String destino, String estado, String horaSalida, String horaLlegada, String terminal, String puerta, String tiempo, String escalas, String precio) {
        this.id = id;
        this.categoria = categoria;
        this.aerolinea = aerolinea;
        this.vuelo = vuelo;
        this.fechaSalida = fechaSalida;
        this.fechaLlegada = fechaLlegada;
        this.origen = origen;
        this.destino = destino;
        this.estado = estado;
        this.horaSalida = horaSalida;
        this.horaLlegada = horaLlegada;
        this.terminal = terminal;
        this.puerta = puerta;
        this.tiempo = tiempo;
        this.escalas = escalas;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getAerolinea() {
        return aerolinea;
    }

    public void setAerolinea(String aerolinea) {
        this.aerolinea = aerolinea;
    }

    public String getVuelo() {
        return vuelo;
    }

    public void setVuelo(String vuelo) {
        this.vuelo = vuelo;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(String fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getPuerta() {
        return puerta;
    }

    public void setPuerta(String puerta) {
        this.puerta = puerta;
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

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Vuelo{" +
                "id=" + id +
                ", categoria='" + categoria + '\'' +
                ", aerolinea='" + aerolinea + '\'' +
                ", vuelo='" + vuelo + '\'' +
                ", fechaSalida='" + fechaSalida + '\'' +
                ", fechaLlegada='" + fechaLlegada + '\'' +
                ", origen='" + origen + '\'' +
                ", destino='" + destino + '\'' +
                ", estado='" + estado + '\'' +
                ", horaSalida='" + horaSalida + '\'' +
                ", horaLlegada='" + horaLlegada + '\'' +
                ", terminal='" + terminal + '\'' +
                ", puerta='" + puerta + '\'' +
                ", tiempo='" + tiempo + '\'' +
                ", escalas='" + escalas + '\'' +
                ", precio='" + precio + '\'' +
                '}';
    }
}