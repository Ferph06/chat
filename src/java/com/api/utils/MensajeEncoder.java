/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.api.utils;

import com.api.pojos.MensajePojo;
import com.google.gson.Gson;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 *
 * @author Familia
 */
public class MensajeEncoder implements Encoder.Text<MensajePojo>{
    @Override 
    public String encode(MensajePojo pojo){
        return new Gson().toJson(pojo);
    }
    
    @Override
    public void destroy(){
        
    }

    @Override
    public void init(EndpointConfig config) {
        
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
