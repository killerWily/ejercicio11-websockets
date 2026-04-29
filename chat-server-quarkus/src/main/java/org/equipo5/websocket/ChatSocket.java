package org.equipo5.websocket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/chat/{username}")
@ApplicationScoped
public class ChatSocket {

    // Almacenamos las sesiones vinculadas al nombre de usuario
    Map<String, Session> sessions = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) {
        sessions.put(username, session);
        broadcast(">> Sistema: " + username + " se ha unido al chat.");
    }

    @OnClose
    public void onClose(Session session, @PathParam("username") String username) {
        sessions.remove(username);
        broadcast(">> Sistema: " + username + " ha salido del chat.");
    }

    @OnError
    public void onError(Session session, @PathParam("username") String username, Throwable throwable) {
        sessions.remove(username);
        System.err.println("Error en sesión de " + username + ": " + throwable.getMessage());
    }

    @OnMessage
    public void onMessage(String message, @PathParam("username") String username) {
        // Enviar el mensaje recibido a todos los demás
        broadcast(username + ": " + message);
    }

    private void broadcast(String message) {
        sessions.values().forEach(s -> {
            s.getAsyncRemote().sendObject(message, result -> {
                if (result.getException() != null) {
                    System.out.println("No se pudo enviar mensaje: " + result.getException());
                }
            });
        });
    }
}