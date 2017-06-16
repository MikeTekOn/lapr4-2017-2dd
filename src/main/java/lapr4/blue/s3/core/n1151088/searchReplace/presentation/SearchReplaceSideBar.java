package lapr4.blue.s3.core.n1151088.searchReplace.presentation;

import lapr4.green.s2.core.n1150838.GlobalSearch.presentation.*;
import csheets.ui.ctrl.UIController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.Semaphore;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import lapr4.blue.s3.core.n1151088.searchReplace.SearchReplaceExtension;
import lapr4.blue.s3.core.n1151088.searchReplace.application.SearchReplaceController;
import lapr4.blue.s3.core.n1151088.searchReplace.domain.PreviewerList;
import lapr4.green.s2.core.n1150838.GlobalSearch.domain.Filter;
import lapr4.green.s2.core.n1150838.GlobalSearch.util.GlobalSearchPublisher;

/**
 * @author Diana Silva [1151088@isep.ipp.pt] update
 * @author Nuno Pinto 1150838
 */
public class SearchReplaceSideBar extends JPanel implements Observer {

    private JTextField searchField;
    private JTextField replaceField;
    private UIController extension;
    private SearchReplaceController ctrl;
    private JList searchList;
    private CellList model;
    private ConfigPane paneFilters;
     private JList previewList;
    private PreviewerList modelPreview;
    

    public SearchReplaceSideBar(UIController extension) {
        GlobalSearchPublisher.getInstance().addObserver(this);
        setName(SearchReplaceExtension.NAME);
        this.extension = extension;
        ctrl = new SearchReplaceController(extension);
        paneFilters = new ConfigPane(ctrl);
        // Creates border
        buildBorder();
        // Creates search components
        initComponents();
        setVisible(true);
    }
    
    /**
     * Private method to nuild the border for the chat.
     *
     * @param panel The chat panel to add the border.
     */
    private void buildBorder() {
        TitledBorder border = BorderFactory.createTitledBorder(SearchReplaceExtension.NAME);
        border.setTitleJustification(TitledBorder.CENTER);
        setBorder(border);
    }

    /**
     * the metho that will build all the window elements
     */
    private void initComponents() {
        setLayout(new GridLayout(1,2));
        add(searchPanel());
        add(replacePanel());
       
    }
    
    private JPanel searchPanel(){
         JPanel searchPanel=new JPanel();
         searchPanel.setLayout(new BorderLayout());
        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new GridLayout(2, 1));
        JPanel textPanel = new JPanel();
        BoxLayout box = new BoxLayout(textPanel, BoxLayout.X_AXIS);
        textPanel.setLayout(box);
        textPanel.add(new JLabel("Enter Regex here: "));
        textPanel.add(searchField());
        upperPanel.add(textPanel);

        upperPanel.add(createButtons());
        searchPanel.add(upperPanel, BorderLayout.NORTH);

        //creates the panel with the search results list
        searchPanel.add(searchList(), BorderLayout.CENTER);
        
        return searchPanel;
    }
    
    private JPanel replacePanel(){
         JPanel previewPanel=new JPanel();
        previewPanel.setLayout(new BorderLayout());
        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new GridLayout(2, 1));
        JPanel textPanel = new JPanel();
        BoxLayout box = new BoxLayout(textPanel, BoxLayout.X_AXIS);
        textPanel.setLayout(box);
        textPanel.add(new JLabel("Replace text: "));
        textPanel.add(replaceField());
        upperPanel.add(textPanel);

        upperPanel.add(createButtonsPreview());
        previewPanel.add(upperPanel, BorderLayout.NORTH);

        //creates the panel with the search results list
        previewPanel.add(previewList(), BorderLayout.CENTER);
        
        return previewPanel;
    }

    /**
     * Creates and returns the panel to show search results
     *
     * @return JScrollPanel
     */
    private JScrollPane searchList() {

        model = new CellList(new ArrayList());
        searchList = new JList(model);
        searchList.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        searchList.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Results: "));
        searchList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList) evt.getSource();
                int index = list.locationToIndex(evt.getPoint());
                if (evt.getClickCount() == 1 && index >= 0) {
                    model.setSelectedItem((String) model.getElementAt(index));
                    extension.setActiveCell(model.getSelectedItem().getCell());
                }

            }
        });
        JScrollPane scroll = new JScrollPane(searchList);
        scroll.setPreferredSize(new Dimension(100, 200));
        return scroll;
    }
    
     /**
     * Creates and returns the panel to show search results
     *
     * @return JScrollPanel
     */
    private JScrollPane previewList() {

        modelPreview = new PreviewerList(new ArrayList(), replaceField.getName());
        previewList = new JList(modelPreview);
        previewList.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        previewList.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Previews: "));
        previewList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList) evt.getSource();
//                int index = list.locationToIndex(evt.getPoint());
//                if (evt.getClickCount() == 1 && index >= 0) {
//                    model.setSelectedItem((String) model.getElementAt(index));
//                    //replace
//                }

            }
        });
        JScrollPane scroll = new JScrollPane(previewList);
        scroll.setPreferredSize(new Dimension(100, 200));
        return scroll;
    }

    /**
     * Creates and returns the field where the user can insert regular
     * expression
     *
     * @return a JTextField
     */
    private JTextField searchField() {
        searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(200,5));
        return searchField;
    }
    
     /**
     * Creates and returns the field where the user can insert text to replace
     * expression
     *
     * @return a JTextField
     */
    private JTextField replaceField() {
        replaceField = new JTextField();
        replaceField.setPreferredSize(new Dimension(200,5));
        return replaceField;
    }

    /**
     * Creates a panel with all the necessary buttons
     *
     * @return a JPanel
     */
    public JPanel createButtons() {
        JPanel panelButtons = new JPanel();
        panelButtons.add(buttonSearch());
        panelButtons.add(buttonFilter());
        return panelButtons;
    }

    
    /**
     * Creates a panel with all the necessary buttons
     *
     * @return a JPanel
     */
    public JPanel createButtonsPreview() {
        JPanel panelButtons = new JPanel();
        panelButtons.add(buttonReview());
        return panelButtons;
    }
    
    /**
     * Creates and returns a button to search.
     *
     * @return a JPanel
     */
    private JButton buttonReview() {
        JButton b = new JButton("Review Replace");
        //b.setPreferredSize(new Dimension(10,10));
        b.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ctrl.stopReplaceThread();
                model.removeAll();
                if (replaceField.getText().length()>0) {
                    List<String> typesToInclude = paneFilters.typesToInclude();
                    List<String> formulasToInclude = paneFilters.formulasToInclude();
                    boolean includeComments = paneFilters.includeComments();
                   // ctrl.startPreviewThread(getCellList(), replaceField.getText());
                } else {
                    JOptionPane.showMessageDialog(null,
                            "The inserted regular expression is not valid.",
                            "Invalid regex",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }));

        return b;
    }
    
    /**
     * Creates and returns a button to search.
     *
     * @return a JPanel
     */
    private JButton buttonSearch() {
        JButton b = new JButton("Search");
        //b.setPreferredSize(new Dimension(10,10));
        b.addActionListener((new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                ctrl.stop();
                model.removeAll();
                if (ctrl.checkIfValid(searchField.getText()) && searchField.getText().length()>0) {
                    List<String> typesToInclude = paneFilters.typesToInclude();
                    List<String> formulasToInclude = paneFilters.formulasToInclude();
                    boolean includeComments = paneFilters.includeComments();
                    ctrl.start(new Filter(typesToInclude, formulasToInclude, includeComments), searchField.getText());
                } else {
                    JOptionPane.showMessageDialog(null,
                            "The inserted regular expression is not valid.",
                            "Invalid regex",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }));

        return b;
    }

    /**
     *
     * @return the button to add filters
     */
    private JButton buttonFilter() {
        JButton b = new JButton("Filters");
        //b.setPreferredSize(new Dimension(10,10));
        b.addActionListener((new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                paneFilters.setVisible(true);

            }
        }));
        return b;
    }

    /**
     * The method that will be called when a cell was found in the search and
     * after that the main list will be updated
     *
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        if (arg!=null && arg instanceof CellInfoDTO) {
            if (!((CellInfoDTO) arg).getCell().toString().equals("0")) {// ignore the null CELL
                java.awt.EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        model.addElement((CellInfoDTO) arg);
                        searchList.updateUI();
                    }
                });
            }

        }else{
            JOptionPane.showMessageDialog(null,
                            "No results!",
                            "No results",
                            JOptionPane.ERROR_MESSAGE);
                }
        }

    }


