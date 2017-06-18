package lapr4.blue.s3.core.n1141570.insertImage.ui;

import csheets.core.Cell;
import csheets.ui.ctrl.SelectionEvent;
import csheets.ui.ctrl.SelectionListener;
import csheets.ui.ctrl.UIController;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import lapr4.blue.s3.core.n1141570.insertImage.ImageableCell;
import lapr4.blue.s3.core.n1141570.insertImage.ImageableCellListener;
import lapr4.blue.s3.core.n1141570.insertImage.ImagesExtension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Eric
 */
@SuppressWarnings("serial")
public class ImagesPanel extends JPanel implements SelectionListener,
        ImageableCellListener {

    /**
     * The assertion controller
     */
    private InsertImageController controller;

    /**
     * The imageable cell currently being displayed in the panel
     */
    private ImageableCell cell;

    /**
     * The image label.
     */
    private JLabel imageLabel = new JLabel();

    private final CardLayout cardLayout = new CardLayout();

    private final JPanel cardLayoutPanel = new JPanel();

    private final UIController uiController;

    private String imagePath = "";

    private final Dimension BUTTON_SIZE = new Dimension(115, 30);

    /**
     * Creates a new images panel.
     *
     * @param uiController the user interface controller
     */
    public ImagesPanel(UIController uiController) {
        // Configures panel
        super(new BorderLayout());
        setName(ImagesExtension.NAME);

        // Creates controller
        controller = new InsertImageController(uiController, this);
        uiController.addSelectionListener(this);
        this.uiController = uiController;

        createComponents();
    }

    /**
     * Creates the user interface components.
     */
    private void createComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(cardLayoutPanelMain(), BorderLayout.CENTER);
        mainPanel.add(createButtonsPanel(), BorderLayout.SOUTH);

        add(mainPanel);
    }

    /**
     * Creates the labels and text fields panel.
     *
     * @return the labels and text fields panel.
     */
    private JPanel cardLayoutPanelMain() {
        JPanel cardLayoutPanelMain = new JPanel(new BorderLayout());

        cardLayoutPanelMain.add(cardLayoutPanel(), BorderLayout.CENTER);
        cardLayoutPanelMain.add(createButtonsPanelOnCardLayout(), BorderLayout.SOUTH);

        return cardLayoutPanelMain;
    }

    private JPanel cardLayoutPanel() {

        //FOR TESTING
        System.out.println("ATUALIZASTE_ME!!!");

        if (cell != null) {
            if (!cell.getImages().isEmpty()) {
                for (String imgStr : cell.getImages()) {
                    ImageIcon icon = new ImageIcon(imgStr);

                    icon = new ImageIcon(imgStr);
                    Image img = icon.getImage();
                    Image newimg = img.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);

                    imageLabel = new JLabel(new ImageIcon(newimg));
                    cardLayoutPanel.add(imageLabel);
                }
            }

        }

        ImageIcon icon = new ImageIcon(imagePath);
        if (cell != null) {

            icon = new ImageIcon(cell.getImage());
        }
        System.out.println("IMAGE PATH no cardLayoutPanel: " + imagePath);

        Image img = icon.getImage();
        Image newimg = img.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);

        imageLabel = new JLabel();
        imageLabel.setText(imagePath);
        imageLabel.setName(imagePath);
        imageLabel.setIcon(icon);
        
        cardLayoutPanel.add(imageLabel);

        //creates layout
        cardLayoutPanel.setLayout(cardLayout);

        return cardLayoutPanel;
    }

    /**
     * Creates the buttons panel.
     *
     * @return buttons panel
     */
    private JPanel createButtonsPanel() {
        JPanel buttonsPanel = new JPanel();

        buttonsPanel.add(addImageButton());
        buttonsPanel.add(removeImageButton());

        return buttonsPanel;
    }

    /**
     * Creates the buttons panel on card layout panel.
     *
     * @return buttons panel on card layout
     */
    private JPanel createButtonsPanelOnCardLayout() {
        JPanel buttonsPanelOnCardLayout = new JPanel();

        buttonsPanelOnCardLayout.add(previousImageButton());
        buttonsPanelOnCardLayout.add(nextImageButton());

        return buttonsPanelOnCardLayout;
    }

    /**
     * Creates the save button.
     *
     * @return save button
     */
    private JButton addImageButton() {
        JButton addImageButton = new JButton("Add");
        addImageButton.setPreferredSize(BUTTON_SIZE);

        addImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                JFileChooser chooser = new JFileChooser();
                int returnName = chooser.showOpenDialog(null);
                String path;

                if (returnName == JFileChooser.APPROVE_OPTION) {
                    File f = chooser.getSelectedFile();
                    if (f != null) {

                        path = f.getAbsolutePath();

                        Cell activeCell = uiController.getActiveCell();
                        ImageableCell imageableCell = (ImageableCell) activeCell.getExtension(ImagesExtension.NAME);
                        //imageableCell.setImage(path);
                        ////////////////////////////////////////////////////
                        imageableCell.setImageToImages(path);
                        imagePath = path;
                        System.out.println("Image changed to " + imagePath);
                    } else {
                        JOptionPane.showMessageDialog(chooser, "Chosen file is not valid!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(chooser, "Canceled", "Cancel", JOptionPane.CANCEL_OPTION);
                }

            }
        });

        return addImageButton;
    }

    /**
     * Creates the cancel button.
     *
     * @return cancel button
     */
    private JButton removeImageButton() {
        JButton removeImageButton = new JButton("Remove");
        removeImageButton.setPreferredSize(BUTTON_SIZE);

        removeImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                Component visibleComponentName = getCurrentCard();
                String nameTest = visibleComponentName.getName();

                ((JLabel) visibleComponentName).setIcon(null);
                String name = ((JLabel) visibleComponentName).getText();
                String name2 = ((JLabel) visibleComponentName).getName();
                

                //cardLayout.removeLayoutComponent(visibleComponentName.);
                cell.removeImageFromImages(name);

            }
        });
        return removeImageButton;
    }

    private JButton nextImageButton() {
        JButton nextImageButton = new JButton("Next");
        nextImageButton.setPreferredSize(BUTTON_SIZE);
        nextImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout.next(cardLayoutPanel);

            }
        });

        return nextImageButton;
    }

    private JButton previousImageButton() {
        JButton previousImageButton = new JButton("Previous");
        previousImageButton.setPreferredSize(BUTTON_SIZE);
        previousImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout.previous(cardLayoutPanel);
            }
        });

        return previousImageButton;
    }

    /**
     * Updates the images path
     *
     * @param event the selection event that was fired
     */
    public void selectionChanged(SelectionEvent event) {
        Cell cell = event.getCell();
        if (cell == null) {
            return;
        }
        if (cell.getExtension(ImagesExtension.NAME) == null) {
            return;
        }

        if (cell != null) {
            ImageableCell activeCell
                    = (ImageableCell) cell.getExtension(ImagesExtension.NAME);
            activeCell.addImageableCellListener(this);

            imageChanged(activeCell);

            imagePath = activeCell.getImage();
            System.out.println("The image path for this cell is " + imagePath);
            
        } else {
            imagePath = "";
        }

        // Stops listening to previous active cell
        if (event.getPreviousCell() != null) {
            ((ImageableCell) event.getPreviousCell().getExtension(ImagesExtension.NAME))
                    .removeImageableCellListener(this);
        }
    }

    /**
     * Updates the image
     *
     * @param imagePath
     */
    public void setUserImage(String imagePath) {

        cardLayoutPanel.removeAll();
        this.imagePath = imagePath;
        ImageIcon icon = new ImageIcon(imagePath);
        if (cell != null) {

            icon = new ImageIcon(cell.getImage());
        }

        //PARA TESTAR
        System.out.println("Image path no setUserImage: " + imagePath);

        Image img = icon.getImage();
        Image newimg = img.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);

        imageLabel = new JLabel(new ImageIcon(newimg));

        //Refreshes the cardLayoutPanel
        cardLayoutPanel().validate();
        cardLayoutPanel().repaint();

    }

    @Override
    public void imageChanged(ImageableCell cell) {
        // Stores the cell for use when applying images
        this.cell = cell;

//        // The controller must decide what to do...
//        controller.cellSelected(cell);
        ////////////////////////////////////////////////
        controller.cellSelected2(cell);
    }

    public Component getCurrentCard() {
        // String activeImageStr = null;
        Component activeComponent = new Component() {
        };

        for (Component comp : cardLayoutPanel.getComponents()) {
            if (comp.isVisible() == true) {

                if (comp instanceof JLabel) {
                    //((JLabel) component).toString();
                    activeComponent = comp;
                }

            }
        }

        //return activeImageStr;
        return activeComponent;
    }

}
