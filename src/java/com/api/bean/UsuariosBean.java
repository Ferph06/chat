/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.api.bean;

import com.api.pojos.UsuariosPojo;
import java.io.IOException;
import java.io.Serializable;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Familia
 */
@ManagedBean
@ViewScoped
public class UsuariosBean implements Serializable {

    private UsuariosPojo us;
    private String username;

    @PostConstruct
    public void init() {
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user") != null) {
            us = (UsuariosPojo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UsuariosPojo getUs() {
        return us;
    }

    public void setUs(UsuariosPojo us) {
        this.us = us;
    }

    public void registrarNuevoUsuario(String name) {
        us = new UsuariosPojo(UUID.randomUUID().toString(), name);
        try {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", us);
            FacesContext.getCurrentInstance().getExternalContext().redirect("./salas/chat.sockets");
        } catch (IOException ex) {
            Logger.getLogger(UsuariosBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
