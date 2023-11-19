package com.example.lab8.Beans;

public class Recursos {
    public int getRecursosId() {
        return recursosId;
    }

    public void setRecursosId(int recursosId) {
        this.recursosId = recursosId;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getProduccionT() {
        return produccionT;
    }

    public void setProduccionT(String produccionT) {
        this.produccionT = produccionT;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    private int recursosId;
    private String hora;
    private String dia;
    private String produccionT;
    private String poblacion;
    private Usuario usuario;
}

