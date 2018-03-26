/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.api.utils;

import com.api.pojos.MensajePojo;
import com.google.gson.Gson;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 *
 * @author Familia
 */
public class MensajeDecoder implements Decoder.Text<MensajePojo> {

    @Override
    public MensajePojo decode(String mensaje) {
        return new Gson().fromJson(mensaje, MensajePojo.class);
    }

    @Override
    public boolean willDecode(String s) {
        boolean exito = false;
        System.out.println("Mensaje OBJ " + s);
        if (s != null) {
            exito = true;
        } else {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        return exito;
    }

    @Override
    public void init(EndpointConfig config) {
        config.getUserProperties().keySet().forEach(l -> {
            System.out.println("LLAVE" + l);
        });
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void destroy() {
        //To change body of generated methods, choose Tools | Templates.
    }

}
