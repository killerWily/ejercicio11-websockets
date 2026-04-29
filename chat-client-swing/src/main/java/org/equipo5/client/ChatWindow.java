package org.equipo5.client;

import javax.swing.*;
import java.awt.*;
import java.net.URI;

public class ChatWindow extends JFrame {
    private JTextArea chatArea;
    private JTextField inputField;
    private WebSocketClient client;

    public ChatWindow(String username) {
        super("Chat Equipo 5 - Usuario: " + username);
        
        // Configuración de la Ventana
        setSize(450, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Área de chat (Historial)
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setBackground(new Color(245, 245, 245));
        add(new JScrollPane(chatArea), BorderLayout.CENTER);

        // Panel inferior (Entrada de texto)
        JPanel bottomPanel = new JPanel(new BorderLayout());
        inputField = new JTextField();
        JButton sendButton = new JButton("Enviar");

        bottomPanel.add(inputField, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);

        // Acción de enviar
        inputField.addActionListener(e -> actionSend());
        sendButton.addActionListener(e -> actionSend());

        setVisible(true);

        // Iniciar conexión WebSocket
        try {
            String dest = "ws://127.0.0.1:8080/chat/" + username;
            //String dest = "ws://localhost:8080/chat/" + username;
            client = new WebSocketClient(new URI(dest), this);
        } catch (Exception e) {
            displayMessage("Error de URI: " + e.getMessage());
        }
    }

    private void actionSend() {
        String text = inputField.getText();
        if (!text.trim().isEmpty()) {
            client.sendMessage(text);
            inputField.setText("");
        }
    }

    public void displayMessage(String message) {
        chatArea.append(message + "\n");
        chatArea.setCaretPosition(chatArea.getDocument().getLength());
    }
}