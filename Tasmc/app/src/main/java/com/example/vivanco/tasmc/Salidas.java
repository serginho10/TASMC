package com.example.vivanco.tasmc;

/**
 * Created by VIVANCO on 29/04/2015.
 */
public class Salidas {
    private String destino;
    private String aerolinea;
    private String vuelo;
    private String hora;
    private String estado;
    private String sala;
    private String terminal;

    public Salidas() {

    }

    public Salidas(String destino, String aerolinea, String vuelo, String hora, String estado, String sala, String terminal) {
        this.destino = destino;
        this.aerolinea = aerolinea;
        this.vuelo = vuelo;
        this.hora = hora;
        this.estado = estado;
        this.sala = sala;
        this.terminal = terminal;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
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

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
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

    @Override
    public String toString() {
        return "Salidas{" +
                "destino='" + destino + '\'' +
                ", aerolinea='" + aerolinea + '\'' +
                ", vuelo='" + vuelo + '\'' +
                ", hora='" + hora + '\'' +
                ", estado='" + estado + '\'' +
                ", sala='" + sala + '\'' +
                ", terminal='" + terminal + '\'' +
                '}';
    }
}
