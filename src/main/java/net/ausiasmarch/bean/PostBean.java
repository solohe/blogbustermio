/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.bean;

import java.util.Date;

/**
 *
 * @author raznara
 */
public class PostBean {
    private Integer Id;
    private String Titulo;
    private String Cuerpo;
    private String Etiquetas;
    private Date Fecha;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    public String getCuerpo() {
        return Cuerpo;
    }

    public void setCuerpo(String Cuerpo) {
        this.Cuerpo = Cuerpo;
    }

    public String getEtiquetas() {
        return Etiquetas;
    }

    public void setEtiquetas(String Etiquetas) {
        this.Etiquetas = Etiquetas;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date Fecha) {
        this.Fecha = Fecha;
    }
    
}
