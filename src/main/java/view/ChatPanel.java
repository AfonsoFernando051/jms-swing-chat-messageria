package view;

import javax.swing.*;
import java.awt.*;

public class ChatPanel extends JPanel {
    private static final long serialVersionUID = 1L;
	private final JTextArea messageArea = new JTextArea();
    private final JTextField inputField = new JTextField();
    private final JButton sendButton = new JButton("Enviar");

    public ChatPanel(ChatClientUI parent) {
        setLayout(new BorderLayout());

        messageArea.setEditable(false);
        add(new JScrollPane(messageArea), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        sendButton.addActionListener(e -> {
            String msg = inputField.getText().trim();
            if (msg.isEmpty()) {
                JOptionPane.showMessageDialog(this, "A mensagem não pode estar vazia!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            parent.sendMessage(msg);
            inputField.setText("");
        });

        inputField.addActionListener(e -> {
            String msg = inputField.getText().trim();
            if (msg.isEmpty()) {
                JOptionPane.showMessageDialog(this, "A mensagem não pode estar vazia!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            parent.sendMessage(msg);
            inputField.setText("");
        });

        add(inputPanel, BorderLayout.SOUTH);
    }

    public void updateMessageArea(String text) {
        messageArea.setText(text);
    }
}
