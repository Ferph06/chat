/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.api.pojos;

import java.io.Serializable;

/**
 *
 * @author Familia
 */
public class UsuariosPojo implements Serializable {

    private String id;
    private String usuario;

    public UsuariosPojo(String id, String usuario) {
        this.id = id;
        this.usuario = usuario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

}
