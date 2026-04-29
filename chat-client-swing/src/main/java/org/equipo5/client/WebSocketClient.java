package org.equipo5.client;

import jakarta.websocket.*;
import java.net.URI;

@ClientEndpoint
public class WebSocketClient {

    private Session session;
    private ChatWindow window;

    public WebSocketClient(URI endpointURI, ChatWindow window) {
        this.window = window;
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(this, endpointURI);
        } catch (Exception e) {
            window.displayMessage("Error al conectar: " + e.getMessage());
        }
    }

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        window.displayMessage("Sistema: Conexión establecida con éxito.");
    }

    @OnMessage
    public void onMessage(String message) {
        window.displayMessage(message);
    }

    @OnClose
    public void onClose(Session session, CloseReason reason) {
        window.displayMessage("Sistema: Conexión cerrada. " + reason.getReasonPhrase());
        this.session = null;
    }

    public void sendMessage(String message) {
        if (this.session != null && this.session.isOpen()) {
            this.session.getAsyncRemote().sendText(message);
        }
    }
}