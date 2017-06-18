package lapr4.blue.s3.core.n1141570.insertImage.ui;

import csheets.core.Cell;
import csheets.ui.ctrl.SelectionEvent;
import csheets.ui.ctrl.SelectionListener;
import csheets.ui.ctrl.UIController;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
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
import javax.swing.JDialog;
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
    private final InsertImageController controller;

    /**
     * The imageable cell currently being displayed in the panel
     */
    private ImageableCell cell;

    /**
     * The image label.
     */
    private JLabel imageLabel = new JLabel();

    /**
     * The card layout.
     */
    private final CardLayout cardLayout = new CardLayout();

    /**
     * The card layout panel.
     */
    private final JPanel cardLayoutPanel = new JPanel();

    /**
     * The user interface controller.
     */
    private final UIController uiController;

    /**
     * The image path.
     */
    private String imagePath = "";

    /**
     * The default button size.
     */
    private final Dimension BUTTON_SIZE = new Dimension(100, 30);

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

        // calls the user interface components
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

    /**
     * Creates the card layout panel.
     *
     * @return the card layout panel.
     */
    private JPanel cardLayoutPanel() {

        if (cell != null) {
            if (!cell.getImages().isEmpty()) {
                for (String imgStr : cell.getImages()) {
                    ImageIcon icon = new ImageIcon(imgStr);

                    icon = new ImageIcon(imgStr);
                    Image img = icon.getImage();
                    Image newimg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);

                    imageLabel = new JLabel(new ImageIcon(newimg));
                    imageLabel.setName(imgStr);
                    cardLayoutPanel.add(imageLabel);
                }
            }
        }
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

        buttonsPanel.add(openImageButton());
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
     * Creates the add button.
     *
     * @return add button
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
                    boolean notFound = true;
                    if (f != null) {

                        path = f.getAbsolutePath();

                        Cell activeCell = uiController.getActiveCell();
                        ImageableCell imageableCell = (ImageableCell) activeCell.getExtension(ImagesExtension.NAME);

                        for (String currentImage : imageableCell.getImages()) {
                            if (currentImage.equalsIgnoreCase(path)) {
                                notFound = false;
                            }
                        }

                        if (notFound) {
                            imageableCell.setImageToImages(path);
                        }
                        imagePath = path;

                        if (!notFound) {
                            JOptionPane.showMessageDialog(chooser, "Cannot add the same image!", "Error", JOptionPane.ERROR_MESSAGE);
                        }

                    } else {
                        JOptionPane.showMessageDialog(chooser, "Chosen file is not valid!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(chooser, "Canceled the image choosing", "Cancel", JOptionPane.CANCEL_OPTION);
                }

            }
        });

        return addImageButton;
    }

    /**
     * Creates the remove image button.
     *
     * @return remove button
     */
    private JButton removeImageButton() {
        JButton removeImageButton = new JButton("Remove");
        removeImageButton.setPreferredSize(BUTTON_SIZE);

        removeImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                boolean removed = false;

                Component visibleComponent = getCurrentCard();
                String imgPath = ((JLabel) visibleComponent).getName();

                //cardLayout.removeLayoutComponent(visibleComponentName.);
                if (cell.removeImageFromImages(imgPath)) {
                    removed = true;
                }
                //refresh the card layout
                ImageableCell activeCell
                        = (ImageableCell) cell.getExtension(ImagesExtension.NAME);
                imageChanged(activeCell);

                if (removed) {
                    JOptionPane.showMessageDialog(cardLayoutPanel, "Removed the current image.", "Remove image", JOptionPane.PLAIN_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(cardLayoutPanel, "Removing the current image failed.", "Removed failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        return removeImageButton;
    }

    /**
     * Creates the open image button.
     *
     * @return open button
     */
    private JButton openImageButton() {
        JButton removeImageButton = new JButton("Open");
        removeImageButton.setPreferredSize(BUTTON_SIZE);

        removeImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                JDialog dialog = new JDialog((Frame) null, "Image selected to open");
                dialog.setUndecorated(true);

                Component visibleComponent = getCurrentCard();
                String imgStr = ((JLabel) visibleComponent).getName();

                ImageIcon icon = new ImageIcon(imgStr);

                icon = new ImageIcon(imgStr);
                Image img = icon.getImage();

                JLabel imageLabel = new JLabel(new ImageIcon(img));
                imageLabel.setName(imgStr);

                dialog.add(imageLabel);
                dialog.setLocationRelativeTo(cardLayoutPanel);
                dialog.pack();
                dialog.setVisible(true);

            }
        });
        return removeImageButton;
    }

    /**
     * Creates the next image button
     *
     * @return the next button
     */
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

    /**
     * Creates the previous image button.
     *
     * @return the previous button
     */
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
     * Updates the image path
     *
     * @param imagePath the new image path
     */
    public void setUserImage(String imagePath) {

        cardLayoutPanel.removeAll();
        this.imagePath = imagePath;
        ImageIcon icon = new ImageIcon(imagePath);
        if (cell != null) {

            icon = new ImageIcon(cell.getImage());
        }

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

        controller.cellSelected(cell);
    }

    /**
     * Obtains the active component of the cardLayoutPanel
     *
     * @return the active component of the cardLayoutPanel
     */
    public Component getCurrentCard() {
        // String activeImageStr = null;
        Component activeComponent = new Component() {
        };

        for (Component comp : cardLayoutPanel.getComponents()) {
            if (comp.isVisible() == true) {
                if (comp instanceof JLabel) {
                    activeComponent = comp;
                }
            }
        }

        return activeComponent;
    }

}
