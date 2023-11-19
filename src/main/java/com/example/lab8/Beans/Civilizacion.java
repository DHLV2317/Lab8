package com.example.lab8.Beans;

public class Civilizacion {
    private int civilizacionId;
    private String estado;
    private String guerraGanada;
    private String guerraPerdida;
    private EstadoCivilizacion estadoCivilizacion;

    public int getCivilizacionId() {
        return civilizacionId;
    }

    public void setCivilizacionId(int civilizacionId) {
        this.civilizacionId = civilizacionId;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getGuerraGanada() {
        return guerraGanada;
    }

    public void setGuerraGanada(String guerraGanada) {
        this.guerraGanada = guerraGanada;
    }

    public String getGuerraPerdida() {
        return guerraPerdida;
    }

    public void setGuerraPerdida(String guerraPerdida) {
        this.guerraPerdida = guerraPerdida;
    }

    public EstadoCivilizacion getEstadoCivilizacion() {
        return estadoCivilizacion;
    }

    public void setEstadoCivilizacion(EstadoCivilizacion estadoCivilizacion) {
        this.estadoCivilizacion = estadoCivilizacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    private Usuario usuario;
}
