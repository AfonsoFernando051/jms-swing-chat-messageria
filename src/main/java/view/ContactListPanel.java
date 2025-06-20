package view;

import javax.swing.*;
import java.awt.*;

public class ContactListPanel extends JPanel {
    private static final long serialVersionUID = 1L;
	private final DefaultListModel<String> contactListModel = new DefaultListModel<>();
    private final JList<String> contactList = new JList<>(contactListModel);

    public ContactListPanel(ChatClientUI parent) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(200, 0));

        contactList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        contactList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selected = contactList.getSelectedValue();
                parent.onContactSelected(selected);
            }
        });

        add(new JScrollPane(contactList), BorderLayout.CENTER);
    }

    public void addContact(String name) {
        contactListModel.addElement(name);
    }
}
