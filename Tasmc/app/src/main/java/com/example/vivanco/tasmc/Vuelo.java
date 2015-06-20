package com.example.vivanco.tasmc;

public class Vuelo {
    private long idVuelo;
    private String numero;
    private String salida;
    private String llegada;
    private String estado;
    private String sala;
    private String terminal;
    private String aerolinea;
    private String origen;
    private String destino;

    public Vuelo(long idVuelo, String numero, String salida, String llegada, String estado, String sala, String terminal, String aerolinea, String origen, String destino) {
        this.idVuelo = idVuelo;
        this.numero = numero;
        this.salida = salida;
        this.llegada = llegada;
        this.estado = estado;
        this.sala = sala;
        this.terminal = terminal;
        this.aerolinea = aerolinea;
        this.origen = origen;
        this.destino = destino;
    }

    public long getIdVuelo() {
        return idVuelo;
    }

    public void setIdVuelo(long idVuelo) {
        this.idVuelo = idVuelo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getSalida() {
        return salida;
    }

    public void setSalida(String salida) {
        this.salida = salida;
    }

    public String getLlegada() {
        return llegada;
    }

    public void setLlegada(String llegada) {
        this.llegada = llegada;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getAerolinea() {
        return aerolinea;
    }

    public void setAerolinea(String aerolinea) {
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

    @Override
    public String toString() {
        return "Vuelo{" +
                "idVuelo=" + idVuelo +
                ", numero='" + numero + '\'' +
                ", salida='" + salida + '\'' +
                ", llegada='" + llegada + '\'' +
                ", estado='" + estado + '\'' +
                ", sala='" + sala + '\'' +
                ", terminal='" + terminal + '\'' +
                ", aerolinea='" + aerolinea + '\'' +
                ", origen='" + origen + '\'' +
                ", destino='" + destino + '\'' +
                '}';
    }
}