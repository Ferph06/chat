/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.api.ws;

import com.api.pojos.MensajePojo;
import com.api.pojos.UsuariosPojo;
import com.api.utils.MensajeDecoder;
import com.api.utils.MensajeEncoder;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author Fernando
 */
@ServerEndpoint(value = "/chat/{username}",
        decoders = MensajeDecoder.class,
        encoders = MensajeEncoder.class)
public class MensajeSocket implements Serializable {

    private Session session;
    private static final Set<MensajeSocket> entradas = new CopyOnWriteArraySet<>();
    private static List<UsuariosPojo> listaUsuario = new ArrayList<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("username") String user) {
        this.session = session;
        entradas.add(this);
        UsuariosPojo u = new UsuariosPojo(this.session.getId(), user);
        listaUsuario.add(u);
        System.out.println("NUEVO USUARIO");
        MensajePojo mensaje = new MensajePojo();
        mensaje.setUserId(u.getUsuario());
        mensaje.setMensaje("Conectado");
        todos(mensaje);

    }

    @OnMessage
    public void onMessage(Session session, MensajePojo mensaje, @PathParam("username") String user) throws IOException, EncodeException {
        String id = listaUsuario.stream().filter(l -> (l.getId() == null ? session.getId() == null : l.getId().equals(session.getId()))).findFirst().get().getId();
        if (id != null) {
            mensaje.setUserId(user);
            todos(mensaje);
        }

    }

    @OnClose
    public void onClose(Session session, @PathParam("username") String user) throws IOException, EncodeException {
        entradas.remove(this);
        MensajePojo mensaje = new MensajePojo();
        String id = listaUsuario.stream().filter(l -> (l.getId() == null ? session.getId() == null : l.getId().equals(session.getId()))).findFirst().get().getId();
        if (id != null) {
            mensaje.setUserId(user);
            mensaje.setMensaje("Desconectado");
            todos(mensaje);
        }
    }

    private static void todos(MensajePojo mensaje) {
        entradas.forEach(endpoint -> {
            synchronized (endpoint) {
                try {
                    endpoint.session.getBasicRemote()
                            .sendObject(mensaje);
                } catch (IOException | EncodeException ex) {
                    Logger.getLogger(MensajeSocket.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
