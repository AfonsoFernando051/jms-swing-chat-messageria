package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TitleBarPanel extends JPanel {
    private static final long serialVersionUID = 1L;

	public TitleBarPanel(ChatClientUI parent) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(parent.getWidth(), 30));
        setBackground(Color.WHITE);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(60, 63, 65));
        menuBar.setBorder(null);

        JMenu contatoMenu = new JMenu("Contato");
        contatoMenu.setForeground(Color.WHITE);
        JMenuItem adicionarContato = new JMenuItem("Adicionar Contato");

        adicionarContato.addActionListener(e -> {
            String nome = JOptionPane.showInputDialog(parent, "Nome do novo contato:");
            nome = nome.trim();

            if (nome.isEmpty()) {
                JOptionPane.showMessageDialog(parent, "O nome do contato não pode ser vazio.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (nome.length() > 30) {
                JOptionPane.showMessageDialog(parent, "O nome do contato deve ter no máximo 30 caracteres.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            parent.addContact(nome);
        });

        contatoMenu.add(adicionarContato);
        menuBar.add(contatoMenu);

        JButton closeButton = new JButton("X");
        closeButton.setFocusPainted(false);
        closeButton.setForeground(Color.BLACK);
        closeButton.setBackground(new Color(255, 0, 0));
        closeButton.setBorder(null);
        closeButton.setPreferredSize(new Dimension(45, 30));
        closeButton.addActionListener(e -> System.exit(0));

        final Point[] dragOffset = {null};
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                dragOffset[0] = e.getPoint();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                Point current = e.getLocationOnScreen();
                parent.setLocation(current.x - dragOffset[0].x, current.y - dragOffset[0].y);
            }
        });

        add(menuBar, BorderLayout.WEST);
        add(closeButton, BorderLayout.EAST);
    }
}
