package lapr4.blue.s3.core.n1151088.searchReplace.presentation;

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
import lapr4.blue.s3.core.n1151088.searchReplace.SearchReplacePublisher;
import lapr4.blue.s3.core.n1151088.searchReplace.application.SearchReplaceController;
import lapr4.blue.s3.core.n1151088.searchReplace.domain.PreviewCellDTO;
import lapr4.blue.s3.core.n1151088.searchReplace.domain.PreviewerList;
import lapr4.green.s2.core.n1150838.GlobalSearch.GlobalSearchExtension;
import lapr4.green.s2.core.n1150838.GlobalSearch.presentation.ConfigPane;
import lapr4.green.s2.core.n1150838.GlobalSearch.presentation.GlobalSearchSideBar;

/**
 *
 * @author Diana Silva [1151088@isep.ipp.pt]
 */
public class ReplacePanel extends GlobalSearchSideBar implements Observer{
     private JTextField replaceField;
    private UIController extension;
    private SearchReplaceController ctrl;
    private JList previewList;
    private ConfigPane paneFilters;
    private PreviewerList model;
   
    public ReplacePanel(UIController uictrl){
        super(uictrl);
        SearchReplacePublisher.getInstance().addObserver(this);
        setName(GlobalSearchExtension.NAME);
        this.extension = extension;
        ctrl = new SearchReplaceController(extension);
        paneFilters = new ConfigPane(ctrl);
        // Creates search components
        initComponents();
        setVisible(true); 
    }
    
     /**
     * the method that will build all the window elements
     */
    private void initComponents() {
        setLayout(new BorderLayout());
        add(searchPanel());
    }
    
    
    private JPanel searchPanel(){
        JPanel searchPanel=new JPanel();
        searchPanel.setLayout(new BorderLayout());
        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new GridLayout(2, 1));
        JPanel textPanel = new JPanel();
        BoxLayout box = new BoxLayout(textPanel, BoxLayout.X_AXIS);
        textPanel.setLayout(box);
        textPanel.add(new JLabel("Replace text: "));
        textPanel.add(replaceField());
        upperPanel.add(textPanel);

        upperPanel.add(createButtons());
        searchPanel.add(upperPanel, BorderLayout.NORTH);

        //creates the panel with the search results list
        searchPanel.add(previewList(), BorderLayout.CENTER);
        
        return searchPanel;
    }
    
    /**
     * Creates and returns the panel to show search results
     *
     * @return JScrollPanel
     */
    private JScrollPane previewList() {

        model = new PreviewerList(new ArrayList(), replaceField.getName());
        previewList = new JList(model);
        previewList.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        previewList.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Previews: "));
        previewList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList) evt.getSource();
                int index = list.locationToIndex(evt.getPoint());
                if (evt.getClickCount() == 1 && index >= 0) {
                    model.setSelectedItem((String) model.getElementAt(index));
                    //replace
                }

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
                  //  ctrl.startPreviewThread(getCellList(), replaceField.getText());
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
     * The method that will be called when a cell was found in the search and
     * after that the main list will be updated
     *
     * @param o
     * @param arg
     */
     @Override
    public void update(Observable o, Object arg) {
        if (arg!=null && arg instanceof String) {
            if (!(arg.equals("0"))) {// ignore the null CELL
                java.awt.EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        model.addElement((PreviewCellDTO) arg);
                        previewList.updateUI();
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
