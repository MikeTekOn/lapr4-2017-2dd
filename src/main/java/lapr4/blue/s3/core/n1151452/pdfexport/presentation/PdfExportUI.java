package lapr4.blue.s3.core.n1151452.pdfexport.presentation;

import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import csheets.core.Address;
import csheets.core.Cell;
import csheets.core.Spreadsheet;
import csheets.ext.style.StyleExtension;
import csheets.io.FilenameExtensionFilter;
import csheets.ui.FileChooser;
import lapr4.blue.s3.core.n1151452.pdfexport.application.PdfExportController;
import lapr4.blue.s3.core.n1151452.pdfexport.domain.GridType;
import lapr4.blue.s3.core.n1151452.pdfexport.domain.PageOrientation;
import lapr4.blue.s3.core.n1151452.pdfexport.domain.PrintArea;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents the UI Dialog of the PDF Export option
 *
 * @author Daniel Gonçalves [1151452@isep.ipp.pt]
 *         on 18/06/17.
 */
@SuppressWarnings("Duplicates")
public class PdfExportUI extends JDialog {

    private static final int WIDTH = 620;
    private static final int HEIGHT = 420;

    private JFrame frame;

    private List<Cell> cells;

    private JRadioButton radioBtnPortrait;
    private JRadioButton radioBtnLanscape;
    private JComboBox<String> pageSizeCombo;
    private JComboBox<PrintArea> printAreaCombo;
    private JList<PrintArea> printAreaJList;
    private JCheckBox headersCheckbox;
    private JCheckBox macrosCheckbox;
    private JCheckBox formulasCheckbox;
    private JCheckBox commentsCheckbox;
    private JTextField rangeField;

    /**
     * The button that checks if the border chosen is "Solid Border"
     */
    private JRadioButton radioButtonSolidBorder;

    /**
     * The button that checks if the border chosen is "Dotted Border"
     */
    private JRadioButton radioButtonDottedBorder;

    /**
     * The button that checks if the border chosen is "No Border"
     */
    private JRadioButton radioButtonNoBorder;

    /**
     * The border color
     */
    private Color gridColor = Color.BLACK;

    public PdfExportUI(JFrame parent, List<Cell> selectedCells) {
        super(parent, "PDF Export", true);

        if (selectedCells == null || selectedCells.isEmpty()) throw new IllegalStateException("Cells can't be null.");

        cells = selectedCells;
        frame = parent;

        super.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        super.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        // Create components
        JPanel panel = new JPanel(new GridLayout(1, 3, 20, 20));
        panel.add(createGeneralPanel());

        JPanel setupAndSectionsPanel = new JPanel();
        BoxLayout setupLayout = new BoxLayout(setupAndSectionsPanel, BoxLayout.Y_AXIS);
        setupAndSectionsPanel.setLayout(setupLayout);
        setupAndSectionsPanel.add(createPageSetupPanel());
        setupAndSectionsPanel.add(createSectionsPanel());
        panel.add(setupAndSectionsPanel);

        setLayout(new BorderLayout(20, 20));
        add(panel, BorderLayout.CENTER);
        add(createExportButton(), BorderLayout.SOUTH);

        setFocusable(true);
        pack();
        setLocationRelativeTo(parent);
        super.setVisible(true);
    }

    /**
     * Creates a panel with the page setup options
     *
     * @return a panel with the page setup options
     */
    private JPanel createPageSetupPanel() {

        TitledBorder border = BorderFactory.createTitledBorder("Page Setup");
        border.setTitleJustification(TitledBorder.CENTER);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(border);

        JLabel comboLbl = new JLabel("Page Size");
        Field[] fields = PageSize.class.getDeclaredFields();
        String[] pageSizes = new String[fields.length];
        for (int i = 0; i < pageSizes.length; i++) {
            pageSizes[i] = fields[i].getName().toUpperCase();
        }
        pageSizeCombo = new JComboBox<>(pageSizes);
        pageSizeCombo.setEditable(false);
        pageSizeCombo.setSelectedItem("A4");
        JPanel comboPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(comboPanel, BoxLayout.Y_AXIS);
        comboPanel.setLayout(boxLayout);
        comboLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        pageSizeCombo.setAlignmentX(Component.CENTER_ALIGNMENT);
        comboPanel.add(comboLbl);
        comboPanel.add(pageSizeCombo);

        Icon portraitIcon = new ImageIcon(PdfExportUI.class.getResource("portrait.png"));
        radioBtnPortrait = new JRadioButton("Portrait", true);
        radioBtnPortrait.setAlignmentX(Component.RIGHT_ALIGNMENT);
        JPanel portraitPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        portraitPanel.add(radioBtnPortrait);
        portraitPanel.add(new JLabel(portraitIcon));

        Icon landscapeIcon = new ImageIcon(PdfExportUI.class.getResource("landscape.png"));
        radioBtnLanscape = new JRadioButton("Landscape", false);
        radioBtnLanscape.setAlignmentX(Component.RIGHT_ALIGNMENT);
        JPanel landscapePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        landscapePanel.add(radioBtnLanscape);
        landscapePanel.add(new JLabel(landscapeIcon));

        ButtonGroup radioGroup = new ButtonGroup();
        radioGroup.add(radioBtnPortrait);
        radioGroup.add(radioBtnLanscape);

        JPanel radioPanel = new JPanel();
        radioPanel.add(portraitPanel);
        radioPanel.add(landscapePanel);
        JLabel radioLbl = new JLabel("Page Orientation");
        radioLbl.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel orientationPanel = new JPanel(new BorderLayout());
        orientationPanel.add(radioLbl, BorderLayout.PAGE_START);
        orientationPanel.add(radioPanel, BorderLayout.CENTER);

        panel.add(comboPanel, BorderLayout.NORTH);
        panel.add(orientationPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createGeneralPanel() {

        TitledBorder border = BorderFactory.createTitledBorder("General");
        border.setTitleJustification(TitledBorder.CENTER);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(border);

        printAreaCombo = new JComboBox<>(PrintArea.values());
        printAreaCombo.setSelectedItem(PrintArea.ACTIVE_SPREADSHEET);
        printAreaCombo.setEditable(false);

        JLabel comboLbl = new JLabel("Print Area");
        JPanel printPanel = new JPanel();
        BoxLayout printLayout = new BoxLayout(printPanel, BoxLayout.Y_AXIS);
        printPanel.setLayout(printLayout);
        comboLbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        printAreaCombo.setAlignmentX(Component.LEFT_ALIGNMENT);

        printAreaCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (printAreaCombo.getSelectedItem().equals(PrintArea.RANGE)) {
                    rangeField.setEditable(true);
                } else {
                    rangeField.setEditable(false);
                }
            }
        });

        JPanel flow = new JPanel(new FlowLayout());
        rangeField = new JTextField(25);
        rangeField.setToolTipText("Example: A4:B7");
        rangeField.setEditable(false);

        flow.add(new JLabel("Cell Range: "));
        flow.add(rangeField);
        flow.setAlignmentX(Component.LEFT_ALIGNMENT);

        printPanel.add(comboLbl);
        printPanel.add(printAreaCombo);
        printPanel.add(flow);

        headersCheckbox = new JCheckBox("Grid Headers", true);
        JPanel comboPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(comboPanel, BoxLayout.Y_AXIS);
        comboPanel.setLayout(boxLayout);
        headersCheckbox.setAlignmentX(Component.LEFT_ALIGNMENT);
        comboPanel.add(printPanel);
        comboPanel.add(headersCheckbox);

        JPanel borderPanel = new JPanel();
        BoxLayout gridBoxLayout = new BoxLayout(borderPanel, BoxLayout.Y_AXIS);
        borderPanel.setLayout(gridBoxLayout);

        JPanel btnsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        radioButtonSolidBorder = new JRadioButton("Solid", true);
        radioButtonDottedBorder = new JRadioButton("Dotted", false);
        radioButtonNoBorder = new JRadioButton("No Border", false);
        ButtonGroup group = new ButtonGroup();
        group.add(radioButtonSolidBorder);
        group.add(radioButtonDottedBorder);
        group.add(radioButtonNoBorder);

        btnsPanel.add(radioButtonSolidBorder);
        btnsPanel.add(radioButtonDottedBorder);
        btnsPanel.add(radioButtonNoBorder);
        JButton colorBtn = createBorderColorButton();
        btnsPanel.add(colorBtn);
        btnsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        borderPanel.add(new JLabel("Grid", JLabel.LEFT));
        borderPanel.add(btnsPanel);

        panel.add(comboPanel, BorderLayout.NORTH);
        panel.add(borderPanel, BorderLayout.CENTER);

        return panel;
    }

    private JButton createBorderColorButton() {
        JButton button = new JButton(new ImageIcon(StyleExtension.class.getResource("res/img/color_bg.gif")));
        button.setContentAreaFilled(false);
        button.setPreferredSize(new Dimension(25, 25));
        button.addActionListener((ActionEvent e) -> {
            gridColor = JColorChooser.showDialog(
                    null, "Choose Border Color", Color.BLACK);
        });
        return button;
    }

    private JPanel createSectionsPanel() {

        TitledBorder border = BorderFactory.createTitledBorder("Sections");
        border.setTitleJustification(TitledBorder.CENTER);

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.setBorder(border);

        macrosCheckbox = new JCheckBox("Macros", false);
        formulasCheckbox = new JCheckBox("Formulas", false);
        commentsCheckbox = new JCheckBox("Comments", false);

        panel.add(macrosCheckbox);
        panel.add(formulasCheckbox);
        panel.add(commentsCheckbox);

        return panel;
    }

    private JPanel createExportButton() {
        JButton exportButton = new JButton("Export");
        exportButton.addActionListener((ActionEvent e) -> {

//            FileFilter filter = new FileNameExtensionFilter("Portable Document Format", "pdf");

            FileChooser.Filter filter = new FileChooser.Filter(
                    new FilenameExtensionFilter("pdf"), "Portable Document Format");

            FileChooser fileChooser = new FileChooser(rootPane, null);
            fileChooser.addChoosableFileFilter(filter);
            File savedFile = null;

            // Prompts the user for a file
            boolean promptForFile = true, canceled = false;
            while (promptForFile) {
                savedFile = fileChooser.getFileToSave();
                if (savedFile != null) {
                    if (savedFile.exists()) {
                        // Prompt to overwrite the file
                        int option = JOptionPane.showConfirmDialog(
                                null,
                                "The chosen file " + savedFile + " already exists\n" +
                                        "Do you want to overwrite it?",
                                "Replace existing file?",
                                JOptionPane.YES_NO_CANCEL_OPTION,
                                JOptionPane.WARNING_MESSAGE
                        );

                        if (option == JOptionPane.YES_OPTION) {

                            promptForFile = false;
                        } else if (option == JOptionPane.CANCEL_OPTION || option == JOptionPane.CLOSED_OPTION) {
                            canceled = true;
                            break;
                        }
                    } else {
                        promptForFile = false;
                    }
                } else {
                    break;
                }
            }

            if (savedFile == null || canceled) {
                JOptionPane.showMessageDialog(rootPane, "Didn't select a file!", "Export failed!", JOptionPane.WARNING_MESSAGE);
            } else {

                if (!rangeField.getText().trim().equals("")) {
                    try {

                        String text = rangeField.getText().trim();

                        Spreadsheet spread = cells.get(0).getSpreadsheet();

                        String[] range = text.split(":");

                        String column1 = range[0].split("[0-9]+")[0];
                        String row1 = range[0].split("[A-Z]+")[1];
                        Address from = new Address(column1, row1);

                        String column2 = range[1].split("[0-9]+")[0];
                        String row2 = range[1].split("[A-Z]+")[1];
                        Address to = new Address(column2, row2);

                        cells = new LinkedList<>(spread.getCells(from, to));

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(rootPane, "Inserted Range is invalid. The range should be in A1:B2 format.");
                        return;
                    }
                }

                GridType gridType;
                if (radioButtonNoBorder.isSelected()) gridType = GridType.NO_GRID;
                else if (radioButtonDottedBorder.isSelected()) gridType = GridType.DOTTED;
                else gridType = GridType.SOLID;

                Rectangle pageSize = PageSize.getRectangle((String) pageSizeCombo.getSelectedItem());

                PageOrientation orientation;
                if (radioBtnPortrait.isSelected()) orientation = PageOrientation.PORTRAIT;
                else orientation = PageOrientation.LANDSCAPE;

                PdfOptionsDTO dto = new PdfOptionsDTO(cells, macrosCheckbox.isSelected(), formulasCheckbox.isSelected(), commentsCheckbox.isSelected(),
                        headersCheckbox.isSelected(), gridType, gridColor,  (PrintArea)printAreaCombo.getSelectedItem(), pageSize,
                        orientation, savedFile.getPath());

                PdfExportThread exportThread = new PdfExportThread(dto);
                exportThread.start();

                dispose();
            }
        });

        JPanel panel = new JPanel();

        panel.add(exportButton);
        return panel;
    }

    private class PdfExportThread extends Thread {

        private PdfOptionsDTO dto;

        private PdfExportThread(PdfOptionsDTO aDto) {

            dto = aDto;
        }

        @Override
        public void run() {

            ExportWorkerDialog dialog = new ExportWorkerDialog(frame, dto);

            PdfExportController controller = new PdfExportController();
            boolean success = false;
            try{
            success = controller.export(dto);}
            catch(RuntimeException e) {
                e.printStackTrace();
            }

//            TESTING ONLY
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            dialog.dispose();

            if (success) {
                JOptionPane.showMessageDialog(rootPane, "Export sucessful!");
            } else {

                JOptionPane.showMessageDialog(rootPane, "Export Failed!", "Export to PDF", JOptionPane.WARNING_MESSAGE);
            }

        }
    }
}
