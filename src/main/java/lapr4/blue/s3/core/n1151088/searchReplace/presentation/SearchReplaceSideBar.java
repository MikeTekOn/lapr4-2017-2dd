package lapr4.blue.s3.core.n1151088.searchReplace.presentation;

import csheets.core.IllegalValueTypeException;
import csheets.core.formula.compiler.FormulaCompilationException;
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
import java.util.logging.Level;
import java.util.logging.Logger;
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
import lapr4.blue.s3.core.n1151088.searchReplace.SearchReplacePublisher;
import lapr4.blue.s3.core.n1151088.searchReplace.application.SearchReplaceController;
import lapr4.blue.s3.core.n1151088.searchReplace.domain.PreviewCellDTO;
import lapr4.blue.s3.core.n1151088.searchReplace.domain.PreviewerList;
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

    private JTextField searchField, replaceField ,toField;
    private JLabel previewLabel;
    private UIController extension;
    private SearchReplaceController ctrl;
    private JList searchList, replaceList;
    private CellList model;
    private PreviewerList modelReplace;
    private ConfigPane paneFilters;
 
    public SearchReplaceSideBar(UIController extension) {
        GlobalSearchPublisher.getInstance().addObserver(this);
        SearchReplacePublisher.getInstance().addObserver(this);
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
        lower.add(occurrencesPanel(), BorderLayout.CENTER);
       // lower.add(previewPanel(), BorderLayout.EAST);
        
        replacePanel.add(upper, BorderLayout.NORTH);
        replacePanel.add(lower, BorderLayout.CENTER);
            
        return replacePanel;
    }
    
    private JPanel previewPanel(){
        JPanel p=new JPanel();
        buildBorder("Preview (Value | Content)", p);
                
        BoxLayout box = new BoxLayout(p, BoxLayout.Y_AXIS);
        p.setLayout(box);;
        
        previewLabel=new JLabel();
        previewLabel.setPreferredSize(new Dimension(100,5));
      
        p.add(previewLabel); 
      //  p.add(createPreviewButton());
         
        return p;
       
    }
     /**
     * Creates and returns the panel to show search results
     *
     * @return JScrollPanel
     */
    private JPanel occurrencesPanel() {
        JPanel p=new JPanel();
        p.setLayout(new BorderLayout());
        buildBorder("Occurrences", p);
        modelReplace = new PreviewerList(new ArrayList(), "");
        replaceList = new JList(modelReplace);
        replaceList.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        replaceList.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Occurrences: "));
        replaceList.addMouseListener(new MouseAdapter() {
            @Override
          
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList) evt.getSource();
                int index = list.locationToIndex(evt.getPoint());
                if (evt.getClickCount() == 1 && index >= 0) {
                    PreviewCellDTO prev=(PreviewCellDTO) replaceList.getSelectedValue();
                 
                }
            }
        }); 
        JScrollPane scroll = new JScrollPane(replaceList);
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
        JButton btReplace= new JButton("Replace");
        //b.setPreferredSize(new Dimension(10,10));
        btReplace.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrl.stopReplaceThread();
                 PreviewCellDTO prev= (PreviewCellDTO) replaceList.getSelectedValue();

            if(prev.getAfterCell().getContent() == null ? prev.getBeforeCell().getContent() != null : !prev.getAfterCell().getContent().equals(prev.getBeforeCell().getContent())){
                if(replaceField.getText().length()>0 && toField.getText().length()>0){              
                    try {
                          
                        if(replaceList.getSelectedIndex()>=0){
                            
                           modelReplace.setSelectedItem(prev.getBeforeCell().toString());

                          
                            PreviewCellDTO selected=(PreviewCellDTO)  modelReplace.getSelectedItem();

                            if (ctrl.checkIfPreviewValid(selected.getBeforeCell(),
                                    replaceField.getText(), toField.getText(), extension)!=null ){

                                ctrl.startPreviewThread(selected.getBeforeCell(),
                                        replaceField.getText(),toField.getText());

                                try {
                                   ctrl.stopReplaceThread();


                                   modelReplace.setSelectedItem(prev.getBeforeCell().toString());
                                    extension.setActiveCell(modelReplace.getSelectedItem().getBeforeCell());

                                    ctrl.startReplaceThread(selected.getBeforeCell(), replaceField.getText(),
                                            toField.getText(),extension);
                                  //  selected.getBeforeCell().setContent(selected.getAfterCell().getContent());
                                    updateUI();

                                } catch (CloneNotSupportedException ex) {
                                    JOptionPane.showMessageDialog(null,
                                            "It wasn´t possible to replace.",
                                            "Replace error",
                                            JOptionPane.ERROR_MESSAGE);
//                                        } catch (FormulaCompilationException ex ) {
//                                            JOptionPane.showMessageDialog(null,
//                                                    "It wasn´t possible to replace.",
//                                                    "Formula compilation error",
//                                                    JOptionPane.ERROR_MESSAGE);
                                }

                            } else {
                                JOptionPane.showMessageDialog(null,
                                        "The inserted expression to replace is not valid. Verify if cell contains expression to replace",
                                        "Invalid replace text",
                                        JOptionPane.ERROR_MESSAGE);
                            }

                        }
                    } catch (CloneNotSupportedException ex) {
                        Logger.getLogger(SearchReplaceSideBar.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    }else{
                         JOptionPane.showMessageDialog(null,
                                    "Please fill the replace and expression to replace.",
                                    "Invalid fieds",
                                    JOptionPane.ERROR_MESSAGE);
                    }   
                } 
              
            }
                
        }));
        return btReplace;
    }
    
      private JButton nextButton(){
        JButton b = new JButton("Next");
        //b.setPreferredSize(new Dimension(10,10));
        b.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(replaceList.getSelectedIndex()<modelReplace.getSize()){
                     replaceList.setSelectedIndex(replaceList.getSelectedIndex()+1);
                }else{
                    replaceList.setSelectedIndex(0);
                }
            }
        }));
        return b;
    }
    
    private JButton buttonsReplaceAll(){
        JButton btReplaceAll = new JButton("Replace All");
        //b.setPreferredSize(new Dimension(10,10));
        btReplaceAll.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 PreviewCellDTO prev= (PreviewCellDTO) replaceList.getSelectedValue();
                ctrl.stopPreviewThread(); 
                ctrl.stopReplaceThread();
      
            if(prev.getAfterCell().getContent() == null ? prev.getBeforeCell().getContent() != null : !prev.getAfterCell().getContent().equals(prev.getBeforeCell().getContent())){
                if(replaceField.getText().length()>0 && toField.getText().length()>0){              
                 
                    ctrl.stopReplaceThread();                                      
                    modelReplace.setSelectedItem(prev.getBeforeCell().toString());
                    ctrl.startReplaceAllThreads(modelReplace, replaceField.getText(), toField.getText(), extension);
                    updateUI();

                }else{
                     JOptionPane.showMessageDialog(null,
                                "Please fill the replace and expression to replace.",
                                "Invalid fieds",
                                JOptionPane.ERROR_MESSAGE);
                }   
            } 
              
            
            }
        }));
        return btReplaceAll;
    }
    
    private JPanel replaceFields(){
        JPanel upper=new JPanel();
        upper.setLayout(new BorderLayout());
        JPanel p=new JPanel();
        JPanel replacePanel=new JPanel();
        replacePanel.setLayout(new GridLayout(2,1));
 
        
        JPanel pReplace=new JPanel();
        
        BoxLayout box = new BoxLayout(pReplace, BoxLayout.X_AXIS);
        pReplace.setLayout(box);;
        
        replaceField=new JTextField();
        replaceField.setPreferredSize(new Dimension(100,5));
       
        JLabel jlabel=new JLabel("Replace");
        
        pReplace.add(jlabel);
        pReplace.add(replaceField); 
        
        
         JPanel pTo=new JPanel();
        
        BoxLayout box2 = new BoxLayout(pTo, BoxLayout.X_AXIS);
        pTo.setLayout(box2);;
        
        toField=new JTextField();
        toField.setPreferredSize(new Dimension(100,5));
       
        JLabel jlabel2=new JLabel("To");
        
        pTo.add(jlabel2);
        pTo.add(toField); 
        
     
        replacePanel.add(pReplace);
        replacePanel.add(pTo);
        
        JPanel button =new JPanel();
        button.add(createPreviewButton());
        
        p.add(replacePanel,BorderLayout.NORTH);
        p.add(button,BorderLayout.SOUTH);
        
        upper.add(p, BorderLayout.WEST);
        upper.add(previewPanel(), BorderLayout.EAST );
        
        return upper;
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
                    try {
                        extension.setActiveCell(model.getSelectedItem().getCell());
                    } catch (CloneNotSupportedException ex) {
                        Logger.getLogger(SearchReplaceSideBar.class.getName()).log(Level.SEVERE, null, ex);
                    }
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
            @Override
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
                
                ctrl.stopPreviewThread();
                modelReplace.removeAll();
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
        JButton btPreview = new JButton("Preview");
        //b.setPreferredSize(new Dimension(10,10));
        btPreview.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrl.stopPreviewThread();            
                try {
                  
                      if(replaceList.getSelectedIndex()>=0){
                        PreviewCellDTO prev=(PreviewCellDTO) replaceList.getSelectedValue();
                        modelReplace.setSelectedItem(prev.getBeforeCell().toString());

                        if(replaceField.getText().length()>0 && toField.getText().length()>0){              
                            if (modelReplace.getSelectedItem()!=null){
                                PreviewCellDTO selected=(PreviewCellDTO)  modelReplace.getSelectedItem();

                                if (ctrl.checkIfPreviewValid(selected.getBeforeCell(),
                                    replaceField.getText(), toField.getText(), extension)!=null ){

                                    ctrl.startPreviewThread(selected.getBeforeCell(),
                                            replaceField.getText(),toField.getText());
                                } else {
                                    JOptionPane.showMessageDialog(null,
                                            "The inserted expression to replace is not valid. Verify if cell contains expression to replace",
                                            "Invalid replace text",
                                            JOptionPane.ERROR_MESSAGE);
                                }

                            }

                            }else{
                                 JOptionPane.showMessageDialog(null,
                                            "Please fill the replace and expression to replace.",
                                            "Invalid fieds",
                                            JOptionPane.ERROR_MESSAGE);
                            }


                        }
                    
                    
                } catch (CloneNotSupportedException ex) {
                    JOptionPane.showMessageDialog(null,
                                            "It wasn´t possible to preview.",
                                            "Invalid fieds",
                                            JOptionPane.ERROR_MESSAGE);
                }

        }
    }));
    return btPreview;
    }

    @Override
    public void update(Observable o, Object arg) {

        if (arg!=null && arg instanceof PreviewCellDTO) {
            java.awt.EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {

                        PreviewCellDTO cell= (PreviewCellDTO) arg;
                        try {
                            previewLabel.setText(cell.buildCellPreviewDescription());    
                            previewLabel.updateUI(); 

                        } catch (IllegalValueTypeException | FormulaCompilationException ex) {
                               JOptionPane.showMessageDialog(null,
                            "It wasn´t possible to preview! ",
                            "Preview error",
                            JOptionPane.ERROR_MESSAGE);
                        }

                    }
                });

            }



            if(arg!=null && arg instanceof CellInfoDTO) {
                    if (!((CellInfoDTO) arg).getCell().toString().equals("0")) {// ignore the null CELL
                        java.awt.EventQueue.invokeLater(new Runnable() {
                            @Override
                            public void run() {

                                CellInfoDTO cell= (CellInfoDTO) arg;
                                model.addElement(cell);
                                searchList.updateUI();   

                                PreviewCellDTO preview=new PreviewCellDTO(cell.getCell(), "", "");

                                modelReplace.addElement(preview);

                                if(modelReplace.getSize()>0){
                                   replaceList.setSelectedIndex(0);
                                }

                                replaceList.updateUI();    
                            }
                        });
                    }
            }
               if(arg instanceof CellInfoDTO && arg==null) {
                JOptionPane.showMessageDialog(null,
                                "No results!",
                                "No results",
                                JOptionPane.ERROR_MESSAGE);
            }
        }
}