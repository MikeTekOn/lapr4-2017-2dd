package lapr4.green.s2.core.n1150901.richCommentsAndHistory.presentation;

import csheets.core.Cell;
import csheets.ext.style.StyleExtension;
import csheets.ext.style.ui.FontChooser;
import csheets.ui.ctrl.SelectionEvent;
import csheets.ui.ctrl.UIController;
import lapr4.blue.s1.lang.n1151031.formulastools.UserStyle;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.List;
import java.util.Map;
import lapr4.green.s2.core.n1150901.richCommentsAndHistory.domain.CommentsWithHistoryListener;
import lapr4.red.s1.core.n1150690.comments.CommentableCellWithMultipleUsers;
import lapr4.red.s1.core.n1150690.comments.domain.User;
import lapr4.red.s1.core.n1150690.comments.presentation.CommentsWithUserUI;
import lapr4.white.s1.core.n1234567.comments.CommentableCell;
import lapr4.white.s1.core.n1234567.comments.CommentsExtension;

/**
 * @author Miguel Silva - 1150901
 */
public class CommentsWithHistoryUI extends CommentsWithUserUI implements CommentsWithHistoryListener {

    /**
     * The user's selected styling options.
     */
    private UserStyle userStyle;

    /**
     * The panel for the format, style and reverse buttons.
     */
    private final JPanel formatAndStyle = new JPanel();

    /**
     * The panel for the history of a comment.
     */
    private final JPanel history = new JPanel();

    /**
     * The panel for the search function.
     */
    private final JPanel search = new JPanel();

    /**
     * The list that will contain the history.
     */
    private JList panelHistory;

    /**
     * The list model to save the history.
     */
    private DefaultListModel historyModel;

    /**
     * The list that will contain the comments found by the search.
     */
    private JList searchList;

    /**
     * The list model to save the search.
     */
    private DefaultListModel searchModel;

    /**
     * Has the index from where the active comments end in the search list.
     */
    private int searchModelSize = 0;

    /**
     * The textfield for the user to search comments.
     */
    private JTextField searchText;

    /**
     * Creates a new condition style panel.
     *
     * @param uiController the user interface controller
     */
    public CommentsWithHistoryUI(UIController uiController) {

        super(uiController);

        userStyle = new UserStyle();

        initComponents();
    }

    /**
     * Initiates the components.
     */
    private void initComponents() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        formatAndStyle.setLayout(new FlowLayout());
        formatAndStyle.add(stylePanel());

        TitledBorder border = BorderFactory.createTitledBorder("History");
        border.setTitleJustification(TitledBorder.CENTER);
        history.setBorder(border);
        history.add(historyPanel());

        TitledBorder border2 = BorderFactory.createTitledBorder("Search Comment");
        border2.setTitleJustification(TitledBorder.CENTER);
        search.setBorder(border2);
        search.add(searchPanel());
        search.add(searchList());

        add(formatAndStyle);
        add(history);
        add(search);
    }

    /**
     * Creates and returns the panel containing the history list.
     *
     * @return The panel containing the history list.
     */
    private JScrollPane historyPanel() {
        historyModel = new DefaultListModel();
        panelHistory = new JList();
        panelHistory.setModel(historyModel);
        panelHistory.setBackground(this.getBackground());
        panelHistory.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        panelHistory.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder()));
        panelHistory.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    String comment = (String) panelHistory.getSelectedValue();
                    User author = controller.changeComment(selectedComment, comment, selectedUser);
                    model.removeElement(selectedUser + ": " + selectedComment);
                    model.addElement(author.name() + ": " + comment);
                }
            }
        });

        return new JScrollPane(panelHistory);
    }

    private JPanel stylePanel() {
        JPanel subPanel = new JPanel(new FlowLayout());

        JPanel selectFormatAndStylePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel selectFormatAndStyleLabel = new JLabel("Style and Format:");
        selectFormatAndStylePanel.add(selectFormatAndStyleLabel);

        JPanel formatAndStylePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        formatAndStylePanel.add(createFontButton());
        formatAndStylePanel.add(createForegroundButton());
        formatAndStylePanel.add(createResetButton());

        subPanel.add(selectFormatAndStylePanel);
        subPanel.add(formatAndStylePanel);

        subPanel.setMaximumSize(subPanel.getPreferredSize());

        return subPanel;
    }

    /**
     * Creates the Font button for the true condition style
     *
     * @return The font button.
     */
    private JButton createFontButton() {
        JButton button = new JButton(new ImageIcon(StyleExtension.class.getResource("res/img/font.gif")));
        button.setContentAreaFilled(false);
        button.setPreferredSize(new Dimension(25, 25));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                font = FontChooser.showDialog(
                        null,
                        "Choose Font",
                        userStyle.getTrueStyleFont());
                flag = 1;
            }
        });
        return button;
    }

    /**
     * Creates the Foreground button for the true condition style
     *
     * @return The foreground button.
     */
    private JButton createForegroundButton() {
        JButton button = new JButton(new ImageIcon(StyleExtension.class.getResource("res/img/color_fg.gif")));
        button.setContentAreaFilled(false);
        button.setPreferredSize(new Dimension(25, 25));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                color = JColorChooser.showDialog(
                        null,
                        "Choose Foreground Color",
                        userStyle.getTrueStyleForegroundColor());
                flag = 2;
            }
        });
        return button;
    }

    /**
     * Creates the Reset button for the formatation and style.
     *
     * @return The reset button.
     */
    private JButton createResetButton() {
        JButton button = new JButton(new ImageIcon(StyleExtension.class.getResource("res/img/reset.gif")));
        button.setContentAreaFilled(false);
        button.setPreferredSize(new Dimension(25, 25));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userStyle.setTrueStyleForegroundColor(Color.black);
                userStyle.setTrueStyleFont(new Font("Dialog", Font.BOLD, 12));
                color = userStyle.getTrueStyleForegroundColor();
                flag = 2;
                font = userStyle.getTrueStyleFont();
                flag = 1;
                panelComments.repaint();
            }
        });

        return button;
    }

    private JPanel searchPanel() {
        JPanel subPanel = new JPanel(new FlowLayout());

        searchText = new JTextField();
        searchText.setPreferredSize(new Dimension(150, 20));
        JButton searchButton = new JButton("Search");
        searchButton.setPreferredSize(new Dimension(50, 30));
        searchButton.addActionListener(new Search());
        searchButton.setMargin(new Insets(0, 0, 0, 0));

        subPanel.add(searchText);
        subPanel.add(searchButton);

        return subPanel;
    }

    /**
     * Creates and returns the panel containing the search list.
     *
     * @return The panel containing the search list.
     */
    private JScrollPane searchList() {
        searchModel = new DefaultListModel();
        searchList = new JList();
        searchList.setModel(searchModel);
        searchList.setBackground(this.getBackground());
        searchList.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        searchList.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder()));
        searchList.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int index = searchList.locationToIndex(e.getPoint());
                if (index < searchModelSize) {
                    searchList.setToolTipText("Comment");
                } else {
                    searchList.setToolTipText("History");
                }
            }
        });

        return new JScrollPane(searchList);
    }

    /**
     * Updates the comment's history when the comments of the active cell are
     * changed.
     *
     * @param cell The cell whose comment's history changed.
     */
    @Override
    public void historyChanged(CommentableCell cell) {
        updateList();
    }

    /**
     * Updates the comment's history.
     */
    private void updateList() {
        historyModel.removeAllElements();
        for (Map.Entry<User, Map<String, List<String>>> mapHistory : controller.history().entrySet()) {
            for (Map.Entry<String, List<String>> userHistory : mapHistory.getValue().entrySet()) {
                if (userHistory.getKey().equals(selectedComment)) {
                    for (String oldVersion : userHistory.getValue()) {
                        historyModel.addElement(oldVersion);
                    }
                }
            }
        }
    }

    @Override
    public void selectionChanged(SelectionEvent event) {

        super.selectionChanged(event);

        Cell c = event.getCell();
        if (c == null) {
            return;
        }
        if (c.getExtension(CommentsExtension.NAME) == null) {
            return;
        }
        if (c != null) {
            cell.addCommentsWithHistoryListener(this);
        }

        // Stops listening to previous active cell
        if (event.getPreviousCell() != null) {
            ((CommentableCellWithMultipleUsers) event.getPreviousCell().getExtension(CommentsExtension.NAME))
                    .removeCommentsWithHistoryListener(this);
        }

        updateList();
    }

    /**
     * An inner class that implements the action listener for the select button.
     */
    private class Search implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            searchModel.removeAllElements();
            Map<Integer, List<String>> found;

            try {
                found = controller.searchPartOfWord(searchText.getText());
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Information", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            for (int key : found.keySet()) {
                switch (key) {
                    case 0:
                        JOptionPane.showMessageDialog(null, "There weren't found any comments.", "Information", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case 1:
                        for (String comment : found.get(1)) {
                            searchModel.addElement(comment);
                        }
                        searchModelSize = searchModel.getSize();
                        break;
                    default:
                        for (String history : found.get(2)) {
                            searchModel.addElement(history);
                        }
                        break;
                }
            }

            searchText.setText((""));
        }
    }

}
