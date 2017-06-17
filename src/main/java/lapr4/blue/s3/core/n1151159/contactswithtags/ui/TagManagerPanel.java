package lapr4.blue.s3.core.n1151159.contactswithtags.ui;

import lapr4.blue.s3.core.n1151159.contactswithtags.domain.Tag;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

/**
 * Represents a panel to manage tags.
 *
 * @author Ivo Ferro [1151159]
 */
public class TagManagerPanel extends JPanel {

    /**
     * The default dimension for buttons.
     */
    private static final Dimension DEFAULT_BUTTONS_SIZE = new Dimension(100, 30);
    /**
     * The set of elements.
     */
    private Set<Tag> elements;

    /**
     * The list of elements.
     */
    private JList<Tag> elementsList;

    /**
     * The remove button.
     */
    private JButton removeButton;

    /**
     * The parent window.
     */
    private Component parentComponent;

    /**
     * Crates an instance of set manager panel.
     *
     * @param parentComponent the parent window or null
     */
    public TagManagerPanel(Component parentComponent) {
        this(new TreeSet<>(), parentComponent);
    }

    /**
     * Creates a new instance of set manager panel.
     *
     * @param elements the elements for the list
     */
    public TagManagerPanel(Set<Tag> elements, Component parentComponent) {
        this.elements = elements;
        this.parentComponent = parentComponent;

        setLayout(new BorderLayout(10, 10));
        add(createListPanel(), BorderLayout.NORTH);
        add(createButtonsPanel(), BorderLayout.CENTER);
    }

    /**
     * Creates the list panel.
     *
     * @return list panel
     */
    private JPanel createListPanel() {
        JPanel listPanel = new JPanel();

        elementsList = new JList<>(new TagListModel(new ArrayList<>(elements)));
        TagListCellRenderer listCellRenderer = new TagListCellRenderer();
        listCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        elementsList.setCellRenderer(listCellRenderer);

        elementsList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                removeButton.setEnabled(!elementsList.isSelectionEmpty());
            }
        });

        JScrollPane scrollPane = new JScrollPane(elementsList);
        listPanel.add(scrollPane);

        return listPanel;
    }

    /**
     * Creates the buttons panel.
     *
     * @return buttons panel
     */
    private JPanel createButtonsPanel() {
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        buttonsPanel.add(createAddButton());
        buttonsPanel.add(createRemoveButton());

        return buttonsPanel;
    }

    /**
     * Creates the add button.
     *
     * @return add button
     */
    private JButton createAddButton() {
        JButton addButton = new JButton("Add");

        addButton.setPreferredSize(DEFAULT_BUTTONS_SIZE);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String tagString;
                Tag tag = null;
                boolean canExit = false;

                do {
                    tagString = (String) JOptionPane.showInputDialog(
                            parentComponent,
                            "Please enter the name of the tag:",
                            "Add Tag",
                            JOptionPane.QUESTION_MESSAGE);

                    if (tagString == null) {
                        canExit = true;
                    } else {
                        try {
                            tag = new Tag(tagString);
                            if (elements.contains(tag)) {
                                JOptionPane.showMessageDialog(parentComponent,
                                        "The given tag already exists!",
                                        "Duplicated tag",
                                        JOptionPane.ERROR_MESSAGE);
                            } else {
                                elements.add(tag);
                                JOptionPane.showMessageDialog(parentComponent,
                                        "The tag was successfully added.");
                                refreshList();
                                canExit = true;
                            }
                        } catch (IllegalStateException ex) {
                            JOptionPane.showMessageDialog(parentComponent,
                                    "The value of the tag must contain only alphanumeric characters!\n(a..z, A..Z, 0..9)",
                                    "Invalid Tag",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } while (!canExit);
            }
        });

        return addButton;
    }

    /**
     * Creates the remove button.
     *
     * @return remove button
     */
    private JButton createRemoveButton() {
        removeButton = new JButton("Remove");

        removeButton.setPreferredSize(DEFAULT_BUTTONS_SIZE);
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (elementsList.isSelectionEmpty()) {
                    JOptionPane.showMessageDialog(parentComponent, "Please select a tag first.");
                } else {
                    int option = JOptionPane.showConfirmDialog(
                            parentComponent,
                            "Are you sure you want to remove the selected element?",
                            "Remove Confirmation",
                            JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        elements.remove(elementsList.getSelectedValue());
                        refreshList();
                    }
                }
            }
        });
        removeButton.setEnabled(!elementsList.isSelectionEmpty());

        return removeButton;
    }

    /**
     * Refresh the list.
     */
    public void refreshList() {
        elementsList.setModel(new TagListModel(new ArrayList<>(elements)));
    }

    /**
     * Gets the tags.
     *
     * @return tags
     */
    public Set<Tag> getTags() {
        return elements;
    }

    /**
     * Sets the tags.
     *
     * @param tags
     */
    public void setTags(Set<Tag> tags) {
        elements = new TreeSet<>(tags);
        refreshList();
    }
}