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
import javax.swing.border.TitledBorder;
import lapr4.blue.s3.core.n1151088.searchReplace.SearchReplaceExtension;
import lapr4.blue.s3.core.n1151088.searchReplace.application.SearchReplaceController;
import lapr4.green.s2.core.n1150838.GlobalSearch.domain.Filter;
import lapr4.green.s2.core.n1150838.GlobalSearch.presentation.CellInfoDTO;
import lapr4.green.s2.core.n1150838.GlobalSearch.presentation.CellList;
import lapr4.green.s2.core.n1150838.GlobalSearch.presentation.ConfigPane;
import lapr4.green.s2.core.n1150838.GlobalSearch.util.GlobalSearchPublisher;

/**
 * @author Diana Silva [1151088@isep.ipp.pt] update
 * @author Nuno Pinto 1150838
 */
public class SearchReplaceSideBar extends JPanel implements Observer {

    private JTextField searchField, replaceField ,toField, previewField;
    private UIController extension;
    private SearchReplaceController ctrl;
    private JList searchList, replaceList;
    private CellList model, modelReplace;
    private ConfigPane paneFilters;

    public SearchReplaceSideBar(UIController extension) {
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
       
        JPanel upperPanel=new JPanel();
        
        JPanel fields=new JPanel();
        
        BoxLayout box = new BoxLayout(fields, BoxLayout.X_AXIS);
        fields.setLayout(box);;
        
        searchField=new JTextField();
        searchField.setPreferredSize(new Dimension(100,5));
       
        JLabel jlabel=new JLabel("Search regex:");
        
        fields.add(jlabel);
        fields.add(searchField); 
       
        
        upperPanel.add(fields);
        upperPanel.add(createSearchButtons());
        
        searchPanel.add(upperPanel);    
       searchPanel.add(searchList());
        
        return searchPanel;
    }
    
    private JPanel labelWithField(JTextField field, String label){
        JPanel p=new JPanel();
        
        BoxLayout box = new BoxLayout(p, BoxLayout.X_AXIS);
        p.setLayout(box);;
        
        field=new JTextField();
        field.setPreferredSize(new Dimension(100,5));
       
        JLabel jlabel=new JLabel(label);
        
        p.add(jlabel);
        p.add(field); 
        return p;
    }
    
     /**
     * Creates a panel with all the necessary buttons
     *
     * @return a JPanel
     */
    public JPanel createSearchButtons() {
        JPanel panelButtons = new JPanel();
        panelButtons.add(buttonSearch());
        panelButtons.add(buttonFilter());
        return panelButtons;
    }
   
    private JPanel replacePanel(){
        JPanel replacePanel=new JPanel();
        buildBorder("Replace", replacePanel);
        replacePanel.setLayout(new BorderLayout());
        
        JPanel upper=new JPanel();
        upper.add(replaceFields());
        
        JPanel lower=new JPanel();
        lower.setLayout(new BorderLayout());
        lower.add(ocurrencesPanel(), BorderLayout.CENTER);
        lower.add(previewPanel(), BorderLayout.EAST);
        
        replacePanel.add(upper, BorderLayout.NORTH);
        replacePanel.add(lower, BorderLayout.CENTER);
            
        return replacePanel;
    }
    
    private JPanel previewPanel(){
        JPanel p=new JPanel();
        buildBorder("Preview", p);
                
        BoxLayout box = new BoxLayout(p, BoxLayout.Y_AXIS);
        p.setLayout(box);;
        
        previewField=new JTextField();
        previewField.setPreferredSize(new Dimension(100,5));
        previewField.setEditable(false);
 
        p.add(previewField); 
        p.add(createPreviewButton());
      
                
        return p;
       
    }
     /**
     * Creates and returns the panel to show search results
     *
     * @return JScrollPanel
     */
    private JPanel ocurrencesPanel() {
        JPanel p=new JPanel();
        p.setLayout(new BorderLayout());
        buildBorder("Occurrences", p);
        modelReplace = new CellList(new ArrayList());
        replaceList = new JList(model);
        replaceList.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        replaceList.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Results: "));
        replaceList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList) evt.getSource();
                int index = list.locationToIndex(evt.getPoint());
                if (evt.getClickCount() == 1 && index >= 0) {
                    modelReplace.setSelectedItem((String) modelReplace.getElementAt(index));
                    extension.setActiveCell(model.getSelectedItem().getCell());
                }

            }
        });
        JScrollPane scroll = new JScrollPane(searchList);
        scroll.setPreferredSize(new Dimension(50, 50));
        p.add(scroll, BorderLayout.CENTER);
        p.add(createReplaceButtons(), BorderLayout.SOUTH);
        return p;
    }
    
    private JPanel createReplaceButtons(){
        JPanel p=new JPanel();
        p.setLayout(new GridLayout(1,2));
        p.add(buttonReplace());
        p.add(buttonsReplaceAll());
        return p;
        
    }
    
    private JButton buttonReplace(){
        JButton b = new JButton("Replace");
        //b.setPreferredSize(new Dimension(10,10));
        b.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paneFilters.setVisible(true);

            }
        }));
        return b;
    }
    
      private JButton nextButton(){
        JButton b = new JButton("Next");
        //b.setPreferredSize(new Dimension(10,10));
        b.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paneFilters.setVisible(true);

            }
        }));
        return b;
    }
    
    private JButton buttonsReplaceAll(){
        JButton b = new JButton("Replace All");
        //b.setPreferredSize(new Dimension(10,10));
        b.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paneFilters.setVisible(true);

            }
        }));
        return b;
    }
    
    private JPanel replaceFields(){
        JPanel p=new JPanel();
        JPanel replacePanel=new JPanel();
        replacePanel.setLayout(new GridLayout(2,1));
 
        replacePanel.add(labelWithField(replaceField, "Replace"));
        replacePanel.add(labelWithField(toField, "To"));
        
        JPanel button =new JPanel();
        button.add(createPreviewButton());
        
        p.add(replacePanel,BorderLayout.NORTH);
        p.add(button,BorderLayout.SOUTH);
        
        return p;
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
            @Override
            public void actionPerformed(ActionEvent e) {
                paneFilters.setVisible(true);

            }
        }));
        return b;
    }
    
     /**
     *
     * @return the button to add filters
     */
    private JButton createPreviewButton() {
        JButton b = new JButton("Preview");
        //b.setPreferredSize(new Dimension(10,10));
        b.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paneFilters.setVisible(true);

            }
        }));
        return b;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg!=null && arg instanceof CellInfoDTO) {
            if (!((CellInfoDTO) arg).getCell().toString().equals("0")) {// ignore the null CELL
                java.awt.EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        model.addElement((CellInfoDTO) arg);
                        modelReplace.addElement((CellInfoDTO) arg);
                        searchList.updateUI();
                        replaceList.updateUI();
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