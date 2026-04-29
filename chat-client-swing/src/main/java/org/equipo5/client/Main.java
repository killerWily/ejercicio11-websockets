package org.equipo5.client;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Ejecutar en el hilo de despacho de eventos de Swing
        SwingUtilities.invokeLater(() -> {
            String name = JOptionPane.showInputDialog(null, 
                "Ingresa tu nombre para el chat:", 
                "Bienvenida Equipo 5", 
                JOptionPane.QUESTION_MESSAGE);

            if (name != null && !name.trim().isEmpty()) {
                new ChatWindow(name.trim());
            } else {
                System.exit(0);
            }
        });
    }
}