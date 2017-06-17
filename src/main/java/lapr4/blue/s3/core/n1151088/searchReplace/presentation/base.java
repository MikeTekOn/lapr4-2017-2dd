package lapr4.blue.s3.core.n1151088.searchReplace.presentation;

import lapr4.green.s2.core.n1150838.GlobalSearch.presentation.*;
import csheets.ui.ctrl.UIController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
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
public class base extends JPanel implements Observer {

    private JTextField searchField, replaceField ,toField;
    private UIController extension;
    private SearchReplaceController ctrl;
    private JList searchList;
    private CellList model;
    private ConfigPane paneFilters;
     private JList previewList;
    private PreviewerList modelPreview;
    

    public base(UIController extension) {
        GlobalSearchPublisher.getInstance().addObserver(this);
        setName(SearchReplaceExtension.NAME);
        this.extension = extension;
        ctrl = new SearchReplaceController(extension);
        paneFilters = new ConfigPane(ctrl);
        // Creates border
        buildBorder(SearchReplaceExtension.NAME, null);
        // Creates search components
        initComponents();
        setVisible(true);
    }
    
    /**
     * Private method to nuild the border for the chat.
     *
     * @param panel The chat panel to add the border.
     */
    private void buildBorder(String content, JPanel p) {
        TitledBorder border = BorderFactory.createTitledBorder(content);
        border.setTitleJustification(TitledBorder.CENTER);
        if(p!=null)
            p.setBorder(border);
        else
            setBorder(border);
    }

    /**
     * the metho that will build all the window elements
     */
    private void initComponents() {
        setLayout(new GridLayout(2,1));
        add(searchPanel());
        add(replacePanel());
       
    }
    
    private JPanel searchPanel(){
         JPanel searchPanel=new JPanel();
         buildBorder("Search", searchPanel);
        searchPanel.setLayout(new GridLayout(2,1));
        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new BorderLayout());
        JPanel textPanel = new JPanel();
        
         BoxLayout box = new BoxLayout(textPanel, BoxLayout.X_AXIS);
        textPanel.setLayout(box); 
        textPanel.add(new JLabel("Enter Regex here: "));
        textPanel.add(createField(searchField));
        upperPanel.add(textPanel, BorderLayout.NORTH);

        upperPanel.add(createButtons(),BorderLayout.SOUTH);
        searchPanel.add(upperPanel, BorderLayout.NORTH);

        //creates the panel with the search results list
        searchPanel.add(searchList(), BorderLayout.CENTER);
        
        return searchPanel;
    }
    
    private JPanel replacePanel(){
         JPanel previewPanel=new JPanel();
         buildBorder("Replace", previewPanel);
        previewPanel.setLayout(new BorderLayout());
  
        previewPanel.add(replaceUpperPanel(), BorderLayout.NORTH);
        //creates the panel with the search results list
     //   previewPanel.add(previewList(), BorderLayout.CENTER);
        previewPanel.add(replaceLowerPanel(), BorderLayout.CENTER);
        previewPanel.add(buttonReview(), BorderLayout.SOUTH);
        
        return previewPanel;
    }
    
    private JPanel replaceUpperPanel(){
        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new BorderLayout());
        JPanel textPanel = new JPanel();
        BoxLayout box = new BoxLayout(textPanel, BoxLayout.X_AXIS);
        textPanel.setLayout(box);
        textPanel.add(new JLabel("Replace: "));
        textPanel.add(createField(replaceField));
        
        JPanel replacePanel=new JPanel();       
        BoxLayout box2 = new BoxLayout(replacePanel, BoxLayout.X_AXIS);
        replacePanel.setLayout(box2);  
        replacePanel.add( new JLabel("To: "));
        replacePanel.add(createField(toField));
        
        upperPanel.add(textPanel, BorderLayout.NORTH);
        upperPanel.add(replacePanel, BorderLayout.CENTER);

        upperPanel.add(nextButton(), BorderLayout.SOUTH);
        return upperPanel;
    }
    
    private JPanel replaceLowerPanel(){
        JPanel lowerPanel = new JPanel();
        buildBorder("Preview", lowerPanel);
        lowerPanel.setLayout(new BorderLayout());
        JPanel textPanel = new JPanel();
        BoxLayout box = new BoxLayout(textPanel, BoxLayout.X_AXIS);
        textPanel.setLayout(box);;
        textPanel.add(createField(replaceField));
        textPanel.add(replaceButton());
        
        JPanel occurrences=new JPanel(); 
        buildBorder("Occurrences", textPanel);
        
        occurrences.add(searchList());
        occurrences.add(nextButton());
        
        lowerPanel.add(textPanel, BorderLayout.WEST);
        lowerPanel.add(occurrences, BorderLayout.CENTER);

        return lowerPanel;
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
        scroll.setPreferredSize(new Dimension(50, 100));
        return scroll;
    }
    
    /**
     * Creates and returns the field where the user can insert regular
     * expression
     *
     * @return a JTextField
     */
    private JTextField createField(JTextField field) {
        field = new JTextField();
       field.setPreferredSize(new Dimension(200,5));
        return field;
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
    public JButton replaceButton() {
         JButton b = new JButton("Replace");
        //b.setPreferredSize(new Dimension(10,10));
        b.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //

            }
        }));
        return b;
    }

      /**
     * Creates a panel with all the necessary buttons
     *
     * @return a JPanel
     */
    public JButton nextButton() {
         JButton b = new JButton("Next");
        //b.setPreferredSize(new Dimension(10,10));
        b.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //

            }
        }));
        return b;
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


