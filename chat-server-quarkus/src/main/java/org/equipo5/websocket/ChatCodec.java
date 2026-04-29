package org.equipo5.websocket;

import jakarta.websocket.DecodeException;
import jakarta.websocket.Decoder;
import jakarta.websocket.EndpointConfig;

public class ChatCodec implements Decoder.Text<String> {
    @Override
    public String decode(String s) throws DecodeException {
        return s; // Aquí podrías usar Jackson para convertir JSON a un Objeto Java
    }

    @Override
    public boolean willDecode(String s) {
        return (s != null);
    }

    @Override
    public void init(EndpointConfig config) {}

    @Override
    public void destroy() {}
}