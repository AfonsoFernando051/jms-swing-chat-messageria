package view;

import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import model.ChatClientJMS;

public class ChatClientUI extends JFrame {
    private static final long serialVersionUID = 1L;

    private final Map<String, StringBuilder> conversations = new HashMap<>();
    private String currentContact = null;
    private String clientId;

    private ContactListPanel contactListPanel;
    private ChatPanel chatPanel;
    private ChatClientJMS jms;

    public ChatClientUI(String clientId) {
        this.clientId = clientId;

        setUndecorated(true);
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(new TitleBarPanel(this), BorderLayout.NORTH);

        contactListPanel = new ContactListPanel(this);
        add(contactListPanel, BorderLayout.WEST);

        chatPanel = new ChatPanel(this);
        add(chatPanel, BorderLayout.CENTER);

        try {
            this.jms = new ChatClientJMS(clientId, this);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao conectar ao servidor JMS", "Erro", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    public void addContact(String name) {
        if (!conversations.containsKey(name)) {
            contactListPanel.addContact(name);
            conversations.put(name, new StringBuilder());
        }
    }

    public void onContactSelected(String contact) {
        currentContact = contact;
        updateMessageArea();
    }

    public void sendMessage(String message) {
        if (currentContact == null || message.isBlank()) return;

        conversations.get(currentContact)
                     .append("Você: ")
                     .append(message)
                     .append("\n");

        updateMessageArea();

        try {
            jms.sendMessage(currentContact, message);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao enviar mensagem", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void receiveMessage(String from, String message) {
        addContact(from);
        conversations.get(from)
                     .append(from)
                     .append(": ")
                     .append(message)
                     .append("\n");

        if (from.equals(currentContact)) {
            updateMessageArea();
        }
    }

    private void updateMessageArea() {
        if (currentContact != null) {
            chatPanel.updateMessageArea(conversations.get(currentContact).toString());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            String id = JOptionPane.showInputDialog("Digite seu código de usuário:");
            if (id != null && !id.isBlank()) {
                new ChatClientUI(id).setVisible(true);
            }
        });
    }

    public String getCurrentContact() {
        return currentContact;
    }
} 
